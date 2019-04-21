package tech.liujianwei.tryspringcloudnetflixzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class TryspringcloudNetflixZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(TryspringcloudNetflixZuulApplication.class, args);
    }

}
