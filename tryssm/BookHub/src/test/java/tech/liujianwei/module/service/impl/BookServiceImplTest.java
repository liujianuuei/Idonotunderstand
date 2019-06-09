package tech.liujianwei.module.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tech.liujianwei.module.model.Book;
import tech.liujianwei.module.service.BookService;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class BookServiceImplTest {

    @Resource(name = "BookService")
    BookService bookService;

    @Test
    public void testGetBook() {
        //ApplicationContext applicationContext = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/applicationContext.xml");
        //BookService bookService = (BookService) applicationContext.getBean("BookService");
        Book book = bookService.getBook(1);
        System.out.println(book);
    }
}
