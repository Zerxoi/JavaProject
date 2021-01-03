package xyz.zerxoi;

import java.util.Scanner;

public class Solution1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int m = input.nextInt();
        int n = input.nextInt();
        input.close();
        int count = 0; // 计数器
        for (int i = m; i <= n; i++) {
            int tmp = i;
            boolean[] flags = new boolean[10]; // 看有无重复数
            int[] num = new int[6]; // 记录各位数字
            boolean ok = true; // 是否无重复
            for (int k = 5; k >= 0; k--) {
                num[k] = tmp % 10;
                if (flags[num[k]]) {
                    ok = false;
                    break;
                }
                flags[num[k]] = true;
                tmp /= 10;
            }
            if (ok && num[0] * 10 + num[1] + num[2] * 10 + num[3] == num[4] * 10 + num[5]) {
                count++;
            }
        }
        System.out.println(count);
    }
}
