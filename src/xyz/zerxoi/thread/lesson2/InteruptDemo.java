package xyz.zerxoi.thread.lesson2;

public class InteruptDemo extends Thread {
    @Override
    public void run() {
        // 判断是否被中断, 如果中断退出循环, 结束线程
        while (!interrupted()) {
            System.out.println("线程开始");
            try {
                System.out.println("线程阻塞");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("线程中断");
                // break 跳出循环, 结束线程
                break;
            }
            System.out.println("线程结束");
        }
    }

    public static void main(String[] args) {
        InteruptDemo t = new InteruptDemo();
        t.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 线程中断
        t.interrupt();
        System.out.println("主线程继续执行");
    }
}
