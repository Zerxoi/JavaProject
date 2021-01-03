package xyz.zerxoi.leetcode.dynamicprogramming;

import java.util.Scanner;

public class MaxCarrot {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int row = input.nextInt();
        int col = input.nextInt();
        int[][] arr = new int[row][col];
        for (int i =0; i< row; i++) {
            for (int j =0; j<col; j++) {
                arr[i][j] = input.nextInt();
            }
        }
        input.close();
        int[][] m = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col;j++) {
                int r1;
                int r2;
                if (i-1 < 0)
                    r1 = 0;
                else
                    r1 = m[i-1][j];
                if (j-1 <0)
                    r2 = 0;
                else
                    r2 = m[i][j-1];
                m[i][j] = r1 > r2 ? r1+arr[i][j]:r2+arr[i][j];
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col;j++) {
                System.out.print(m[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println(m[row-1][col-1]);
    }

    static int maxCarrot(int[][] arr, int i, int j, int[][] m) {
        if (i<0 || j <0)
            return 0;
        if (m[i][j] > 0)
            return m[i][j];
        int r1 = maxCarrot(arr, i-1, j, m);
        int r2 = maxCarrot(arr, i, j-1, m);
        m[i][j] = r1>r2?(r1+arr[i][j]):(r2+arr[i][j]);
        return m[i][j];
    }


}
