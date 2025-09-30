package chucknorris;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Input string:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        System.out.println("The result:");
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int ascii = (int) c;
            String binary = String.format("%7s", Integer.toBinaryString(ascii)).replace(' ', '0');
            System.out.println(c + " = " + binary);
        }
    }
}
