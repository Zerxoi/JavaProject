package xyz.zerxoi.thread.syn;

import java.util.ArrayList;
import java.util.List;

public class UnsafeListDemo {
    public static void main(final String[] args) {
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < 1000; i++) {
            new Thread(() -> list.add(Thread.currentThread().getName())).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }
}
