package tech.liujianwei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tech.liujianwei.module.api.BookApi;
import tech.liujianwei.module.model.Book;

import javax.annotation.Resource;

@Controller
@RequestMapping("/book")
public class BookController {

    @Resource(name="BookApi")
    BookApi bookApi;

    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable String id) {
        System.out.println("BookController:GET:/book/" + id);
        Book book = bookApi.getBook(Integer.valueOf(id));
        // Can also use Model+"book" instead of ModelAndView
        ModelAndView bookView = new ModelAndView("book");
        bookView.addObject("book", book);
        return bookView;
    }

    @GetMapping
    public ModelAndView get(Model model) {
        System.out.println("BookController:GET:/book/");
        model.addAttribute("book", new Book());
        ModelAndView bookView = new ModelAndView("book_creation");
        return bookView;
    }

    @PostMapping
    public String create(@ModelAttribute Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        System.out.println("BookController:POST:/book/" + book);
        bookApi.addBook(book);
        return "book_creation_success";
    }

}
