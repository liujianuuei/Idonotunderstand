package tech.liujianwei.repository;

import org.springframework.stereotype.Repository;
import tech.liujianwei.model.Book;

@Repository
public interface BookRepository {
    Book getBook(int id);

    void addBook(Book book);
}
