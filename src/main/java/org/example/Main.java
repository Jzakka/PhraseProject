package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Service service = new Service();
    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");

        String command;
        while (!(command = getCommand()).equals("종료")) {
            if (command.equals("등록")) {
                service.register();
            } else if (command.equals("목록")) {
                service.list();
            } else if (command.contains("삭제")) {
                service.delete(parseId(command));
            }
        }
    }

    private static String getCommand() {
        System.out.print("명령) ");
        return Input.getKeyboard().nextLine();
    }

    private static Long parseId(String command){
        int start = command.indexOf("id=") + 3;
        return Long.valueOf(command.substring(start));
    }
}