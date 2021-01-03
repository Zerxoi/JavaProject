package xyz.zerxoi.thread.advance;

public class InheritableThreadLocalDemo {
    private static ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();
    public static void main(String[] args) throws InterruptedException {
        threadLocal.set(100);
        System.out.println(Thread.currentThread().getName() + "初始值" + threadLocal.get());

        new Thread(()-> {
            System.out.println(Thread.currentThread().getName() + "继承主线程" + threadLocal.get());
            threadLocal.set(50);
            System.out.println(Thread.currentThread().getName() + "修改成" + threadLocal.get());
        }).start();
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName() + "仍然是" + threadLocal.get());
    }
}
