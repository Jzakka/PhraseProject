package org.example;

import org.example.controller.Controller;

import java.io.IOException;

public class App {
    private Controller controller = new Controller();

    public void run(){
        System.out.println("== 명언 앱 ==");

        String command;
        while (!(command = getCommand()).equals("종료")) {
            controller.execute(command);
        }

        Input.getKeyboard().close();
    }

    private String getCommand() {
        System.out.print("명령) ");
        return Input.getKeyboard().nextLine();
    }
}
