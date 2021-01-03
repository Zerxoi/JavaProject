package xyz.zerxoi.thread.lesson1;

public class RunnableThreadDemo implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "----------" + i);
        }
    }

    public static void main(String[] args) {
        RunnableThreadDemo rt = new RunnableThreadDemo();
        new Thread(rt, "ThreadA").start();
        new Thread(rt, "ThreadB").start();


        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "----------" + i);
        }
    }
}
