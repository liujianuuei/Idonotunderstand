package tech.liujianwei.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Date;

public class Crawler implements Runnable {
    @Override
    public void run() {
        crawl();
    }

    public void crawl() {
        try {
            System.out.println("[" + Thread.currentThread().getName() + "] crawling...[" + new Date() + "]");
            Document doc = Jsoup.connect("http://httpbin.org/").get();
            System.out.println("[" + Thread.currentThread().getName() + "] saving document...");
            System.out.println(doc.html()); // Should save document to database, e.g. MySQL, MongoDB etc.
        } catch (IOException e) {
            // Error handling goes here
            e.printStackTrace();
        }
    }
}
