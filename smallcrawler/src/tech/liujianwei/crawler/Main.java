package tech.liujianwei.crawler;

public class Main {


    public static void main(String[] args) {
        int totalTimes = Integer.valueOf(args[0]);
        int threadsNumber = Integer.valueOf(args[1]);
        new CrawlerService(totalTimes, threadsNumber).execute();
        System.out.println("All Done");
    }
}
