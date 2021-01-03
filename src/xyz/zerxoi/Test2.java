package xyz.zerxoi;

import java.util.Scanner;

import java.util.PriorityQueue;

public class Test2 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        input.nextLine();
        for(int i = 0;i<num;i++){
            PriorityQueue<int[]> q = new PriorityQueue<>((a, b)->{return a[2] - b[2];});//存储递增边长
            int[] p = new int[100010];
            String[] info = input.nextLine().split(" ");
            int n = Integer.valueOf(info[0]);
            int m = Integer.valueOf(info[1]);
            int k = Integer.valueOf(info[2]);
            for(int j = 0;j<m;j++){
                String[] brigeInfo = input.nextLine().split(" ");
                int a = Integer.valueOf(brigeInfo[0]);
                int b = Integer.valueOf(brigeInfo[1]);
                int w = Integer.valueOf(brigeInfo[2]);
                int[] c = {a, b, w};
                q.offer(c);
            }
            for(int z = 1;z<=n;++z){
                p[z] = z;
            }
            // int res = 0;
            int cnt = 1;
            while(q.size() != 0){
                int[] c = q.poll();
                int a = find(c[0],p);
                int b = find(c[1],p);
                if(a != b && c[2]<=k){
                    // res += c[2];
                    p[a] = b;
                    cnt++;
                }
            }
            if(cnt == n ){
                System.out.print("YES");
            }else{
                System.out.print("NO");
            }
        }
        input.close();
    }
    static int find(int x,int[] p){
        if(p[x] != x)p[x] = find(p[x],p);
        return p[x];
    }
}