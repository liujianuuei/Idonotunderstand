package com.oracle.suitecloudapiquery;

import com.oracle.suitecloudapiquery.dao.ApiRegistryDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class SuitecloudApiQueryApplication {

    @Bean
    ApiRegistryDao apiRegistryDao() {
        return new ApiRegistryDao();
    }

    public static void main(String[] args) {
        SpringApplication.run(SuitecloudApiQueryApplication.class, args);
    }

}
