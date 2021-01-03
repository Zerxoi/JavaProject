package xyz.zerxoi.thread.syn;

public class CinemaDemo {
    public static void main(String[] args) throws InterruptedException {
        Cinema cinema = new Cinema(10);
        Thread alice = new Thread(new Customer(cinema, 6), "Alice");
        Thread bob = new Thread(new Customer(cinema, 5), "Bob");
        alice.start();
        bob.start();
        System.out.println(cinema.getAvaliable());
    }
}

class Cinema {
    private int available;

    public Cinema(int available) {
        this.available = available;
    }

    public int bookTickets(int seats) {
        if (seats > available)
                return 0;
        synchronized (this) {
            if (seats > available)
                return 0;
            System.out.println(available + "张电影票可用");
            available -= seats;
            return seats;
        }
    }

    public int getAvaliable() {
        return available;
    }
}

class Customer implements Runnable {
    private Cinema cinema;
    private int seats;

    Customer(Cinema cinema, int seats) {
        this.cinema = cinema;
        this.seats = seats;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "订购了" + cinema.bookTickets(seats) + "张电影票");
    }
}