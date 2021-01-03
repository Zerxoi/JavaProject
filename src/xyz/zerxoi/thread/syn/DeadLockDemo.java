package xyz.zerxoi.thread.syn;

public class DeadLockDemo {
    public static void main(String[] args) {
        Lipstick lipstick = new Lipstick();
        Mirror mirror = new Mirror();
        Makeup alice = new Makeup(lipstick, mirror, "Alice", 0);
        Makeup zbc = new Makeup(lipstick, mirror, "ZBC", 1);

        alice.start();
        zbc.start();
    }
}

class Lipstick {
}

class Mirror {
}

class Makeup extends Thread {
    Lipstick lipstick;
    Mirror mirror;
    int choice;

    public Makeup(Lipstick lipstick, Mirror mirror, String name, int choice) {
        super(name);
        this.lipstick = lipstick;
        this.mirror = mirror;
        this.choice = choice;
    }

    @Override
    public void run() {
        if (choice == 0) {
            synchronized (lipstick) {
                System.out.println(Thread.currentThread().getName() + "使用口红");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // synchronized (mirror) {
                // System.out.println(Thread.currentThread().getName() + "使用镜子");
                // }
            }
            synchronized (mirror) {
                System.out.println(Thread.currentThread().getName() + "使用镜子");
            }
        } else {
            synchronized (mirror) {
                System.out.println(Thread.currentThread().getName() + "使用镜子");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // synchronized (lipstick) {
                // System.out.println(Thread.currentThread().getName() + "使用口红");
                // }
            }
            synchronized (lipstick) {
                System.out.println(Thread.currentThread().getName() + "使用口红");
            }
        }
    }
}