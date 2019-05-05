package forkjoin;

public class Update implements Comparable<Update> {
    private final String author;
    private final String text;
    private final long createTime;


    private Update(Builder b) {
        this.author = b.author;
        this.text = b.text;
        this.createTime = b.createTime;
    }

    @Override
    public int compareTo(Update other) {
        return (int) (this.createTime - other.createTime);
    }

    @Override
    public String toString() {
        return author + ":" + text + ":" + createTime;
    }

    public static class Builder {
        private String author;
        private String text;
        private long createTime;

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder updateText(String text) {
            this.text = text;
            return this;
        }

        public Builder createTime(long now) {
            this.createTime = now;
            return this;
        }

        public Update build() {
            return new Update(this);
        }
    }
}
