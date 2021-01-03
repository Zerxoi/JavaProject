# join 原理

[【Java】Thread类中的join()方法原理](https://blog.csdn.net/u010983881/article/details/80257703)
[Thread.join()原理](https://focusss.github.io/2019/02/27/Thread-join-%E5%8E%9F%E7%90%86/)

```java
public static void main(String[] args){
    System.out.println("MainThread run start.");

    //启动一个子线程
    Thread threadA = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("threadA run start.");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("threadA run finished.");
        }
    });
    threadA.start();

    System.out.println("MainThread join before");
    try {
        threadA.join();    //调用join()
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    System.out.println("MainThread run finished.");
}
```

```java
public final void join() throws InterruptedException {
    join(0);
}

public final synchronized void join(long millis) throws InterruptedException {
    long base = System.currentTimeMillis();  //获取当前时间
    long now = 0;

    if (millis < 0) {
        throw new IllegalArgumentException("timeout value is negative");
    }

    if (millis == 0) {    //这个分支是无限期等待直到b线程结束
        while (isAlive()) {
            wait(0);
        }
    } else {    //这个分支是等待固定时间，如果b没结束，那么就不等待了。
        while (isAlive()) {
            long delay = millis - now;
            if (delay <= 0) {
                break;
            }
            wait(delay);
            now = System.currentTimeMillis() - base;
        }
    }
}
```

在线程A中调用线程B的 `join` 方法, 因为 `join` 是 `synchronized` 方法, 成员方法加了synchronized说明是synchronized(this)，this就是线程B对象本身。也就是说，线程A持有了线程B对象的锁。 

```java
while (isAlive()) {   // threadB线程状态
    wait(0);        // wait(0) 让执行这个方法的线程（主线程）阻塞
}
```

如果线程B是活跃的，则循环调用wait(0)，此时正在执行的线程（线程）释放线程B的对象锁，其他线程可以竞争锁。

大家都知道，有了wait()，必然有notify()，什么时候才会notify呢？在jvm源码里：

```java
// 位于/hotspot/src/share/vm/runtime/thread.cpp中
void JavaThread::exit(bool destroy_vm, ExitType exit_type) {
    // ...

    // Notify waiters on thread object. This has to be done after exit() is called
    // on the thread (if the thread is the last thread in a daemon ThreadGroup the
    // group should have the destroyed bit set before waiters are notified).
    // 有一个贼不起眼的一行代码，就是这行
    ensure_join(this);

    // ...
}


static void ensure_join(JavaThread* thread) {
    // We do not need to grap the Threads_lock, since we are operating on ourself.
    Handle threadObj(thread, thread->threadObj());
    assert(threadObj.not_null(), "java thread object must exist");
    ObjectLocker lock(threadObj, thread);
    // Ignore pending exception (ThreadDeath), since we are exiting anyway
    thread->clear_pending_exception();
    // Thread is exiting. So set thread_status field in  java.lang.Thread class to TERMINATED.
    java_lang_Thread::set_thread_status(threadObj(), java_lang_Thread::TERMINATED);
    // Clear the native thread instance - this makes isAlive return false and allows the join()
    // to complete once we've done the notify_all below
    java_lang_Thread::set_thread(threadObj(), NULL);

    // 同志们看到了没，别的不用看，就看这一句
    // thread就是当前线程，是啥？就是刚才例子中说的threadA线程啊。
    lock.notify_all(thread);

    // Ignore pending exception (ThreadDeath), since we are exiting anyway
    thread->clear_pending_exception();
}
```

一旦线程B执行完毕（状态为TERMINATED），JVM会调用lock.notify_all(thread)，唤醒持有线程B这个对象锁的线程，至此阻塞在线程B对象上的线程，即主线程可以继续执行后面的内容。
