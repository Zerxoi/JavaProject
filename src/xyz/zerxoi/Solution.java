package xyz.zerxoi;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();

        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                int j = i;
                int num = 0;
                while (j < line.length() && Character.isDigit(line.charAt(j))) {
                    num = num * 10 + line.charAt(j) - '0';
                    j++;
                }
                i = j;
                if (num >= 1000 && num <= 3999) {
                    System.out.print(num + " ");
                }
            }
        }
        input.close();
    }

}
