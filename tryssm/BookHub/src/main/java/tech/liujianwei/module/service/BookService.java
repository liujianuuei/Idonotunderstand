package tech.liujianwei.module.service;

import tech.liujianwei.module.model.Book;

public interface BookService {
    Book getBook(int id);

    void addBook(Book book);
}