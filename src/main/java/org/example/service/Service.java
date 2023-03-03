package org.example.service;

import org.example.driver.JsonFileDriver;
import org.example.domain.Phrase;
import org.example.repository.JsonRepo;
import org.example.repository.Repository;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Service {
    private Repository repo = JsonRepo.getPhraseRepo();
    private JsonFileDriver jfd = new JsonFileDriver();

    public long register(List<String> contentAndAuthor) throws IOException {
        return repo.register(contentAndAuthor.get(0), contentAndAuthor.get(1));
    }

    public Map<Long, Phrase> list() throws IOException {
        return repo.list();
    }

    public void delete(Long id) throws IOException {
        repo.delete(id);
    }

    public Phrase findPhraseToBeUpdated(Long id) throws IOException {
        return repo.find(id);
    }

    public void update(long id, String newContent, String newAuthor) throws IOException {
        repo.update(id, newContent, newAuthor);
    }

    public void build() throws IOException {
        JSONArray jsonArray = new JSONArray();
        for (Map.Entry<Long, Phrase> e : repo.list().entrySet()) {
            jsonArray.add(e.getValue().toJson());
        }
        jfd.overWriteDate(jsonArray);
    }
}
