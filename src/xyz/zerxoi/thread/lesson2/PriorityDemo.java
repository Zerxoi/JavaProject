package xyz.zerxoi.thread.lesson2;

public class PriorityDemo {
    public static void main(String[] args) {
        Runnable priority = () -> System.out.println(Thread.currentThread().getName() + "-------" + Thread.currentThread().getPriority());
        Thread t1 = new Thread(priority, "t1");
        Thread t2 = new Thread(priority, "t2");
        Thread t3 = new Thread(priority, "t3");
        Thread t4 = new Thread(priority, "t4");
        Thread t5 = new Thread(priority, "t5");

        t1.setPriority(1);
        t2.setPriority(4);
        t3.setPriority(7);
        t4.setPriority(9);
        t5.setPriority(10);

        System.out.println(Thread.currentThread().getName() + "-------" + Thread.currentThread().getPriority());
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        

    }
}