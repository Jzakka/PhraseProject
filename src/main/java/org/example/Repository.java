package org.example;

import java.util.ArrayList;

public interface Repository {
    long register(String content, String authorName);

    ArrayList<Phrase> listPhrases();
}
