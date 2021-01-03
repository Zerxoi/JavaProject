package xyz.zerxoi.thread.cooperation;

public class ProducerConsumerMonitor {
    public static void main(String[] args) {
        IMonitor monitor = new IMonitor(5);
        new Thread(new IConsumer(monitor), "Consumer").start();
        new Thread(new IProducer(monitor), "Producer").start();
    }
}

class IProducer implements Runnable {
    private IMonitor monitor;

    public IProducer(IMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                monitor.push(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "生产" + i);
        }
    }
}

class IConsumer implements Runnable {
    private IMonitor monitor;

    public IConsumer(IMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + "消费" + monitor.pop());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class IMonitor {
    private int[] items;
    private int num;
    private int count;
    public IMonitor(int num) {
        this.num = num;
        items = new int[num];
    }

    synchronized public void push(int item) throws InterruptedException {
        if (count == num)
            this.wait();
        // 并不是所有时候都需要 notifyAll , 因为只有在容量空的时候
        // pop 才会 wait， 这是才需要 notifyAll，当然这个 if语句
        // 是可以省略的， 只保留 notifyAll 也可以。
        if (count == 0)
            this.notifyAll();
        items[count++] = item;
    }

    synchronized public int pop() throws InterruptedException {
        if (count == 0)
            this.wait();
        // 并不是所有时候都需要 notifyAll , 因为只有在容量满的时候
        // push 才会 wait， 这是才需要 notifyAll，当然这个 if语句
        // 是可以省略的， 只保留 notifyAll 也可以。
        if (count == num)
            this.notifyAll();
        return items[--count];
    }
}