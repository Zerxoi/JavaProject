package xyz.zerxoi.thread.advance;

public class LockDemo {
    static Lock lock = new Lock();
    public static void a() throws InterruptedException {
        System.out.println("a申请上锁");
        lock.lock();
        System.out.println("a上锁");

        b();
        System.out.println("a申请解锁");
        lock.unlock();
        System.out.println("a解锁");
    }

    public static void b() throws InterruptedException {
        System.out.println("b申请上锁");
        lock.lock();
        System.out.println("b上锁");

        System.out.println("b申请解锁");
        lock.unlock();
        System.out.println("b解锁");
    }

    public static void main(String[] args) throws InterruptedException {
        a();
    }
}

// 只要上锁了，任何进程包括本进程都无法访问临界内容，都会被挂起并等待被唤醒
class Lock {
    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException {
        // 如果已上锁
        if (isLocked)
            // 其他线程会被挂起，等待被唤醒
            wait();
        // 如果没上锁或者锁被解开，上锁
        isLocked = true;
    }

    public synchronized void unlock() {
        // 解锁
        isLocked = false;
        // 唤醒挂起进程
        notify();
    }
}