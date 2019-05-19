package tech.liujianwei.crawler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CrawlerService {
    public final static int TIMEOUT = 60;

    private final int totalTimes;
    private final int threadsNumber;

    public CrawlerService(int totalTimes, int threadsNumber) {
        this.totalTimes = totalTimes;
        this.threadsNumber = threadsNumber;
    }

    public void execute() {
        ExecutorService exec = Executors.newFixedThreadPool(threadsNumber);
        Crawler crawler = new Crawler();
        for (int i = 0; i < totalTimes; i++) {
            exec.submit(crawler);
        }
        exec.shutdown();

        try {
            exec.awaitTermination(TIMEOUT, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // Error handing goes here
            e.printStackTrace();
        }
    }
}
