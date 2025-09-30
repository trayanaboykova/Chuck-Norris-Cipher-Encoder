package chucknorris;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Input string:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            result.append(input.charAt(i));
            if (i < input.length() - 1) {
                result.append(' ');
            }
        }

        System.out.println();
        System.out.println(result.toString());
    }
}
