package tech.liujianwei.module.api;

import org.springframework.stereotype.Service;
import tech.liujianwei.module.model.Book;
import tech.liujianwei.module.service.BookService;

import javax.annotation.Resource;

@Service("BookApi")
public class BookApi {

    @Resource(name = "BookService")
    BookService bookService;

    public Book getBook(int id) {
        return bookService.getBook(id);
    }

    public void addBook(Book book) {
        bookService.addBook(book);
    }
}
