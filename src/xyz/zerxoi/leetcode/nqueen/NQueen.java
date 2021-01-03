package xyz.zerxoi.leetcode.nqueen;

import java.util.ArrayList;
import java.util.List;

public class NQueen {

    public static void main(String[] args) {
        solveNQueens(4);
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        int[] q = new int[n];
        for (int j = 0; j < n; j++) {
            solveNQueensAux(0, j, n, res, q);
        }
        return res;
    }

    public static void solveNQueensAux(int i, int j, int n, List<List<String>> res, int[] q) {
        if (isConflict(q, i, j)) {
            return;
        }
        q[i] = j;
        if (i == n - 1) {
            List<String> list = formatQ(q, n);
            res.add(list);
            return;
        }
        for (int k = 0; k < n; k++) {
            solveNQueensAux(i + 1, k, n, res, q);
        }
    }

    public static boolean isConflict(int[] q, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (Math.abs(j - q[k]) == Math.abs(i - k) || j == q[k]) {
                return true;
            }
        }
        return false;
    }

    public static List<String> formatQ(int[] q, int n) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append('.');
        }
        for (int i = 0; i < n; i++) {
            sb.setCharAt(q[i], 'Q');
            list.add(sb.toString());
            sb.setCharAt(q[i], '.');
        }
        return list;
    }
}