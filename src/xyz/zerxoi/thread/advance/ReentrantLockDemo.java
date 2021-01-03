package xyz.zerxoi.thread.advance;

public class ReentrantLockDemo {
    static IReentrantLock lock = new IReentrantLock();
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

class IReentrantLock {
    private boolean isLocked;
    private Thread lockedBy;
    private int count;

    synchronized public void lock() throws InterruptedException {
        // 获取当前申请锁的进程
        Thread t = Thread.currentThread();
        System.out.println("lock " + t.getName());
        // 如果已经上锁，并且申请锁的进程和上锁进程不是一个进程，将申请锁的
        // 进程挂起；如果申请锁的进程和上锁进程一样，不用被挂起，可以继续执行
        if (isLocked && lockedBy != t)
            wait();
        // 每重入一次 count 加 1
        count++;
        // 上锁
        isLocked = true;
        // 设置上锁进程
        lockedBy = t;
    }

    synchronized public void unlock() {
        Thread t = Thread.currentThread();
        System.out.println("unlock " + t.getName());
        // 只有上锁进程才能开锁
        if (lockedBy == t) {
            // count 减 1
            count--;
            // 只有 count 计数变为零时才能开锁
            if (count == 0) {
                // 解锁
                isLocked = false;
                // 唤醒上锁队列中的进程
                notify();
                // 设置上锁进程为空
                lockedBy = null;
            }
        }
    }
}