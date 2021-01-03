package xyz.zerxoi.thread.syn;

public class Web12306Demo {
    public static void main(String[] args) throws InterruptedException {
        Web12306 web12306 = new Web12306(10);
        Passenger alice = new Passenger(web12306, "Alice", 6);
        Passenger bob = new Passenger(web12306, "Bob", 5);
        alice.start();
        bob.start();
        Thread.sleep(100);
        System.out.println("剩余票数："+web12306.getAvailable());
    }
}

class Passenger extends Thread{
    private int seats;
    public Passenger(Runnable runnable, String name, int seats) {
        super(runnable, name);
        this.seats = seats;
    }
    public int getSeats() {
        return seats;
    }
}

class Web12306 implements Runnable {
    private int available;

    public Web12306(int available) {
        this.available = available;
    }

    public int getAvailable() {
        return available;
    }

    public int bookTickets(int seats) {
        if (seats > available)
            return 0;
        synchronized (this) {
            if (seats > available)
                return 0;
            available -= seats;
            return seats;
        }
    }

    @Override
    public void run() {
        Passenger p= (Passenger)Thread.currentThread();
        System.out.println(p.getName() + "订购了" + bookTickets(p.getSeats()) + "张火车票");
    }
}