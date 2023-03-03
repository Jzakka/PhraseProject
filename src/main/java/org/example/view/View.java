package org.example.view;

import org.example.domain.Phrase;

import java.util.*;

public class View {
    private static Scanner sc = new Scanner(System.in);

    private View() {}

    public static Scanner getKeyboard() {
        return sc;
    }

    public static List<String> registerInput() {
        System.out.print("명언 : ");
        String content = View.getKeyboard().nextLine();
        System.out.print("작가 : ");
        String author = View.getKeyboard().nextLine();

        return Arrays.asList(content, author);
    }

    public static void registerComplete(long registeredId) {
        System.out.printf("%d번 명언이 등록되었습니다.%n", registeredId);
    }

    public static void listPhrases(Map<Long, Phrase> phrases) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("---------------------");
        phrases.forEach((i,p)-> System.out.println(p));
    }

    public static void deleteComplete(long deletedId) {
        System.out.printf("%d번 명언이 삭제되었습니다.%n", deletedId);
    }

    public static String newContent(String originalContent) {
        System.out.printf("명언(기존) : %s%n", originalContent);
        System.out.print("명언 : ");
        return getKeyboard().nextLine();

    }

    public static String newAuthor(String originalAuthor) {
        System.out.printf("작가(기존) : %s%n", originalAuthor);
        System.out.print("작가 : ");
        return getKeyboard().nextLine();
    }

    public static void buildComplete() {
        System.out.println("data.json 파일의 빌드가 완료되었습니다.");
    }

    public static void header() {
        System.out.println("== 명언 앱 ==");
    }

    public static String getCommand() {
        System.out.print("명령) ");
        return getKeyboard().nextLine();
    }
}
