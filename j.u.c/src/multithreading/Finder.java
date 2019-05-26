package multithreading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class Finder {

    private final int poolSize;

    public Finder(final int poolSize) {
        this.poolSize = poolSize;
    }

    public List<Number> find(final long start, final long end) throws InterruptedException, ExecutionException {
        System.out.println("preparing tasks...");
        ExecutorService exec = Executors.newFixedThreadPool(poolSize);
        List<Future<Number>> results = new ArrayList<>((int) (end - start + 1));
        //List<Callable<Number>> tasks = new ArrayList<>((int) (end - start + 1));
        Worker worker = new Worker(new Number(start));
        System.out.println("executing tasks...");
        for (long l = start; l <= end; l++) {
            results.add(exec.submit(worker));
            //tasks.add(worker);
        }
        //System.out.println("executing tasks...");
        //List<Future<Number>> results = exec.invokeAll(tasks, 24 * 60 * 60, TimeUnit.SECONDS);
        exec.shutdown();
        System.out.println("awaiting termination...");
        exec.awaitTermination(24 * 60 * 60, TimeUnit.SECONDS);

        System.out.println("reducing results...");
        List<Number> primeNums = new ArrayList<>((int) ((end - start + 1) / 10));
        for (Future<Number> f : results) {
            if (f.get().isPrime()) {
                primeNums.add(f.get());
            }
        }
        return primeNums;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        try {
            List<Number> results = new Finder(1000).find(100000000, 200000000);
            Collections.sort(results);
            System.out.println("DONE. Found " + results.size() + " prime numbers.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000.00 + " sec");
    }
}

class Worker implements Callable<Number> {
    private volatile Number number;

    public Worker(Number number) {
        this.number = number;
    }

    @Override
    public Number call() {
        Number t = null;
        try {
            t = getNumber();
            t.setPrime(isPrime(t.getValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public void setNumber(Number num) {
        this.number = num;
    }

    public Number getNumber() {
        synchronized (number) {
            return new Number(number.getAndIncrement());
        }
    }

    public boolean isPrime(long number) {
        //track(number);
        if (number <= 1) {
            return false;
        }
        for (long i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private void track(long number) {
        if (number % 10000 == 0) {
            System.out.println("[" + new Date() + "] " + Thread.currentThread().getName() + " calculating..." + number);
        }
    }
}

class Number implements Comparable<Number> {
    private long value;
    private boolean isPrime;

    public Number(long value) {
        this.value = value;
    }

    public long getValue() {
        return this.value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long getAndIncrement() {
        long v = this.value;
        this.value += 1;
        return v;
    }

    public boolean isPrime() {
        return isPrime;
    }

    public void setPrime(boolean prime) {
        isPrime = prime;
    }

    @Override
    public int compareTo(Number o) {
        if (this.value < o.value) {
            return -1;
        } else if (this.value == o.value) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}

// - Reuse of Callable/Runnable instance will increase performance by 36%
// - Increasement of the collection capacity is memory-consuming, so remember to set `initialCapacity` at first
// - Logging is very time-consuming
// - `submit`-in-loop give better performance than `invokeAll`-at-last
