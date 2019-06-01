package tech.liujianwei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {

    @GetMapping("/id")
    public String get() {
        System.out.println("BookController:/book/id");
        //ModelAndView view = new ModelAndView("book");
        return "book";
    }

}
