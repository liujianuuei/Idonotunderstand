package condition;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class QueueWorker implements Runnable {

    private ReentrantLock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();

    private MarketDataService service;
    private boolean stopped = false;
    private Queue<byte[]> queue;

    public QueueWorker(MarketDataService service) {
        super();
        this.service = service;
        this.queue = new LinkedList<byte[]>();
    }

    public void run() {
        byte[] content = null;
        while (!stopped) {
            while (content == null) {
                try {
                    lock.lock();
                    //synchronized (queue) {
                    if (queue.isEmpty()) {
                        try {
                            System.out.println("waiting for queue");
                            notEmpty.await(10, TimeUnit.SECONDS);
                            if (!queue.isEmpty()) {
                                content = queue.poll();
                                System.out.println("got " + new String(content));
                            }
                        } catch (InterruptedException e) {
                            continue;
                        }
                    }
                    //}
                }finally {
                    lock.unlock();
                }
            }
            service.parseData(content);
            content = null;
        }
    }

    public void addAndNotify(byte[] content) {
        try {
            lock.lock();
            //synchronized (queue) {
            queue.offer(content);
            //queue.notify();
            notEmpty.signal();
            //}
        }finally {
            lock.unlock();
        }
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }
}