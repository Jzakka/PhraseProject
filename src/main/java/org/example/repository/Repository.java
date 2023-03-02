package org.example.repository;

import org.example.domain.Phrase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface Repository {
    long register(String content, String authorName) throws IOException;

    void init();

    long getNextId();
    Phrase find(Long id) throws IOException;
    Map<Long, Phrase> list() throws IOException;

    void delete(long id) throws IOException;

    void update(long i, String updated_content, String mola) throws IOException;
}
