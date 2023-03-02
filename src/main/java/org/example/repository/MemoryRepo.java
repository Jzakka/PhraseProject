package org.example.repository;

import org.example.domain.Phrase;

import java.util.*;

public class MemoryRepo implements Repository {
    private long nextId = 1;
    private static MemoryRepo memoryRepo = new MemoryRepo();

    private Map<Long, Phrase> datum = new TreeMap<>(Collections.reverseOrder());

    private MemoryRepo() {}

    public static MemoryRepo getPhraseRepo(){
        return memoryRepo;
    }

    @Override
    public long getNextId() {
        return nextId;
    }

    public void init() {
        nextId = 1;
        datum.clear();
    }

    @Override
    public long register(String content, String authorName) {
        // TODO: Create and register Phrase
        datum.put(nextId, new Phrase(nextId, content, authorName));
        return nextId++;
    }

    @Override
    public Phrase find(Long id) {
        return datum.get(id);
    }

    @Override
    public Map<Long, Phrase> list() {
        return datum;
    }

    @Override
    public void delete(long id) {
        datum.remove(id);
    }

    @Override
    public void update(long id, String updatedContent, String authorName) {
        datum.put(id, new Phrase(id, updatedContent, authorName));
    }
}
