package org.example.service;

import org.example.Input;
import org.example.repository.JsonFileDriver;
import org.example.domain.Phrase;
import org.example.repository.JsonRepo;
import org.example.repository.Repository;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.Map;

public class Service {
    private Repository repo = JsonRepo.getPhraseRepo();
    private JsonFileDriver jfd = new JsonFileDriver();

    public void register() throws IOException {
        System.out.print("명언 : ");
        String content = Input.getKeyboard().nextLine();
        System.out.print("작가 : ");
        String author = Input.getKeyboard().nextLine();

        long registeredId = repo.register(content, author);
        System.out.printf("%d번 명언이 등록되었습니다.%n", registeredId);
    }

    public void list() throws IOException {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("---------------------");
        Map<Long, Phrase> phrases = repo.list();
        phrases.forEach((i,p)-> System.out.println(p));
    }

    public void delete(Long id) throws IOException {
        repo.delete(id);
        System.out.printf("%d번 명언이 삭제외었습니다.", id);
    }

    public void update(Long id) throws IOException {
        Phrase phrase = repo.find(id);
        System.out.printf("명언(기존) : %s%n", phrase.getContent());
        System.out.print("명언 : ");
        String newContent = Input.getKeyboard().nextLine();
        System.out.printf("작가(기존) : %s%n", phrase.getAuthor());
        System.out.print("작가 : ");
        String newAuthor = Input.getKeyboard().nextLine();

        repo.update(id, newContent, newAuthor);
    }

    public void build() throws IOException {
        JSONArray jsonArray = new JSONArray();
        for (Map.Entry<Long, Phrase> e : repo.list().entrySet()) {
            jsonArray.add(e.getValue().toJson());
        }
        jfd.overWriteDate(jsonArray);

        System.out.println("data.json 파일의 빌드가 완료되었습니다.");
    }
}
