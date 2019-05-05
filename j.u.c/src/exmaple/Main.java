package exmaple;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws Exception {

        FileProcessor processor = new FileProcessor();

        System.out.println("preparig data...");

        for (int i = 1; i <= 10000; i++) {
            processor.put("STOCK.TRAN." + i);
        }

        ExecutorService pool = Executors.newFixedThreadPool(60);

        for (int i = 1; i <= 60; i++) {
            pool.submit(processor);
        }
        pool.shutdown();
    }
}
