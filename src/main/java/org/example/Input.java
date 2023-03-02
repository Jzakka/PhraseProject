package org.example;

import java.util.Scanner;

public class Input {
    private static Scanner sc = new Scanner(System.in);

    public Input() {}

    public static Scanner getKeyboard() {
        return sc;
    }
}
