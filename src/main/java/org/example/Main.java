package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static Repository repo = MemoryRepo.getPhraseRepo();
    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");

        String command;
        while (!(command = getCommand()).equals("종료")) {
            if (command.equals("등록")) {
                register();
            } else if (command.equals("목록")) {
                list();
            }
        }
    }

    static String getCommand() {
        System.out.print("명령) ");
        return sc.nextLine();
    }

    static void register(){
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        System.out.printf("%d번 명언이 등록되었습니다.%n", repo.register(content, author));
    }

    static void list(){
        System.out.println("번호 / 작가 / 명언");
        System.out.println("---------------------");
        ArrayList<Phrase> phrases = repo.listPhrases();
        for (long i = phrases.size() - 1; i >= 0; i--) {
            System.out.println(phrases.get((int) i));
        }
    }
}