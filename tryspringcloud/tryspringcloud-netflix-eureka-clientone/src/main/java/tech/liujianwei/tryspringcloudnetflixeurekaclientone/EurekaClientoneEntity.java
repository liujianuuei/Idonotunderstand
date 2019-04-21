package tech.liujianwei.tryspringcloudnetflixeurekaclientone;

public class EurekaClientoneEntity {

    private final long id;
    private final String content;

    public EurekaClientoneEntity(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
