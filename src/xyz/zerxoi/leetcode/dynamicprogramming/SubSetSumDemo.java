package xyz.zerxoi.leetcode.dynamicprogramming;

public class SubSetSumDemo {
    public static void main(String[] args) {
        int[] arr = {3,34,4,12,5,2};

        System.out.println(SubSetSum(arr,9));
        System.out.println(SubSetSum(arr,10));
        System.out.println(SubSetSum(arr,11));
        System.out.println(SubSetSum(arr, 12));
        System.out.println(SubSetSum(arr, 13));


    }

    static public boolean SubSetSumRec(int[] arr, int index, int sum) {
        if (sum==0)
            return true;
        if (index < 0)
            return false;
        return SubSetSumRec(arr, index-1, sum) || SubSetSumRec(arr, index-1, sum-arr[index]);
    }

    static public boolean SubSetSum(int[] arr, int sum) {
        boolean[][] m = new boolean[arr.length][sum+1];
        for (int i = 0; i< arr.length; i++)
            m[i][0] = true;
        for (int j = 1; j < sum+1; j++)
            m[0][j] = arr[0] == j;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < sum+1; j++) {
                int tmp = j - arr[i];
                if (tmp >= 0 && tmp < sum+1) {
                    m[i][j] = m[i-1][tmp];
                }
                m[i][j] = (m[i][j]||m[i-1][j]);
            }
        }
        return m[arr.length-1][sum];
    }
}
