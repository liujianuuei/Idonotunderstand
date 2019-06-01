package tech.liujianwei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import tech.liujianwei.model.Book;
import tech.liujianwei.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable String id) {
        System.out.println("BookController:/book/" + id);
        Book book = bookService.getBook(Integer.valueOf(id));
        // Can also use Model+"book" instead of ModelAndView
        ModelAndView bookView = new ModelAndView("book");
        bookView.addObject("book", book);
        return bookView;
    }

}
