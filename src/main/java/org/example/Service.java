package org.example;

import java.util.ArrayList;
import java.util.Map;

public class Service {
    private Repository repo = MemoryRepo.getPhraseRepo();

    public void register(){
        System.out.print("명언 : ");
        String content = Input.getKeyboard().nextLine();
        System.out.print("작가 : ");
        String author = Input.getKeyboard().nextLine();

        System.out.printf("%d번 명언이 등록되었습니다.%n", repo.register(content, author));
    }

    public void list(){
        System.out.println("번호 / 작가 / 명언");
        System.out.println("---------------------");
        Map<Long, Phrase> phrases = repo.list();
        for (long i = phrases.size() - 1; i >= 0; i--) {
            System.out.println(phrases.get((int) i));
        }
    }
}
