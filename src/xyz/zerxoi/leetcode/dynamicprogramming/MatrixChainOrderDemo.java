package xyz.zerxoi.leetcode.dynamicprogramming;

public class MatrixChainOrderDemo {
    public static void main(String[] args) {
        int[] p = {30,35,15,5,10,20,25};
        MatrixChainOrder(p);
    }

    public static void MatrixChainOrder(int[] p) {
        int n = p.length-1;
        int[][] m = new int[n][n];
        int[][] s = new int[n][n];
        for (int l = 1; l < n; l++) {
            for (int i = 0; i < n-l; i++) {
                int j = i + l;
                int min = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int tmp = m[i][k] + m[k+1][j] + p[i]*p[k+1]*p[j+1];
                    if (tmp < min) {
                        min = tmp;
                        m[i][j] = tmp;
                        s[i][j] = k;
                    }
                }
            }
        }
        print2DimensionalArray(m);
        print2DimensionalArray(s);
        printOptimalParents(s, 0, n-1);
    }

    static void print2DimensionalArray(int[][] arr) {
        for (int i = 0; i <arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++)
                System.out.print(arr[i][j]+"\t");
            System.out.println();
        }
    }

    static void printOptimalParents(int[][] s, int i, int j) {
        if (i == j) {
            System.out.print("A"+i);
            return;
        }
        System.out.print("(");
        int k = s[i][j];
        printOptimalParents(s,i,k);
        printOptimalParents(s,k+1,j);
        System.out.print(")");
    }
}
