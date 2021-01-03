package xyz.zerxoi.thread.syn;

public class SafeTicketSystemDemo {
    public static void main(String[] args) {
        BugTicket buyTickets = new BugTicket();
        new Thread(buyTickets, "Alice").start();
        new Thread(buyTickets, "Bob").start();
        new Thread(buyTickets, "Chris").start();
    }
}

class SafeBugTicket implements Runnable {
    private int num = 1000;
    private synchronized void buy() {
            System.out.println(Thread.currentThread().getName() + "购买了" + num-- + "号票" );
    }

    @Override
    public void run() {
        while (num > 0)
            buy();
    }
}