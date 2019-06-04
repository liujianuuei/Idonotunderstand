package tech.liujianwei.service.impl;

import org.springframework.stereotype.Service;
import tech.liujianwei.repository.BookRepository;
import tech.liujianwei.model.Book;
import tech.liujianwei.service.BookService;

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
