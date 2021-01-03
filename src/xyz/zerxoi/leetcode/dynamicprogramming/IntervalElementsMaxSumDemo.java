package xyz.zerxoi.leetcode.dynamicprogramming;

public class IntervalElementsMaxSumDemo {
    public static void main(String[] args) {
        int[] arr = {-1,2,7,5,3,4,1,6};
        
        // System.out.println(IntervalElementsMaxSumRec(arr));
        System.out.println(IntervalElementsMaxSumRec(arr));
        System.out.println(IntervalElementsMaxSum(arr));
    }

    // static int IntervalElementsMaxSumRec(int[] arr) {
    //     int[][] max = new int[arr.length][arr.length];
    //     return IntervalElementsMaxSumRecAux(arr, 0, arr.length-1, max);
    // }

    // static int IntervalElementsMaxSumRecAux(int[] arr, int i, int j, int[][] max) {
    //     if (i > j)
    //         return 0;
    //     if (i == j) {
    //         max[i][j] = arr[i]>0?arr[i]:0;
    //         return max[i][j];
    //     }
    //     if (max[i][j] > 0)
    //         return max[i][j];
    //     int res = Integer.MIN_VALUE;
    //     for (int k = i; k <= j; k++) {
    //         int sum = arr[k] + IntervalElementsMaxSumRecAux(arr, i, k-2, max) + IntervalElementsMaxSumRecAux(arr, k+2, j, max);
    //         if (sum > max[i][j])
    //             max[i][j] = sum;
    //         if (sum > res)
    //             res = sum;
    //     }
    //     return res;
    // }

    static public int IntervalElementsMaxSumRec(int[] arr) {
        int[] opt = new int[arr.length];
        int[] s=new int[arr.length];
        int res = IntervalElementsMaxSumRecAux(arr, arr.length-1, opt,s);
        System.out.println(printSolution(s));
        return res;
    }

    static public int IntervalElementsMaxSumRecAux(int[] arr, int index, int[] opt, int[] s) {
        if (index<0) {
            return 0;
        }
        int r1 = arr[index] + IntervalElementsMaxSumRecAux(arr, index-2, opt, s);
        int r2 = IntervalElementsMaxSumRecAux(arr, index-1, opt, s);

        if (r1 > r2) {
            opt[index] = r1;
            s[index] = index-2;
        } else {
            opt[index] = r2;
            s[index] = index-1;
        }

        opt[index] = r1>r2?r1:r2;
        
        return opt[index];
    }

    static public int IntervalElementsMaxSum(int[] arr) {
        int[] opt = new int[arr.length];
        int[] s = new int[arr.length];

        if (arr.length > 0) {
            if (arr[0] > 0) {
                opt[0] = arr[0];
                s[0] = -2;
            } else {
                opt[0] = 0;
                s[0] = -1;
            }
        }
        if (arr.length > 1) {
            if (arr[1] > arr[0]) {
                opt[1] = arr[1];
                s[1] = -1;
            } else {
                opt[1]= arr[0];
                s[1] = 0;
            }
        }
        for (int i = 2; i < arr.length; i++) {
            int r1 = arr[i] + opt[i-2];
            int r2 = arr[i-1];
            if (r1 > r2) {
                opt[i] = r1;
                s[i] = i-2;
            } else {
                opt[i] = r2;
                s[i] = i-1;
            }
        }
        System.out.println(printSolution(s));
        return opt[arr.length-1];
    }

    static public String printSolution(int[] s) {
        StringBuilder sb = new StringBuilder();
        sb.append("Solution:[");
        for (int i = s.length-1; i >=0 ;i--) {
            if (s[i] == i-2) {
                sb.append(i+" ");
                i--;
            }
        }
        sb.setCharAt(sb.length()-1, ']');
        return sb.toString();
    }
}
