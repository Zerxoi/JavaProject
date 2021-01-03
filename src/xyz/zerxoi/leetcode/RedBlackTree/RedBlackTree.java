package xyz.zerxoi.leetcode.RedBlackTree;

import java.util.Scanner;

public class RedBlackTree {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        n*=2;
        String rb = input.next();

        char[] colors = rb.toCharArray();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }

        int sum = 0;
        for (int i = 1; i < n; i++) {
            char color = colors[i];
            int tmp = arr[i];
            int p = 0;
            int q = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (color != colors[j]) {
                    p++;
                } else {
                    if (arr[j] > arr[i]) {
                        p++;
                        q = p;
                    } else
                        break;
                }
            }
            int k = 0;
            while (k < q) {
                arr[i - k] = arr[i - 1 - k];
                colors[i - k] = colors[i - 1 - k];
                k++;
            }
            arr[i - k] = tmp;
            colors[i-k] = color;

            System.out.println("==================");
            for(int z = 0; z< n; z++) {
                System.out.print(arr[z]);
            }
            System.out.println();
            for(int z = 0; z< n; z++) {
                System.out.print(colors[z]);
            }
            System.out.println("交换"+q+"次");
            System.out.println("==================");

            sum += q;
        }
        System.out.println(sum);
        input.close();
    }
}
