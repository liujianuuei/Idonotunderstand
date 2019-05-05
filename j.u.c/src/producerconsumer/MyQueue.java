package producerconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class MyQueue<E> {
    private Queue<E> queue;

    public MyQueue() {
        this.queue = new LinkedList<E>();
    }

    public void offer(E e) {
        synchronized (queue) {
            this.queue.offer(e);
            this.queue.notify();
        }
    }

    public E poll() {
        synchronized (queue) {
            if (this.queue.isEmpty()) {
                try {
                    System.out.println(Thread.currentThread().getName() + ": waiting for new element...");
                    queue.wait();
                } catch (InterruptedException e) {
                    //
                }
            }
            E element = this.queue.poll();
            System.out.println(Thread.currentThread().getName() + ": got new element " + element);
            return element;
        }
    }

    public static void main(String[] args) {
        MyQueue<String> q = new MyQueue();
        new Thread(() -> {
            while (true) {
                q.poll();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                q.poll();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                q.offer("TA" + System.currentTimeMillis());
                try{TimeUnit.SECONDS.sleep(1);}catch (InterruptedException e){}
            }
        }).start();

        new Thread(() -> {
            while (true) {
                q.offer("TB" + System.currentTimeMillis());
                try{TimeUnit.SECONDS.sleep(1);}catch (InterruptedException e){}
            }
        }).start();
    }
}
