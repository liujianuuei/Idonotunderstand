package juc;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private AtomicInteger counter = new AtomicInteger(0);

    public int next() {
        int cur = counter.get();
        boolean r = counter.compareAndSet(cur, cur + 1);
        if (r) {
            return counter.get();
        }
        System.exit(0);
        return 0;
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 1000000; i++) {
                    System.out.println(counter.next());
                }
            }
        };
        new Thread(runnable).start();
//        new Thread(runnable).start();
//        new Thread(runnable).start();
//        new Thread(runnable).start();
    }
}
