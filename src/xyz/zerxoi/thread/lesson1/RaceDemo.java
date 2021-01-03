
package xyz.zerxoi.thread.lesson1;

public class RaceDemo {
    int length;
    Tortoise tortoise;
    Rabbit rabbit;
    boolean haveWinner;

    public RaceDemo(int length, int tStep, int rStep, int rSleep) {
        this.length = length;
        this.tortoise = new Tortoise(tStep);
        this.rabbit = new Rabbit(rStep, rSleep);
    }

    class Rabbit implements Runnable {
        int step;
        int sleep;
        public Rabbit(int step, int sleep) {
            this.step = step;
            this.sleep = sleep;
        }
    
        @Override
        public void run() {
            for (int i = 0; i < length && !haveWinner; i+=step) {
                System.out.println(Thread.currentThread().getName() + "跑了"+i+"步");
                try {
                    Thread.sleep(sleep);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (!haveWinner)
                printWinner();
        }
    }
    
    class Tortoise implements Runnable {
        int step;
        public Tortoise(int step) {
            this.step = step;
        }
    
        @Override
        public void run() {
            for (int i = 0; i < length&& !haveWinner; i+=step) {
                System.out.println(Thread.currentThread().getName() + "跑了"+i+"步");
            }
            if (!haveWinner)
                printWinner();
        }
    }

    void printWinner() {
        if (!haveWinner) {
            haveWinner = true;
            System.out.println(Thread.currentThread().getName()+"获胜");
        }
    }

    void start() {
        new Thread(tortoise, "乌龟").start();
        new Thread(rabbit, "兔子").start();
    }

    public static void main(String[] args) {
        new RaceDemo(100, 1, 10, 10).start();
    }
}


