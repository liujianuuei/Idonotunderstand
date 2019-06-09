package tech.liujianwei.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tech.liujianwei.module.api.BookApi;
import tech.liujianwei.module.model.Book;

import javax.annotation.Resource;

@Controller
@RequestMapping("/api/book")
public class BookRestController {

    @Resource(name="BookApi")
    BookApi bookApi;

    @GetMapping("/{id}")
    @ResponseBody
    public Book get(@PathVariable String id) {
        System.out.println("BookRestController:GET:/api/book/" + id);
        Book book = bookApi.getBook(Integer.valueOf(id));
        return book;
    }
}
