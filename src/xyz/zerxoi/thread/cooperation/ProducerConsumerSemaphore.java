package xyz.zerxoi.thread.cooperation;

public class ProducerConsumerSemaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore();
        new SemaphoreConsumer(semaphore).start();
        new SemaphoreProducer(semaphore).start();
    }
}

class SemaphoreProducer extends Thread {
    Semaphore semaphore;

    public SemaphoreProducer(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            semaphore.produce("product"+i);
            System.out.println("produce:product"+i);
        }
    }
}

class SemaphoreConsumer extends Thread {
    Semaphore semaphore;

    public SemaphoreConsumer(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
           System.out.println("consume:"+semaphore.consume());
        }
    }
}

class Semaphore {
    boolean flag;
    String product;

    public synchronized void produce(String product) {
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.product = product;
        flag = !flag;
        this.notifyAll();
    }

    public synchronized String consume() {
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        flag = !flag;
        this.notifyAll();
        return product;
    }
}