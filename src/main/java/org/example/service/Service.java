package org.example.service;

import org.example.Input;
import org.example.domain.Phrase;
import org.example.repository.JsonRepo;
import org.example.repository.Repository;

import java.io.IOException;
import java.util.Map;

public class Service {
    private Repository repo = JsonRepo.getPhraseRepo();

    public void register() throws IOException {
        System.out.print("명언 : ");
        String content = Input.getKeyboard().nextLine();
        System.out.print("작가 : ");
        String author = Input.getKeyboard().nextLine();

        System.out.printf("%d번 명언이 등록되었습니다.%n", repo.register(content, author));
    }

    public void list() throws IOException {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("---------------------");
        Map<Long, Phrase> phrases = repo.list();
        phrases.forEach((i,p)-> System.out.println(p));
    }

    public void delete(Long id) throws IOException {
        if(repo.delete(id)){
            System.out.printf("%d번 명언이 삭제되었습니다.%n", id);
            return;
        }
        System.out.printf("%d번 명언은 존재하지 않습니다.%n", id);
    }

    public void update(Long id) throws IOException {
        System.out.printf("명언(기존) : %s%n", repo.find(id).getContent());
        System.out.print("명언 : ");
        String newContent = Input.getKeyboard().nextLine();
        System.out.printf("작가(기존) : %s%n", repo.find(id).getAuthor());
        System.out.print("작가 : ");
        String newAuthor = Input.getKeyboard().nextLine();

        repo.update(id, newContent, newAuthor);
    }
}
