package xyz.zerxoi.thread;

public class WaitNotifyDemo {
    public static void main(String[] args) {
        SynObj synObj = new SynObj();
        ARunnable r = new ARunnable(synObj);
        new Thread(r).start();
        new Thread(r).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 从synObj对象等待队列唤醒一个进程
        synchronized (synObj) {
            System.out.println(Thread.currentThread().getName() + " 获得 synObj 对象锁");
            System.out.println(Thread.currentThread().getName() + " 释放 synObj 对象锁");
            System.out.println(Thread.currentThread().getName() + " 移动至 synObj 对象同步队列");
            synObj.notify();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 从synObj对象等待队列唤醒一个进程
        synchronized (synObj) {
            System.out.println(Thread.currentThread().getName() + " 获得 synObj 对象锁");
            System.out.println(Thread.currentThread().getName() + " 释放 synObj 对象锁");
            System.out.println(Thread.currentThread().getName() + " 移动至 synObj 对象同步队列");
            synObj.notify();
        }
    }
}

class ARunnable implements Runnable {
    SynObj synObj;

    public ARunnable(SynObj synObj) {
        this.synObj = synObj;
    }

    @Override
    public void run() {
        f();
    }

    synchronized public void f() {
        System.out.println(Thread.currentThread().getName() + " 获得 r 对象锁");
        synObj.syn();
        System.out.println(Thread.currentThread().getName() + " 释放 r 对象锁");
    }
}

class SynObj {
    synchronized public void syn() {
        System.out.println(Thread.currentThread().getName() + " 获得 synObj 对象锁");
        try {
            System.out.println(Thread.currentThread().getName() + " 释放 synObj 对象锁");
            System.out.println(Thread.currentThread().getName() + " 移动至 synObj 对象等待队列");
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 被唤醒");
        System.out.println(Thread.currentThread().getName() + " 继续执行");
    }
}