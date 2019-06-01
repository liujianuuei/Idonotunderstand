package tech.liujianwei.service;

import org.springframework.stereotype.Service;
import tech.liujianwei.model.Book;

public interface BookService {
    Book getBook(int id);
}