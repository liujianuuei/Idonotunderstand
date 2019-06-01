package tech.liujianwei.model;

public class Author {
    private String name;
    private String nationality;

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
        return name + "[" + nationality + "]";
    }
}
