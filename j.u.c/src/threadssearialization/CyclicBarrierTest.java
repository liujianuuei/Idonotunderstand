package threadssearialization;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest implements Runnable {

    private CyclicBarrier barrier;

    public CyclicBarrierTest(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " ready");
        try {
            this.barrier.await();
        } catch (BrokenBarrierException | InterruptedException e) {
            //
        }
        System.out.println(Thread.currentThread().getName() + " executed");
    }

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(3);
        CyclicBarrierTest t = new CyclicBarrierTest(barrier);

        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();

        System.out.println(Thread.currentThread().getName() + " DONE");
    }
}
