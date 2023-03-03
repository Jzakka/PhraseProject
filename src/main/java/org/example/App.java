package org.example;

import org.example.controller.Controller;
import org.example.view.View;

public class App {
    private Controller controller = new Controller();

    public void run(){
        View.header();

        String command;
        while (!(command = View.getCommand()).equals("종료")) {
            controller.execute(command);
        }

        View.getKeyboard().close();
    }
}
