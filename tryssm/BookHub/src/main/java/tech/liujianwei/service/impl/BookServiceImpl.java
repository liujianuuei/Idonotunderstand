package tech.liujianwei.service.impl;

import org.springframework.stereotype.Service;
import tech.liujianwei.model.Author;
import tech.liujianwei.model.Book;
import tech.liujianwei.service.BookService;

@Service
public class BookServiceImpl implements BookService {
    @Override
    public Book getBook(int id) {
        if (id == 1) {
            Author author = new Author().setName("Craig Walls").setNationality("US");
            Book book = new Book().setName("Spring in Action").setAuthor(author);
            return book;
        } else {
            Author author = new Author().setName("Joshua Bloch").setNationality("US");
            Book book = new Book().setName("Effective Java").setAuthor(author);
            return book;
        }
    }
}
