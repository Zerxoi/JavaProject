package xyz.zerxoi.thread.advance;

public class DCLSingletonPatternDemo {
    private static volatile DCLSingletonPatternDemo instance;
    private DCLSingletonPatternDemo() { }

    static public DCLSingletonPatternDemo getInstance() {
        // 双重检测 避免不必要的对象，提高效率
        if (instance != null) {
            return instance;
        }
        synchronized(DCLSingletonPatternDemo.class) {
            if (instance == null) {
                // new 分三个步骤
                // 1.开辟空间 2.初始化对象信息 3.返回对象的地址给引用
                // 可能会发生指令重排，导致步骤是提前于步骤2，会导致
                // 其他线程在 synchronized 语句外的检测返回一个未初始
                // 化的对象，所以要在 instance 前加上volatile 关键字
                instance = new DCLSingletonPatternDemo();
            }
        }
        return instance;
    }
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            System.out.println(DCLSingletonPatternDemo.getInstance());
        });
        t.start();
        System.out.println(DCLSingletonPatternDemo.getInstance());
    }
}