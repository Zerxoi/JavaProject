package xyz.zerxoi.leetcode.dynamicprogramming;

public class CutRod {
    // 动态规划：钢条切割
    // 动态规划实际上是记录运算中已经计算过的数据，以避免重复计算
    // 数组 p 为价格表，索引对应的是长度，值对应价格
    // 数组 r 存放索引长度下最大价格，这里用数组 r 记录计算好的数据
    // r[i] = max(p[1]+r[i-1], p[2]+r[i-2],..., p[i-1]+r[1], p[i])
    // 即第一段不切割，剩下部分切割求最优解
    // 数组 s 记录最优解的第一段切割长度
    // 动态规划分为两类，一类是自顶向下，递归并且记录计算过的值
    // 另一类是自底向上，大规模问题依赖小规模的问题，逐步求解
    public static void main(String[] args) {
        int[] p = {0,1,5,8,9,10,17,17,20,24,30};
        System.out.println(upBottomCutRod(9,p));
        System.out.println(bottomUpCutRod(9,p));
    }

    public static int upBottomCutRod(int l, int[] p) {
        int[] r = new int[l+1];
        int[] s = new int[l+1];
        int res = upBottomCutRodAux(l,p,r,s);
        System.out.println(printCutRodSolution(l, s));
        return res;
    }

    public static int upBottomCutRodAux(int l, int[] p, int[] r, int[] s) {
        if (r[l]> 0)
            return r[l];
        for (int i = 1; i <= l && i <= 10; i++) {
            int tmp = p[i] + upBottomCutRodAux(l-i, p,r,s);
            if (tmp > r[l]) {
                r[l] = tmp;
                s[l] = i;
            }
        }
        return r[l];
    }

    public static int bottomUpCutRod(int l, int[] p) {
        int[] r = new int[l+1];
        int[] s = new int[l+1];
        for (int i = 1; i <= l; i++) {
            for (int j = 1; j <= i&& j<=10; j++) {
                int tmp = p[j] + r[i-j];
                if (tmp > r[i]) {
                    r[i] = tmp;
                    s[i] = j;
                }
            }
        }
        System.out.println(printCutRodSolution(l, s));
        return r[l];
    }

    public static String printCutRodSolution(int l,int[] s) {
        StringBuilder sb = new StringBuilder();
        while (l > 0) {
            sb.append(s[l] + " ");
            l -= s[l];
        }
        return sb.toString();
    }

}

