package tech.liujianwei.model;

public class Book {
    private int id;
    private String name;
    private Author author;

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

    public Author getAuthor() {
        return author;
    }

    public Book setAuthor(Author author) {
        this.author = author;
        return this;
    }

    @Override
    public String toString() {
        return id + ". " + name + " - " + author;
    }
}
