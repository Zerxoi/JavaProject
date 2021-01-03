package xyz.zerxoi.thread.lesson2;

public class DaemonDemo {
    public static void main(String[] args) {
        Runnable god = () -> {
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("God bless you.");
            }
        };

        Runnable people = () -> {
            for (int i = 1; i <= 100; i++) {
                System.out.println("I have lived for " + i + " years");
            }
            System.out.println("Goodbye world");
        };

        Thread godThread = new Thread(god);
        godThread.setDaemon(true);

        Thread peopleThread = new Thread(people);
        godThread.start();
        peopleThread.start();
    }
}
