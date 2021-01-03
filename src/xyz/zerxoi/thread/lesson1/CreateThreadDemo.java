package xyz.zerxoi.thread.lesson1;

public class CreateThreadDemo extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("线程----" + i);
        }
    }

    public static void main(String[] args) {
        new CreateThreadDemo().start();

        for (int i = 0; i < 100; i++) {
            System.out.println("main-----"+i);
        }
    }
}
