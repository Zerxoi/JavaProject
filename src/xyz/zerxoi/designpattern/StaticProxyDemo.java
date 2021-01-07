package xyz.zerxoi.designpattern;

public class StaticProxyDemo {
    public static void main(String[] args) {
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        UserDaoProxy userDaoProxy = new UserDaoProxy(userDaoImpl);
        userDaoProxy.save();

        // 典型的静态代理
        new Thread(()->System.out.println("lambda表达式")).start();
        // lambda表达式对象是目标对象，Thread对象就是lambda表达式对象的代理，二者都实现Runnable接口
        // 在Thread对象的run()方法中调用真实对象的run()方法

    }
}

// 总结：
// 真实对象和代理对象都要实现同一个接口
// 代理对象要代理真实对象，要将真实对象以参数形式传入代理对象
// 在代理对象方法中对真实对象方法进行调用并加以修饰