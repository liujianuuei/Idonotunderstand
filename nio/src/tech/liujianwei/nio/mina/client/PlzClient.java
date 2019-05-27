package tech.liujianwei.nio.mina.client;

import org.apache.mina.common.ConnectFuture;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.SocketConnector;
import org.apache.mina.transport.socket.nio.SocketConnectorConfig;
import tech.liujianwei.nio.mina.codec.PlzProtocolCodecFactory;
import tech.liujianwei.nio.mina.model.LoginMsg;

import java.net.InetSocketAddress;

public class PlzClient {

    private SocketConnector connector = null;
    private SocketConnectorConfig config = null;
    private PlzIoHandler handler;

    public PlzClient() {

    }

    public void start() {
        // ����������
        connector = new SocketConnector();

        // ��������
        config = new SocketConnectorConfig();
        // ���ӱ������
        config.getFilterChain().addLast("codec", new ProtocolCodecFilter(new PlzProtocolCodecFactory()));

        // ����������
        handler = new PlzIoHandler();
        handler.start();

        while (true) {
            try {
                ConnectFuture future = connector.connect(new InetSocketAddress("localhost", 5050), handler, config);

                // һֱ�ȴ��������
                future.join();

                // ��ɺ��ûỰ
                IoSession session = future.getSession();

                LoginMsg login = new LoginMsg();
                login.setData("|LN|user:liujianwei,password:123456|END|");
                // ���͵�½��Ϣ
                session.write(login).join();
                // ���쳣���ʾ�ɹ�.
                System.out.println("Connection was established.");

                // �������ӳɹ���һֱ�ȴ��Ự�ر�.
                session.getCloseFuture().join();
                System.out.println("Connection was closed.");
                // ���Ͽ����Ӻ�������ͼ����������
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Connection was closed, reconnecting...");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Connected failed, reconnecting...");
                // �������Ӻ�����������.
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException interE) {
                    e.printStackTrace();
                }
            }
        }
    }
}
