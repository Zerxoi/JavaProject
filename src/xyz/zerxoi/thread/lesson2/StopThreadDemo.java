package xyz.zerxoi.thread.lesson2;

public class StopThreadDemo implements Runnable {
    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("run...Thread" + i++);
        }
        System.out.println("线程停止");
    }

    public void stop() {
        flag = false;
    }

    public static void main(String[] args) {
        StopThreadDemo demo = new StopThreadDemo();
        new Thread(demo).start();
        for (int i = 0; i < 100; i++) {
            System.out.println("main"+i);
            if (i == 50){
                demo.stop();
            }
        }
    }
}
