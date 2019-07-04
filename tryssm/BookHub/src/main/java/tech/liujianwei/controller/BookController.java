package tech.liujianwei.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import tech.liujianwei.module.api.BookApi;
import tech.liujianwei.module.model.Book;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/book")
public class BookController {

    @Resource(name="BookApi")
    BookApi bookApi;

    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable String id) {
        System.out.println("BookController:GET:/book/" + id);
        Book book = bookApi.getBook(Integer.valueOf(id));
        // Can also use Model+"book" instead of ModelAndView
        ModelAndView bookView = new ModelAndView("book");
        bookView.addObject("book", book);
        return bookView;
    }

    @GetMapping
    public ModelAndView get(Model model) {
        System.out.println("BookController:GET:/book/");
        model.addAttribute("book", new Book());
        ModelAndView bookView = new ModelAndView("book_creation");
        return bookView;
    }

    @PostMapping
    public String create(@ModelAttribute Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        System.out.println("BookController:POST:/book/" + book);
        bookApi.addBook(book);
        return "book_creation_success";
    }

    @GetMapping("/batch")
    public ModelAndView tryUploadBooks() {
        System.out.println("BookController:GET:/book/batch/");
        ModelAndView bookView = new ModelAndView("book_batch_upload");
        return bookView;
    }

    @PostMapping("/batch")
    public String uploadBooks(MultipartFile file) throws IOException {
        File localFile = new File(file.getOriginalFilename());
        file.transferTo(localFile);
        System.out.println(FileUtils.readFileToString(localFile));
        return "book_creation_success";
    }

}
