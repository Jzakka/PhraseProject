package org.example;

import java.util.Map;

public interface Repository {
    long register(String content, String authorName);

    void init();

    Phrase find(Long id);
    Map<Long, Phrase> list();

    boolean delete(long id);

    boolean update(long i, String updated_content, String mola);
}
