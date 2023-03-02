package org.example;

public class Phrase {
    private long id;
    private String content;
    private String author;

    public Phrase(long id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    @Override
    public String toString() {
        return String.format("%d / %s / %s", id, author, content);
    }
}
