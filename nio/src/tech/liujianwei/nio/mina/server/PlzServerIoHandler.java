package tech.liujianwei.nio.mina.server;

import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoHandlerAdapter;
import org.apache.mina.common.IoSession;
import org.apache.mina.util.SessionLog;
import tech.liujianwei.nio.mina.model.LoginMsg;
import tech.liujianwei.nio.mina.model.PlzHpMsg;

public class PlzServerIoHandler extends IoHandlerAdapter {

    private PlzMessageSender plzMessageSender;

    public void sessionOpened(IoSession session) {
        // set idle time to 60 seconds
        session.setIdleTime(IdleStatus.BOTH_IDLE, 60);
        // initial sum is zero
        session.setAttachment(new Integer(0));
    }

    public void messageReceived(IoSession session, Object message) {
        LoginMsg am = (LoginMsg) message;
        System.out.println("�յ���½��Ϣ: " + am);
        // send back a response message
        session.write(am);
        System.out.println("���ͽ�����");
        if (plzMessageSender == null) {
            System.out.println("����������Ϣ�߳�");
            plzMessageSender = new PlzMessageSender(session);
            plzMessageSender.start();
        }
    }

    private class PlzMessageSender extends Thread {

        private IoSession session;

        public PlzMessageSender(IoSession sesison) {
            this.session = sesison;
        }

        public void run() {
            while (true) {
                try {
                    PlzHpMsg msg = new PlzHpMsg();
                    msg.setData("|HP|�»���|100|END|");
                    session.write(msg);
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("Connection closed.");
        plzMessageSender.join();
        System.out.println("Sender thread stopped.");
        plzMessageSender = null;
        try {
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sessionIdle(IoSession session, IdleStatus status) {
        SessionLog.info(session, "Disconnecting the idle.");
        // disconnect an idle client
        //session.close();
    }

    public void exceptionCaught(IoSession session, Throwable cause) {
        // close the connection on exceptional situation
        cause.printStackTrace();
        System.exit(1);
    }
}
