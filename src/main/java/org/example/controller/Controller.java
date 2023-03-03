package org.example.controller;

import org.example.Validator;
import org.example.View;
import org.example.domain.Phrase;
import org.example.service.Service;

import java.io.IOException;
import java.util.Map;

public class Controller {

    private Service service = new Service();
    private Validator validator = new Validator();

    public void execute(String request) {
        try {
            dispatch(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Long parseId(String request) {
        int start = request.indexOf("id=") + 3;
        long parsedId = Long.parseLong(request.substring(start));
        validator.validateId(parsedId);
        return parsedId;
    }

    private String parseAction(String request) {
        return request.split("\\?")[0];
    }

    private void dispatch(String request) throws IOException {
        String action = parseAction(request);

        switch (action) {
            case "등록":
                long registeredId = service.register(View.registerInput());
                View.registerComplete(registeredId);
                break;
            case "목록":
                Map<Long, Phrase> phrases = service.list();
                View.listPhrases(phrases);
                break;
            case "삭제":
                long toBeDeletedId = parseId(request);
                service.delete(toBeDeletedId);
                View.deleteComplete(toBeDeletedId);
                break;
            case "수정":
                long toBeUpdatedId = parseId(request);
                Phrase phraseToBeUpdated = service.findPhraseToBeUpdated(toBeUpdatedId);

                String newContent = View.newContent(phraseToBeUpdated.getContent());
                String newAuthor = View.newAuthor(phraseToBeUpdated.getAuthor());
                service.update(toBeUpdatedId, newContent, newAuthor);
                break;
            case "빌드":
                service.build();
                View.buildComplete();
                break;
        }
    }
}
