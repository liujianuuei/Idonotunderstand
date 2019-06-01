package tech.liujianwei.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import tech.liujianwei.model.Book;
import tech.liujianwei.service.BookService;

public class BookServiceImplTest {

    @Test
    public void testGetBook() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = (BookService) applicationContext.getBean("bookServiceImpl");
        Book book = bookService.getBook(1);
        System.out.println(book);
    }
}
