package tech.liujianwei.tryspringcloudnetflixeurekaclientone;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EurekaClientoneController {

    @GetMapping("/serviceone/{id}")
    public EurekaClientoneEntity get(@PathVariable long id) {
        // Should retrieve data from data source using Spring Data
        return new EurekaClientoneEntity(id, "Hello");
    }
}
