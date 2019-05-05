package threadssearialization;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest implements Runnable {

    private CountDownLatch latch;

    public CountDownLatchTest(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        this.latch.countDown();
        System.out.println(Thread.currentThread().getName() + " executed");
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        CountDownLatchTest t = new CountDownLatchTest(latch);

        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();

        latch.await();
        System.out.println(Thread.currentThread().getName() + " DONE");
    }
}
