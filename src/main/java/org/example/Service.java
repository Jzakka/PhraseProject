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
        phrases.forEach((i,p)-> System.out.println(p));
    }

    public void delete(Long id){
        if(repo.delete(id)){
            System.out.printf("%d번 명언이 삭제되었습니다.%n", id);
            return;
        }
        System.out.printf("%d번 명언은 존재하지 않습니다.%n", id);
    }
}
