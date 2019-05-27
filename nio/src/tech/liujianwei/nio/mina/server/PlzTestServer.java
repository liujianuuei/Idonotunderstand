package tech.liujianwei.nio.mina.server;

import org.apache.mina.common.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.SocketAcceptor;
import org.apache.mina.transport.socket.nio.SocketAcceptorConfig;
import tech.liujianwei.nio.mina.codec.PlzProtocolCodecFactory;

import java.net.InetSocketAddress;

public class PlzTestServer {
    public static void main(String[] args) throws Throwable {
        System.out.println("start test server, listening on port " + 5050);

        IoAcceptor acceptor = new SocketAcceptor();

        // Prepare the service configuration.
        SocketAcceptorConfig config = new SocketAcceptorConfig();
        config.setReuseAddress(true);
        config.getFilterChain().addLast("codec", new ProtocolCodecFilter(new PlzProtocolCodecFactory()));

        acceptor.bind(new InetSocketAddress(5050), new PlzServerIoHandler(), config);

        System.out.println("Listening on port " + 5050);
    }
}
