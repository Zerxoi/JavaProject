package xyz.zerxoi.thread.lesson1;

public class UnsafeTicketSystemDemo implements Runnable {
    private int ticketNum = 100;

    @Override
    public void run() {
        while (ticketNum > 0) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"抢到了第"+ticketNum--+"张票！");
        }
    }

    public static void main(String[] args) {
        UnsafeTicketSystemDemo uts = new UnsafeTicketSystemDemo();

        new Thread(uts, "小明").start();
        new Thread(uts, "小红").start();
        new Thread(uts, "黄牛").start();

    }
}
