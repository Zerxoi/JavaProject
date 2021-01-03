package xyz.zerxoi.thread;

public class LockDemo {
    static Lock lock = new Lock();

    public static void main(String[] args) {
        try {
            a();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        new Thread(() -> {
            try {
                a();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void a() throws InterruptedException {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " lock");
        b();
        System.out.println(Thread.currentThread().getName() + " unlock");
        lock.unlock();
    }

    public static void b() throws InterruptedException {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " lock");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " unlock");
        lock.unlock();
    }
}

class Lock {
    boolean locked;
    public synchronized void lock() throws InterruptedException {
        if (locked) // 如果已经上锁, 当前进程加入 lock 对象等待队列中
            wait();
        locked = true; // 上锁
    }

    public synchronized void unlock() {
        locked = false; // 解锁
        notify(); // 唤醒 lock 对象队列中的线程
    }
}