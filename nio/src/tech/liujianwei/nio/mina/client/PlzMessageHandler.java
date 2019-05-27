package tech.liujianwei.nio.mina.client;

import org.apache.mina.common.IoSession;
import org.apache.mina.handler.demux.MessageHandler;
import tech.liujianwei.nio.mina.client.processors.PlzMsgHpProcessor;
import tech.liujianwei.nio.mina.model.AbstractPlzMsg;
import tech.liujianwei.nio.mina.model.PlzHpMsg;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class PlzMessageHandler implements MessageHandler<AbstractPlzMsg> {

    private ArrayBlockingQueue<AbstractPlzMsg> queue;
    private MessageDispatcher dispatcher;
    private volatile boolean stopped = false;

    public PlzMessageHandler() {
        dispatcher = new MessageDispatcher();
        queue = new ArrayBlockingQueue<>(2000);
    }

    @Override
    public void messageReceived(IoSession ioSession, AbstractPlzMsg plzMessage) {
        try {
            if (stopped) {
                return;
            }
            queue.offer(plzMessage, 50L, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            //
        }
    }

    private void messageDispatched(AbstractPlzMsg message) {
        if (message instanceof PlzHpMsg) {
            new PlzMsgHpProcessor().process(message);
        } else {
            //
        }
    }

    private class MessageDispatcher extends Thread {
        private AbstractPlzMsg message;

        @Override
        public void run() {
            while (!stopped && !isInterrupted()) {
                try {
                    while ((message = queue.poll(50L, TimeUnit.MILLISECONDS)) == null) {
                        if (stopped) {
                            return;
                        }
                    }
                    messageDispatched(message);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    public void start() {
        dispatcher.start();
        stopped = false;
    }

    public void stop() {
        try {
            stopped = true;
            dispatcher.join();
        } catch (Exception e) {
            //
        }
    }
}
