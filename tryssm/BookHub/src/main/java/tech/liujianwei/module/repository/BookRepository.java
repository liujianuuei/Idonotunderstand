package tech.liujianwei.module.repository;

import org.springframework.stereotype.Repository;
import tech.liujianwei.module.model.Book;

@Repository
public interface BookRepository {
    Book getBook(int id);

    void addBook(Book book);
}
