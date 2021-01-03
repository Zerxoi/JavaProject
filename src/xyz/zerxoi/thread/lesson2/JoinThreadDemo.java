package xyz.zerxoi.thread.lesson2;

// 线程join后，其他线程阻塞，要等到该线程完成后再执行其他线程。
public class JoinThreadDemo implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            System.out.println("VIP"+i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new JoinThreadDemo());
        thread.start();

        for (int i = 0; i < 500; i++) {
            System.out.println("main"+i);
            if (i == 200) {
                thread.join();
            }
        }
    }
}
