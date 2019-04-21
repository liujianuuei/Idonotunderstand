package tech.liujianwei.tryspringcloudnetflixeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TryspringcloudNetflixEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TryspringcloudNetflixEurekaServerApplication.class, args);
    }

}
