package xyz.zerxoi;

import java.util.Scanner;

public class Test3 {
    static String[] pool = new String[1000001];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int n = input.nextInt();
            for (int j = 0; j < n; j++) {
                switch (input.next()) {
                    case "open":
                        System.out.println(open(input.next()));
                        break;
                    case "dup":
                        System.out.println(dup(input.nextInt()));
                        break;
                    case "dup2":
                        dup2(input.nextInt(), input.nextInt());
                        break;
                    case "close":
                        close(input.nextInt());
                        break;
                    case "query":
                        System.out.println(query(input.nextInt()));
                        break;
                }
            }
            for (int k = 0; k < pool.length; k++) {
                pool[k] = null;
            }
        }
        input.close();
    }

    public static int open(String file_name) {
        int i = 0;
        while (pool[i] != null) {
            i++;
        }
        pool[i] = file_name;
        return i;
    }

    public static int dup(int fd) {
        int i = 0;
        while (pool[i] != null) {
            i++;
        }
        pool[i] = pool[fd];
        return i;
    }

    public static void dup2(int fd, int new_fd) {
        pool[new_fd] = pool[fd];
    }

    public static void close(int fd) {
        pool[fd] = null;
    }

    public static String query(int fd) {
        return pool[fd];
    }
}
