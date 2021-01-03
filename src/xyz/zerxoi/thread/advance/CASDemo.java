package xyz.zerxoi.thread.advance;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    private static AtomicInteger stock = new AtomicInteger(5);
    public static void main(String[] args) {
        for (int i = 0; i< 5; i++) {
            new Thread(() -> {
                Integer left = stock.decrementAndGet();
                System.out.println(Thread.currentThread().getName() + "抢了一个产品");
                if (left <1) {
                    System.out.println("抢完了");
                    return;
                }
                System.out.println("还剩"+left+"件商品");
            }).start();
        }

    }
}
