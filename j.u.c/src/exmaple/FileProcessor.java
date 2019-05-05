package exmaple;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FileProcessor implements Runnable {

    private Queue<String> fileNames = new ConcurrentLinkedQueue();

    public void put(String fileName) {
        this.fileNames.offer(fileName);
    }

    public String get() {
        return this.fileNames.poll();
    }

    @Override
    public void run() {
        try {
            String name = get();
            while (name != null) {
                System.out.println(Thread.currentThread().getName() + ": processing file " + name);
                for(int i=0;i<9999999;i++) {for(int j=0;j<9999999;j++) {}} // Thread.currentThread().sleep(1000);
                name = get();
            }
        } catch (Exception e) {
            //
        }
    }
}
