package org.example;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");

        String command;
        while (!(command = getCommand()).equals("종료")) {
            if (command.equals("등록")) {
                register();
            }
        }
    }

    static String getCommand() {
        System.out.print("명령) ");
        return sc.nextLine();
    }

    static void register(){
        System.out.print("명언 : ");
        sc.nextLine();
        System.out.print("작가 : ");
        sc.nextLine();
    }
}