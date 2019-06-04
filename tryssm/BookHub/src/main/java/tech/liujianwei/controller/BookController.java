package tech.liujianwei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tech.liujianwei.model.Book;
import tech.liujianwei.service.BookService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/book")
public class BookController {

    @Resource(name="BookService")
    BookService bookService;

    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable String id) {
        System.out.println("BookController:GET:/book/" + id);
        Book book = bookService.getBook(Integer.valueOf(id));
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
        return "book_creation_success";
    }

}
