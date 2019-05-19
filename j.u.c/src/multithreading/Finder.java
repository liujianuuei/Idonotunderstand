package multithreading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class Finder {

    private final int poolSize;

    public Finder(final int poolSize) {
        this.poolSize = poolSize;
    }

    public List<Number> find(final long start, final long end) throws InterruptedException, ExecutionException {
        List<Number> primeNums = new ArrayList<Number>();

        System.out.println("preparing tasks...");
        ExecutorService exec = Executors.newFixedThreadPool(poolSize);
        List<Callable<Number>> tasks = new ArrayList<>();
        Worker worker = new Worker(new Number(start));
        for (long l = start; l <= end; l++) {
            tasks.add(worker);
        }
        System.out.println("executing tasks...");
        final List<Future<Number>> results = exec.invokeAll(tasks, 1 * 60 * 60, TimeUnit.SECONDS);
        exec.shutdown();
        exec.awaitTermination(1 * 60 * 60, TimeUnit.SECONDS);

        System.out.println("reducing tasks...");
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
            List<Number> results = new Finder(100).find(100000000, 101000000);
            Collections.sort(results);
            for (Number n : results) {
                System.out.println(n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000.00);
    }
}

class Worker implements Callable<Number> {
    private Number number;

    public Worker(Number number) {
        this.number = number;
    }

    @Override
    public Number call() {
        Number t = getNumber();
        t.setPrime(isPrime(t.getValue()));
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
}

class Number implements Comparable<Number> {
    private long value;
    private boolean isPrime;

    public Number(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
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

// 2.536
// 1.622