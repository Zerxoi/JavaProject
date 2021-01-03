package xyz.zerxoi.thread.advance;

public class ThreadLocalDemo {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>() {
        protected Integer initialValue() {
            return -1;
        }
    };

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "初始值" + threadLocal.get());
        threadLocal.set(7);
        System.out.println(Thread.currentThread().getName() + "修改后" + threadLocal.get());

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "初始值" + threadLocal.get());
            threadLocal.set(30);
            System.out.println(Thread.currentThread().getName()+"修改后"+threadLocal.get());
        }, "A").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "初始值" + threadLocal.get());
            threadLocal.set(5);
            System.out.println(Thread.currentThread().getName()+"修改后"+threadLocal.get());
        }, "B").start();
    }
}
