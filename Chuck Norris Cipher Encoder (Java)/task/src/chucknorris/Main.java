package chucknorris;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Input string:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Step 1: convert whole string to binary (7-bit each char)
        StringBuilder binaryMessage = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            String binary = String.format("%7s", Integer.toBinaryString(c)).replace(' ', '0');
            binaryMessage.append(binary);
        }

        // Step 2: Encode using Chuck Norris unary code
        StringBuilder result = new StringBuilder();
        char prevBit = binaryMessage.charAt(0);
        result.append(prevBit == '1' ? "0 " : "00 ");
        result.append("0");

        for (int i = 1; i < binaryMessage.length(); i++) {
            char currentBit = binaryMessage.charAt(i);
            if (currentBit == prevBit) {
                // Continue the same sequence → add another "0"
                result.append("0");
            } else {
                // New sequence → add a space + new block
                result.append(" ");
                result.append(currentBit == '1' ? "0 " : "00 ");
                result.append("0");
            }
            prevBit = currentBit;
        }

        // Output
        System.out.println("The result:");
        System.out.println(result.toString());
    }
}
