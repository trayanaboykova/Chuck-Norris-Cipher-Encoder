package chucknorris;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please input operation (encode/decode/exit):");
            String operation = scanner.nextLine().trim();

            if (operation.equals("exit")) {
                System.out.println("Bye!");
                break;
            } else if (operation.equals("encode")) {
                System.out.println("Input string:");
                String input = scanner.nextLine();
                String encoded = encode(input);
                System.out.println("Encoded string:");
                System.out.println(encoded);
            } else if (operation.equals("decode")) {
                System.out.println("Input encoded string:");
                String encoded = scanner.nextLine();
                if (!isValidEncoded(encoded)) {
                    System.out.println("Encoded string is not valid.");
                } else {
                    String decoded = decode(encoded);
                    System.out.println("Decoded string:");
                    System.out.println(decoded);
                }
            } else {
                System.out.printf("There is no '%s' operation%n", operation);
            }
        }
    }

    // --- ENCODER ---
    private static String encode(String input) {
        StringBuilder binaryMessage = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            String binary = String.format("%7s", Integer.toBinaryString(input.charAt(i))).replace(' ', '0');
            binaryMessage.append(binary);
        }

        StringBuilder result = new StringBuilder();
        char prevBit = binaryMessage.charAt(0);
        result.append(prevBit == '1' ? "0 " : "00 ");
        result.append("0");

        for (int i = 1; i < binaryMessage.length(); i++) {
            char currentBit = binaryMessage.charAt(i);
            if (currentBit == prevBit) {
                result.append("0");
            } else {
                result.append(" ");
                result.append(currentBit == '1' ? "0 " : "00 ");
                result.append("0");
            }
            prevBit = currentBit;
        }
        return result.toString();
    }

    // --- DECODER ---
    private static String decode(String encoded) {
        String[] blocks = encoded.split("\\s+");
        StringBuilder bits = new StringBuilder();

        for (int i = 0; i < blocks.length; i += 2) {
            String header = blocks[i];
            String zeros = blocks[i + 1];

            char bit = header.equals("0") ? '1' : '0';
            int runLen = zeros.length();

            for (int k = 0; k < runLen; k++) {
                bits.append(bit);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < bits.length(); i += 7) {
            String chunk = bits.substring(i, i + 7);
            int code = Integer.parseInt(chunk, 2);
            result.append((char) code);
        }
        return result.toString();
    }

    // --- VALIDATION ---
    private static boolean isValidEncoded(String encoded) {
        if (!encoded.matches("[0 ]+")) {
            return false; // only 0s and spaces allowed
        }
        String[] blocks = encoded.trim().split("\\s+");
        if (blocks.length % 2 != 0) {
            return false; // must be even number of blocks
        }
        for (int i = 0; i < blocks.length; i += 2) {
            if (!(blocks[i].equals("0") || blocks[i].equals("00"))) {
                return false; // invalid header
            }
        }
        // build binary string
        StringBuilder bits = new StringBuilder();
        for (int i = 0; i < blocks.length; i += 2) {
            String header = blocks[i];
            String zeros = blocks[i + 1];
            char bit = header.equals("0") ? '1' : '0';
            bits.append(String.valueOf(bit).repeat(zeros.length()));
        }
        return bits.length() % 7 == 0;
    }
}
