package org.example;

import java.util.ArrayList;

public class MemoryRepo implements Repository {
    private static long nextId = 1;

    private ArrayList<Phrase> datum = new ArrayList<>();

    private MemoryRepo() {}

    public static MemoryRepo getPhraseRepo(){
        return new MemoryRepo();
    }

    @Override
    public long register(String content, String authorName) {
        // TODO: Create and register Phrase
        datum.add(new Phrase(nextId, content, authorName));
        return nextId++;
    }

    @Override
    public ArrayList<Phrase> listPhrases() {
        return datum;
    }
}
