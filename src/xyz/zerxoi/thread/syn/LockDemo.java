package xyz.zerxoi.thread.syn;

import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    public static void main(String[] args) {
        LockBugTickets lbg = new LockBugTickets();
        new Thread(lbg, "A").start();
        new Thread(lbg, "B").start();
        new Thread(lbg, "C").start();
    }
}

class LockBugTickets implements Runnable {
    int num = 100;

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                if (num > 0) {
                    System.out.println(Thread.currentThread().getName() + "购买了" + num-- + "号票");
                } else
                    break;
            } finally {
                lock.unlock();
            }
        }
    }
}