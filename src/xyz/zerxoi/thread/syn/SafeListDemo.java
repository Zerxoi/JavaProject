package xyz.zerxoi.thread.syn;

import java.util.ArrayList;
import java.util.List;

public class SafeListDemo {
    public static void main(final String[] args) {
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < 1000; i++) {
            // 在此处添加 synchronized 块不好用，因为这里仅创建一个 Thread 对象
            // 并不是对 list 进行读写的地方
            // synchronized (list) {
            new Thread(() -> {
                synchronized (list) {
                    list.add(Thread.currentThread().getName());
                }
            }).start();
            // }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }
}
