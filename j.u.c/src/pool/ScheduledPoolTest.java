package pool;

import java.util.concurrent.*;

public class ScheduledPoolTest {

    private ScheduledExecutorService stpe;
    private ScheduledFuture<?> future;

    private BlockingQueue<String> q = new LinkedTransferQueue<>();

    private void run() {
        stpe = Executors.newScheduledThreadPool(2);
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("run again");
                String msg = q.poll();
                if(msg != null) {
                    System.out.println(msg);
                }
            }
        };
        stpe.scheduleAtFixedRate(runnable, 10, 3, TimeUnit.SECONDS);
    }

    public void cancel() {
        future.cancel(false);
    }

    public static void main(String[] args) {
        new ScheduledPoolTest().run();
    }
}
