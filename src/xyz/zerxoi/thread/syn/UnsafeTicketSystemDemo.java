package xyz.zerxoi.thread.syn;

public class UnsafeTicketSystemDemo {
    public static void main(String[] args) {
        BugTicket buyTickets = new BugTicket();
        new Thread(buyTickets, "Alice").start();
        new Thread(buyTickets, "Bob").start();
        new Thread(buyTickets, "Chris").start();
    }
}

class BugTicket implements Runnable {
    private int num = 1000;
    private void buy() {
            System.out.println(Thread.currentThread().getName() + "购买了" + num-- + "号票" );
    }

    @Override
    public void run() {
        while (num > 0)
            buy();
    }
}