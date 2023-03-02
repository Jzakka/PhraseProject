package org.example.controller;

import org.example.Validator;
import org.example.service.Service;

import java.io.IOException;

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
    private Long parseId(String request){
        int start = request.indexOf("id=") + 3;
        long parsedId = Long.parseLong(request.substring(start));
        validator.validateId(parsedId);
        return parsedId;
    }

    private String parseAction(String request){
        return request.split("\\?")[0];
    }

    private void dispatch(String request) throws IOException {
        String action = parseAction(request);

        switch(action) {
            case "등록":
                service.register();
                break;
            case "목록":
                service.list();
                break;
            case "삭제":
                service.delete(parseId(request));
                break;
            case "수정":
                service.update(parseId(request));
                break;
            case "빌드":
                service.build();
                break;
        }
    }
}
