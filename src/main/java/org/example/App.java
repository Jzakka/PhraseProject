package org.example;

import org.example.Controller.Controller;

import java.io.IOException;

public class App {
    private Controller controller = new Controller();

    public void run() throws IOException {
        System.out.println("== 명언 앱 ==");

        String command;
        while (!(command = getCommand()).equals("종료")) {
            controller.excute(command);
        }

        Input.getKeyboard().close();
    }

    private String getCommand() {
        System.out.print("명령) ");
        return Input.getKeyboard().nextLine();
    }
}
