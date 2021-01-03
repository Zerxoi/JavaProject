package xyz.zerxoi.thread.lesson1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CallableThreadDemo implements Callable<Long> {
    int n;

    public CallableThreadDemo(int n) {
        this.n = n;
    }

    @Override
    public Long call() {
        return nthFibonacci(n);
    }

    public static void main(String[] args) {
        CallableThreadDemo d1 = new CallableThreadDemo(10);
        CallableThreadDemo d2 = new CallableThreadDemo(15);
        CallableThreadDemo d3 = new CallableThreadDemo(20);
        CallableThreadDemo d4 = new CallableThreadDemo(25);
        CallableThreadDemo d5 = new CallableThreadDemo(25);

        ExecutorService ser = Executors.newFixedThreadPool(5);

        Future<Long> tr1 = ser.submit(d1);
        Future<Long> tr2 = ser.submit(d2);
        Future<Long> tr3 = ser.submit(d3);
        Future<Long> tr4 = ser.submit(d4);

        FutureTask<Long> ft = new FutureTask<Long>(d5);
        new Thread(ft).start();

        try {
            System.out.println("10 th Fibonacci :" + tr1.get());
            System.out.println("15 th Fibonacci :" + tr2.get());
            System.out.println("20 th Fibonacci :" + tr3.get());
            System.out.println("25 th Fibonacci :" + tr4.get());
            System.out.println("25 th Fibonacci :" + ft.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ser.shutdownNow();
    }

    public long nthFibonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        long a = 0, b = 1;
        long tmp;
        for (int i = 2; i <= n; i++) {
            tmp = a + b;
            a = b;
            b = tmp;
        }
        return b;
    }
}