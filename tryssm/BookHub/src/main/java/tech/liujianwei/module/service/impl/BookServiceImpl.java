package tech.liujianwei.module.service.impl;

import org.springframework.stereotype.Service;
import tech.liujianwei.module.repository.BookRepository;
import tech.liujianwei.module.model.Book;
import tech.liujianwei.module.service.BookService;

import javax.annotation.Resource;

@Service("BookService")
public class BookServiceImpl implements BookService {

    @Resource
    private BookRepository bookRepository;

    @Override
    public Book getBook(int id) {
        return bookRepository.getBook(id);
    }

    @Override
    public void addBook(Book book){
        bookRepository.addBook(book);
    }
}
