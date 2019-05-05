package syncqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueTest<E> {

    public static void main(String[] args) {
        BlockingQueue<String> q = new SynchronousQueue();
        new Thread(() -> {
            try {
                String s = q.poll(60L, TimeUnit.SECONDS); // Keep alive 60 sec
                System.out.println(s);
            } catch (Exception e) {
            }
        }).start();
        new Thread(() -> {
            try {
                boolean r = q.offer("Data" + System.currentTimeMillis(), 60L, TimeUnit.SECONDS); // Keep alive 60 sec
                System.out.println(r);
            } catch (Exception e) {
            }
        }).start();
    }
}