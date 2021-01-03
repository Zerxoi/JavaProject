package xyz.zerxoi.designpattern;

public class StaticProxyDemo {
    public static void main(String[] args) {
        new WeddingCompany(new You()).happyMarry();
        new Thread(()->System.out.println("lambda表达式")).start();
        // Thread对象就是lambda表达式对象的代理，二者都实现Runnable接口
        // 在Thread对象的run()方法中调用真实对象的run()方法

    }
}

interface Marry {
    void happyMarry();
}

// 真实对象
class You implements Marry {
    @Override
    public void happyMarry() {
        System.out.println("新婚快乐");
    }
}

// 代理对象
class WeddingCompany implements Marry {
    private Marry target;
    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void happyMarry() {
        before();
        target.happyMarry();
        after();
    }

    void before() {
        System.out.println("婚前布置");
    }

    void after() {
        System.out.println("婚后收尾款");
    }
}

// 总结：
// 真实对象和代理对象都要实现同一个接口
// 代理对象要代理真实对象，要将真实对象以参数形式传入代理对象
// 在代理对象方法中对真实对象方法进行调用并加以修饰