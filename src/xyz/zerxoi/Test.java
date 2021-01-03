package xyz.zerxoi;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        String s = input.nextLine();
        input.close();
        System.out.println(reverseString(s, n));
    }

    static String reverseString(String s, int n) {
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i += n) {
            int j = i + n - 1;
            if (j > len - 1)
                j = len - 1;
            while (j >= i)
                sb.append(s.charAt(j--));
        }
        return sb.toString();
    }
}
