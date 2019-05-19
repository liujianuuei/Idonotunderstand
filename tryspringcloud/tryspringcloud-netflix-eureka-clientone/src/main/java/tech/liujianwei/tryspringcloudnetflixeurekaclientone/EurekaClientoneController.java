package tech.liujianwei.tryspringcloudnetflixeurekaclientone;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EurekaClientoneController {

    @GetMapping("/serviceone/{id}")
    public EurekaClientoneEntity get(@PathVariable long id) {
        // Should retrieve data from data source using Spring Data
        if (id > 100) {
            throw new EntityNotFoundException("Entity not found");
        }
        return new EurekaClientoneEntity(id, "Hello");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
