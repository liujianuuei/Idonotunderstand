package tech.liujianwei.nio.nwp.nio.b64encodec;

import tech.liujianwei.nio.nwp.nio.b64encodec.worker.B64Worker;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class B64Server {
    public static void main(String[] args) throws IOException {
        // 对应 I/O 编程中服务端启动
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8000));
        serverSocketChannel.configureBlocking(false);
        Selector acceptanceSelector = Selector.open();
        serverSocketChannel.register(acceptanceSelector, SelectionKey.OP_ACCEPT);

        Selector readinessSelector = Selector.open();

        new Thread(() -> {
            while (true) {
                try {
                    // 监测是否有新的连接
                    //System.out.println("Checking Selector for new connection");
                    if (acceptanceSelector.select(1) > 0) {
                        Set<SelectionKey> selectedKeys = acceptanceSelector.selectedKeys();
                        Iterator<SelectionKey> keys = selectedKeys.iterator();
                        while (keys.hasNext()) {
                            SelectionKey key = keys.next();
                            if (key.isAcceptable()) {
                                // 每来一个新连接，不需要创建一个线程，而是直接注册到 Selector
                                SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                                clientChannel.configureBlocking(false);
                                clientChannel.register(readinessSelector, SelectionKey.OP_READ);
                                keys.remove();
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    // 批量轮询是否有哪些连接有数据可读
                    //System.out.println("Checking Selector for ready channel");
                    if (readinessSelector.select(1) > 0) {
                        Set<SelectionKey> selectedKeys = readinessSelector.selectedKeys();
                        Iterator<SelectionKey> keys = selectedKeys.iterator();
                        while (keys.hasNext()) {
                            SelectionKey key = keys.next();
                            if (key.isReadable()) {
                                SocketChannel clientChannel = (SocketChannel) key.channel();
                                B64Worker worker = new B64Worker(clientChannel);
                                worker.run();
                                keys.remove();
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        System.out.println("B64 Server started");
    }
}