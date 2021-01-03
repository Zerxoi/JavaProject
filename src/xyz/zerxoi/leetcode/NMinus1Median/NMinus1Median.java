package xyz.zerxoi.leetcode.NMinus1Median;

import java.util.Arrays;
import java.util.Scanner;

public class NMinus1Median {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        // arr 是要排序的数组
        // tmp 是原数组
        // 起初两者是一样的
        int[] arr = new int[n];
        int[] tmp = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
            tmp[i] = arr[i];
        }

        input.close();

        // 对arr 排序，中位数一定在arr[n/2-1]和arr[n/2]之间
        Arrays.sort(arr);

        // 如果要移除的元素小于或者等于arr[n/2-1]则返回arr[n/2]
        // 否则返回arr[n/2-1]
        for (int i = 0; i < n; i++)
            if (tmp[i] <= arr[n / 2 - 1])
                System.out.println(arr[n / 2]);
            else
                System.out.println(arr[n / 2 - 1]);
    }
}