package tech.liujianwei.module.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Book {
    private int id;
    private String name;
    private List<Author> authors;

    public Book() {
        this.authors = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Book setName(String name) {
        this.name = name;
        return this;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public Book setAuthors(List<Author> authors) {
        this.authors = authors;
        return this;
    }

    public Book addAuthor(Author author) {
        if (this.authors == null) {
            this.authors = new ArrayList<>();
        }
        this.authors.add(author);
        return this;
    }

    @Override
    public String toString() {
        return id + ". " + name + " - " + authors.stream().map(e -> e.toString()).collect(Collectors.joining(", "));
    }
}
