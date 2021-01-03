package xyz.zerxoi.thread.syn;

import java.util.concurrent.*;

public class JUCArrayList {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
        for (int i = 0; i < 1000; i++)
            new Thread(() -> {
                list.add(Thread.currentThread().getName());
            }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }
}
