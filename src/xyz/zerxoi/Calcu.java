package xyz.zerxoi;

import java.util.Scanner;

public class Calcu {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int m = input.nextInt();
        int n = input.nextInt();
        int x = input.nextInt();
        int y = input.nextInt();

        double[][] arr = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = input.nextDouble();
            }
        }

        input.close();

        double tmp = arr[x][y];
        for (int j = 0; j < n; j++) {
            arr[x][j] = arr[x][j] / tmp;
        }

        for (int i = 0; i < m; i++) {
            tmp = arr[i][y];
            if (i != x) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] -= tmp * arr[x][j];
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
