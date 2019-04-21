package tech.liujianwei.tryspringcloudnetflixeurekaclientanr;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class EurekaClientanrController {

    @Autowired
    private EurekaClient discoveryClient;

    public String serviceUrl() {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("trysprinigcloud-netflix-eureka-clientone", false);
        return instance.getHomePageUrl();
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/serviceanr/{id}")
    public String doGet(@PathVariable long id) {
        return restTemplate().getForEntity(serviceUrl() + "/serviceone/" + id, String.class).getBody();
    }
}
