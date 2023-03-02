package org.example;

import org.example.service.Service;

import java.io.IOException;

public class App {
    private Service service = new Service();

    public void run() throws IOException {
        System.out.println("== 명언 앱 ==");

        String command;
        while (!(command = getCommand()).equals("종료")) {
            if (command.equals("등록")) {
                service.register();
            } else if (command.equals("목록")) {
                service.list();
            } else if (command.contains("삭제")) {
                service.delete(parseId(command));
            } else if (command.contains("수정")) {
                service.update(parseId(command));
            }
        }
    }

    private String getCommand() {
        System.out.print("명령) ");
        return Input.getKeyboard().nextLine();
    }

    private Long parseId(String command){
        int start = command.indexOf("id=") + 3;
        return Long.valueOf(command.substring(start));
    }
}
