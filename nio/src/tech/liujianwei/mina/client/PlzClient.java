package tech.liujianwei.mina.client;

import org.apache.mina.common.ConnectFuture;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.SocketConnector;
import org.apache.mina.transport.socket.nio.SocketConnectorConfig;
import tech.liujianwei.mina.codec.PlzProtocolCodecFactory;
import tech.liujianwei.mina.model.LoginMsg;

import java.net.InetSocketAddress;

public class PlzClient {

    private SocketConnector connector = null;
    private SocketConnectorConfig config = null;
    private PlzIoHandler handler;

    public PlzClient() {

    }

    public void start() {
        // 创建连接器
        connector = new SocketConnector();

        // 配置连接
        config = new SocketConnectorConfig();
        // 增加编解码器
        config.getFilterChain().addLast("codec", new ProtocolCodecFilter(new PlzProtocolCodecFactory()));

        // 创建处理器
        handler = new PlzIoHandler();
        handler.start();

        while (true) {
            try {
                ConnectFuture future = connector.connect(new InetSocketAddress("localhost", 5050), handler, config);

                // 一直等待连接完成
                future.join();

                // 完成后获得会话
                IoSession session = future.getSession();

                LoginMsg login = new LoginMsg();
                login.setData("|LN|user:liujianwei,password:123456|END|");
                // 发送登陆消息
                session.write(login).join();
                // 无异常则表示成功.
                System.out.println("Connection was established.");

                // 初次连接成功，一直等待会话关闭.
                session.getCloseFuture().join();
                System.out.println("Connection was closed.");
                // 当断开连接后立即试图再重新连接
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Connection was closed, reconnecting...");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Connected failed, reconnecting...");
                // 过五秒钟后再重新连接.
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException interE) {
                    e.printStackTrace();
                }
            }
        }
    }
}
