package chucknorris;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Input encoded string:");
        Scanner scanner = new Scanner(System.in);
        String encoded = scanner.nextLine().trim();

        // 1) Parse unary blocks -> reconstruct the binary bitstream
        String[] blocks = encoded.split("\\s+"); // split by spaces
        StringBuilder bits = new StringBuilder();

        for (int i = 0; i < blocks.length; i += 2) {
            String header = blocks[i];          // "0" -> series of 1s, "00" -> series of 0s
            String zeros = blocks[i + 1];       // its length is the run length

            char bit = header.equals("0") ? '1' : '0';
            int runLen = zeros.length();

            for (int k = 0; k < runLen; k++) {
                bits.append(bit);
            }
        }

        // 2) Split into 7-bit groups and turn into characters
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < bits.length(); i += 7) {
            String chunk = bits.substring(i, i + 7);
            int code = Integer.parseInt(chunk, 2);
            result.append((char) code);
        }

        System.out.println("The result:");
        System.out.println(result.toString());
    }
}
