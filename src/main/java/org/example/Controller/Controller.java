package org.example.Controller;

import org.example.Validator;
import org.example.service.Service;

import java.io.IOException;
import java.util.NoSuchElementException;

public class Controller {

    private Service service = new Service();
    private Validator validator = new Validator();

    public void excute(String request) throws IOException {
        try {
            if (request.equals("등록")) {
                service.register();
            } else if (request.equals("목록")) {
                service.list();
            } else if (request.contains("삭제")) {
                service.delete(parseId(request));
            } else if (request.contains("수정")) {
                service.update(parseId(request));
            } else if (request.contains("빌드")) {
                service.build();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private Long parseId(String request){
        int start = request.indexOf("id=") + 3;
        long parsedId = Long.valueOf(request.substring(start));
        validator.validateId(parsedId);
        return parsedId;
    }
}
