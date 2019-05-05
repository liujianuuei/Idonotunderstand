package transferqueue;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

public class LinkedTransferQueueTest {
    private LinkedTransferQueue<String> q = new LinkedTransferQueue<>();

    public void offer(String s) {
        try {
            System.out.println("transfering...");
            boolean r = q.tryTransfer(s, 2, TimeUnit.SECONDS);
            System.out.println("transfered?"+r);
        } catch (Exception e) {
        }
    }

    public String poll() {
        try {
            return q.take();
        } catch (InterruptedException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        LinkedTransferQueueTest t = new LinkedTransferQueueTest();
        new Thread(() -> {
            String s = t.poll();
            System.out.println("got " + s);
        }).start();
        t.offer("Hi");
        System.out.println("Done");
    }
}
