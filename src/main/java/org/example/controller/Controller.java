package org.example.controller;

import org.example.View;
import org.example.domain.Phrase;
import org.example.service.Service;

import java.io.IOException;
import java.util.Map;

public class Controller {

    private Service service = new Service();

    public void execute(String request) {
        try {
            dispatch(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void dispatch(String request) throws IOException {
        String action = parseAction(request);

        switch (action) {
            case "등록":
                register();
                break;
            case "목록":
                list();
                break;
            case "삭제":
                long toBeDeletedId = delete(request);
                View.deleteComplete(toBeDeletedId);
                break;
            case "수정":
                long toBeUpdatedId = parseId(request);
                Phrase phraseToBeUpdated = getPhraseToBeUpdated(toBeUpdatedId, request);
                update(toBeUpdatedId, phraseToBeUpdated);
                break;
            case "빌드":
                build();
                break;
        }
    }

    private String parseAction(String request) {
        return request.split("\\?")[0];
    }

    private Long parseId(String request) {
        int start = request.indexOf("id=") + 3;
        long parsedId = Long.parseLong(request.substring(start));
        return parsedId;
    }

    private void register() throws IOException {
        long registeredId = service.register(View.registerInput());
        View.registerComplete(registeredId);
    }

    private void list() throws IOException {
        Map<Long, Phrase> phrases = service.list();
        View.listPhrases(phrases);
    }

    private long delete(String request) throws IOException {
        long toBeDeletedId = parseId(request);
        service.delete(toBeDeletedId);
        return toBeDeletedId;
    }

    private Phrase getPhraseToBeUpdated(long toBeUpdatedId, String request) throws IOException {
        return service.findPhraseToBeUpdated(toBeUpdatedId);
    }

    private void update(long toBeUpdatedId, Phrase phraseToBeUpdated) throws IOException {
        String newContent = View.newContent(phraseToBeUpdated.getContent());
        String newAuthor = View.newAuthor(phraseToBeUpdated.getAuthor());
        service.update(toBeUpdatedId, newContent, newAuthor);
    }

    private void build() throws IOException {
        service.build();
        View.buildComplete();
    }
}
