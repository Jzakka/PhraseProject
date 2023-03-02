package org.example.repository;

import org.example.domain.Phrase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface Repository {
    long register(String content, String authorName) throws IOException;

    void init();

    Phrase find(Long id) throws FileNotFoundException, IOException;
    Map<Long, Phrase> list() throws IOException;

    boolean delete(long id) throws FileNotFoundException, IOException;

    boolean update(long i, String updated_content, String mola) throws FileNotFoundException, IOException;
}
