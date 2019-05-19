package tech.liujianwei.tryspringcloudnetflixeurekaclientone;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EurekaErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError() {
        //do something like logging
        return "ERROR!";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}