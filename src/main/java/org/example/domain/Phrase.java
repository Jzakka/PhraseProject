package org.example.domain;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Phrase {
    private long id;
    private String content;
    private String author;

    public Phrase(long id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public JSONObject toJson(){
        Map<String, Object> phrase = new HashMap<>();
        phrase.put("id", id);
        phrase.put("content", content);
        phrase.put("author", author);
        return new JSONObject(phrase);
    }

    @Override
    public String toString() {
        return String.format("%d / %s / %s", id, author, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phrase phrase = (Phrase) o;
        return id == phrase.id && Objects.equals(content, phrase.content) && Objects.equals(author, phrase.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, author);
    }
}
