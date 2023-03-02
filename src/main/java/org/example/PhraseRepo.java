package org.example;

public class PhraseRepo {
    private static long nextId = 1;

    private PhraseRepo() {}

    public static PhraseRepo getPhraseRepo(){
        return new PhraseRepo();
    }

    public void register(String content, String authorName) {
        // TODO: Create and register Phrase

        System.out.printf("%d번 명언이 등록되었습니다.%n", nextId++);
    }
}
