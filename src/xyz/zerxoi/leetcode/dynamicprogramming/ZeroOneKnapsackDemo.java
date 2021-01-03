package xyz.zerxoi.leetcode.dynamicprogramming;

public class ZeroOneKnapsackDemo {
    public static void main(String[] args) {
        int[] weight = { 2, 3, 4, 5, 9 };
        int[] value = { 3, 4, 5, 8, 10 };
        System.out.println(ZeroOneKnapsackRec(weight, value, 20));
        System.out.println(ZeroOneKnapsack(weight, value, 20));

    }

    static public int ZeroOneKnapsackRec(int[] weight, int[] value, int capacity) {
        int[][] s = new int[weight.length][capacity + 1];
        return ZeroOneKnapsackAux(weight, value, capacity, weight.length - 1, s);
    }

    static public int ZeroOneKnapsackAux(int[] weight, int[] value, int capacity, int index, int[][] s) {
        if (index < 0 || capacity <= 0)
            return 0;
        if (s[index][capacity] > 0)
            return s[index][capacity];
        int r1 = 0;
        int r2 = 0;
        if (capacity >= weight[index]) {
            r1 = value[index] + ZeroOneKnapsackAux(weight, value, capacity - weight[index], index - 1, s);
        }
        r2 = ZeroOneKnapsackAux(weight, value, capacity, index - 1, s);
        s[index][capacity] = r1 > r2 ? r1 : r2;
        return s[index][capacity];
    }

    // 如果不需要最优解方案的话，可以去除与数组 m 相关的内容
    static int ZeroOneKnapsack(int[] weight, int[] value, int capacity) {
        int[][] s = new int[weight.length][capacity + 1];
        int[][] m = new int[weight.length][capacity + 1];

        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[i].length; j++)
                m[i][j] = -1;
        for (int j = 0; j < capacity + 1; j++) {
            if (j >= weight[0]) {
                s[0][j] = value[0];
                m[0][j] = 0;
            }
        }
        for (int i = 1; i < weight.length; i++) {
            for (int j = 0; j < capacity + 1; j++) {
                int r1 = 0;
                int r2 = 0;
                if (j >= weight[i])
                    r1 = value[i] + s[i - 1][j - weight[i]];
                r2 = s[i - 1][j];
                s[i][j] = r1 > r2 ? r1 : r2;
                m[i][j] = r1 > r2 ? i : m[i - 1][j];
            }
        }
        printSolution(m, weight);
        return s[weight.length - 1][capacity];
    }

    static void printSolution(int m[][], int[] weight) {
        int j = m[0].length - 1;
        for (int i = m.length - 1; i >= 0; i--) {
            if (m[i][j] == i) {
                System.out.print(m[i][j] + " ");
                j -= weight[i];
            }
        }
        System.out.println();
    }
}
