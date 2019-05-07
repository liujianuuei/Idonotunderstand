package tech.liujianwei.mina.client;

import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoSession;
import org.apache.mina.handler.demux.DemuxingIoHandler;
import tech.liujianwei.mina.model.AbstractPlzMsg;

public class PlzIoHandler extends DemuxingIoHandler {
    PlzMessageHandler handler;

    public PlzIoHandler() {
        super();
        handler = new PlzMessageHandler();
        this.addMessageHandler(AbstractPlzMsg.class, handler);
    }

    public void stop() {
        handler.stop();
    }

    public void start() {
        handler.start();
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("MINA session was created.");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("MINA session was opened.");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("MINA session was idle");
    }

    public void exceptionCaught(IoSession session, Throwable cause) {
        System.out.println("MINA exception caught. " + cause.getMessage());
    }

    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("MINA session was closed.");
    }
}
