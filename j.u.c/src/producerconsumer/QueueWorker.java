package producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

public class QueueWorker implements Runnable {

    private boolean stopped = false;
    private Queue<byte[]> queue;

    public QueueWorker() {
        super();
        this.queue = new LinkedList<byte[]>();
    }

    public void run() {
        byte[] content = null;
        while (!stopped) {
            while (content == null) {
                synchronized (queue) {
                    if (queue.isEmpty()) {
                        try {
                            queue.wait(500);
                            if(!queue.isEmpty()) {
                                content = queue.poll();
                            }
                        } catch (InterruptedException e) {
                            continue;
                        }
                    }
                }
            }
            // process content
            content = null;
        }
    }

    public void addAndNotify(byte[] content) {
        synchronized (queue) {
            queue.offer(content);
            queue.notify();
        }
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public static void main(String[] args) {
        QueueWorker worker = new QueueWorker();
        new Thread(worker).start();
    }
}