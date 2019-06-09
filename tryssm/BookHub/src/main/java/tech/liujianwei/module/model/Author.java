package tech.liujianwei.module.model;

public class Author {
    private int id;
    private String name;
    private String nationality;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Author setName(String name) {
        this.name = name;
        return this;
    }

    public String getNationality() {
        return nationality;
    }

    public Author setNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    @Override
    public String toString() {
        return id + ". " + name + "[" + nationality + "]";
    }
}
