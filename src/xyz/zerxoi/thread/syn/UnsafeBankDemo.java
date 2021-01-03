package xyz.zerxoi.thread.syn;

public class UnsafeBankDemo {
    public static void main(String[] args) {
        Account account = new Account("Bob", 1000);
        new Withdraw(account, "Bob", 600).start();
        new Withdraw(account, "Alice", 800).start();
    }
}

class Account {
    String name;
    int balance;

    public Account(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }
}

class Withdraw extends Thread {
    Account account;
    int amount;

    public Withdraw(Account account, String name, int amount) {
        super(name);
        this.account = account;
        this.amount = amount;
    }

    // 在方法上加 synchronized 修饰符锁住 this 对象
    @Override
    public void run() {
        // 执行以下代码块时会将 account 对象锁住，跳出代码块时才能被下一个线程执行
        synchronized (account) {
            System.out.println(
                    this.getName() + ":" + account.name + "账户余额(" + account.balance + ") - " + "提现金额(" + amount + ")");
            if (account.balance < amount) {
                System.out.println("余额不足");
                return;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.balance -= amount;
            System.out.println(this.getName() + ":" + account.name + "账户余额(" + account.balance + ")");
        }
    }
}