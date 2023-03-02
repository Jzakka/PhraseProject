package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MemoryRepo implements Repository {
    private static long nextId = 1;

    private Map<Long, Phrase> datum = new HashMap<>();

    private MemoryRepo() {}

    public static MemoryRepo getPhraseRepo(){
        return new MemoryRepo();
    }

    public static long getNextId() {
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
    public Map<Long, Phrase> list() {
        return datum;
    }

    @Override
    public boolean delete(long id) {
        try {
            datum.remove(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
