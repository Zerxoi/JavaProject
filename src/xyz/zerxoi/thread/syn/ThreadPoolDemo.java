package xyz.zerxoi.thread.syn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        // Executrors 是一个工具类
        // newFixedThreadPool 方法实则时调用 ThreadPoolExecutor 来生成 ExecutorService 对象
        ExecutorService es = Executors.newFixedThreadPool(10);

        // ExecutorService 对象使用 execute 方法执行 Runnable 实现
        // ExecutorService 对象使用 submit 方法执行 Callable 返回 Future 对象， 再使用 Future 对象的 get 方法获取结果
        es.execute(new ARunnable());
        es.execute(new ARunnable());
        es.execute(new ARunnable());
        es.execute(new ARunnable());

        es.shutdown();
    }
}

class ARunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
