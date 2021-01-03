package xyz.zerxoi.thread.lesson2;

public class ThreadStateDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread.State state = thread.getState();
        System.out.println(state); // NEW

        thread.start();
        state = thread.getState();
        System.out.println(state); // RUNNABLE

        while (state != Thread.State.TERMINATED) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            state = thread.getState();
            System.out.println(state);
        }

        // thread.start(); // 进程终止后不能再次运行
    }
}
