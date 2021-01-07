package xyz.zerxoi.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Human {
    public void sleep();

    public void eat();

    public void work();
}

class Employ implements Human {

    @Override
    public void sleep() {
        System.out.println("睡觉 6 小时");
    }

    @Override
    public void eat() {
        System.out.println("吃外卖");
    }

    @Override
    public void work() {
        System.out.println("工作 12 小时");
    }

}

class ProxyFactory {
    public static Object getProxyInstance(Object obj) {
        MyInvocationHandler handler = new MyInvocationHandler(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
    }
}

class MyInvocationHandler implements InvocationHandler {
    private Object obj;

    public MyInvocationHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理类对象的方法分配给被代理类对象之前");
        Object result = method.invoke(obj, args);
        System.out.println("代理类对象的方法分配给被代理类对象之后");
        return result;
    }
}


public class DynamicProxyDemo {
    public static void main(String[] args) {
        Employ employ = new Employ();
        Human proxy = (Human)ProxyFactory.getProxyInstance(employ);
        proxy.eat();
        proxy.sleep();
    }
}