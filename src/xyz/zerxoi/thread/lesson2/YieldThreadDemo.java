package xyz.zerxoi.thread.lesson2;

// 礼让
public class YieldThreadDemo {
    public static void main(String[] args) {
        YieldDemo y1 = new YieldDemo();
        YieldDemo y2 = new YieldDemo();
        new Thread(y1, "A").start();
        new Thread(y2, "B").start();
    }
}

class YieldDemo implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i< 10; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
            if (i == 5) {
                System.out.println("yield");
                Thread.yield();
            }
        }
    }
}