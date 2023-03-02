package org.example;

import java.util.Map;

public interface Repository {
    long register(String content, String authorName);

    void init();

    Map<Long, Phrase> list();

    boolean delete(long id);
}
