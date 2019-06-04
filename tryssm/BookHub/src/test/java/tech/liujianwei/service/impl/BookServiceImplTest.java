package tech.liujianwei.service.impl;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import tech.liujianwei.model.Book;
import tech.liujianwei.service.BookService;

public class BookServiceImplTest {

    @Test
    public void testGetBook() {
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/applicationContext.xml");
        BookService bookService = (BookService) applicationContext.getBean("BookService");
        Book book = bookService.getBook(1);
        System.out.println(book);
    }
}
