package xyz.zerxoi.leetcode.dynamicprogramming;

import java.util.Scanner;

public class SkiDemo {
    static int n;
    static int m;
    static int[][] arr;
    static int[][] cache;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        m = input.nextInt();
        arr = new int[n][m];
        cache = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = input.nextInt();
            }
        }
        input.close();


        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int temp = ski(i, j, Integer.MAX_VALUE);
                max = temp > max ? temp : max;
            }
        }
        System.out.println(max);


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(cache[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // ski 方法计算 arr 数组中第i行，第j列的元素的最大下滑距离
    // 并将结果存储在 cache 数组中，减少重复调用 ski 的次数
    // pre 表示数组前一个位置的数值
    static int ski(int i, int j, int pre) {
        // 如果越界或者前一个位置元素的值小于当前位置，直接返回
        if (i >= n || i < 0 || j >= m || j < 0 || pre <= arr[i][j])
            return 0;
        // 如果 arr 数组中第i行，第j列的元素的最大下滑距离存在，直接返回
        if (cache[i][j] != 0)
            return cache[i][j];
        // 如果不存在，计算周围位置的下滑距离，将其中最大值加1保存到 cache 中，并返回
        cache[i][j] = Math.max(Math.max(ski(i - 1, j, arr[i][j]), ski(i + 1, j, arr[i][j])),
                Math.max(ski(i, j - 1, arr[i][j]), ski(i, j + 1, arr[i][j])))+1;
        return cache[i][j];
    }
}
