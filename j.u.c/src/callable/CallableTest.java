package callable;

import java.util.concurrent.*;

public class CallableTest implements Callable<String> {
    @Override
    public String call() throws Exception {
        for (int i = 0; i < 9999; i++) {
            //System.out.println(Thread.currentThread().getName() + " " + new Date());
        }
        return "donedone";
    }

    public static void main(String[] args) throws InterruptedException , ExecutionException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Future future = exec.submit(new CallableTest());
        System.out.println("Done?" + future.isDone());
        while (!future.isDone()) {
            //
        }
        Object s = future.get();
        System.out.println(s);
        exec.shutdown();
    }
}
