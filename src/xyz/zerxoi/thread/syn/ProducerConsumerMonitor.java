package xyz.zerxoi.thread.syn;

public class ProducerConsumerMonitor {
    public static void main(String[] args) {
        Monitor moniter = new Monitor(10);
        Producer producer = new Producer(moniter);
        Consumer consumer = new Consumer(moniter);

        producer.start();
        consumer.start();
    }
}

class Producer extends Thread {
    Monitor moniter;

    public Producer(Monitor moniter) {
        this.moniter = moniter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            moniter.push(new Product(i));
            System.out.println("produce id:" + i);
        }
    }
}

class Consumer extends Thread {
    Monitor moniter;

    public Consumer(Monitor moniter) {
        this.moniter = moniter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("consume id:" + moniter.pop().id);
        }
    }
}

class Product {
    int id;

    public Product(int id) {
        this.id = id;
    }
}

class Monitor {
    int cacheNum;
    int count;
    Product[] products;

    Monitor(int cacheNum) {
        this.cacheNum = cacheNum;
        products = new Product[cacheNum];
    }

    public synchronized void push(Product product) {
        if (count == cacheNum) {
            try {
                // System.out.println("push wait");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        products[count++] = product;
        this.notifyAll();
    }

    public synchronized Product pop() {
        if (count == 0) {
            try {
                // System.out.println("pop wait");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Product product = products[--count];
        this.notifyAll();
        return product;
    }
}