package com.oracle.suitecloudflowquery;

import com.oracle.suitecloudflowquery.dao.FlowDefinitionDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class SuitecloudFlowQueryApplication {

    @Bean
    FlowDefinitionDao flowDefinitionDao() {
        return new FlowDefinitionDao();
    }

    public static void main(String[] args) {
        SpringApplication.run(SuitecloudFlowQueryApplication.class, args);
    }

}
