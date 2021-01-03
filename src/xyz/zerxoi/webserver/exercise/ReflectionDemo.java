package xyz.zerxoi.webserver.exercise;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionDemo {
    public static void main(String[] args)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        // 1.获取 CLass 对象的三种方法
        Apple apple = new Apple();
        // 对象获取 Class
        Class<?> clz = apple.getClass();
        // 类获取 Class
        clz = Apple.class;
        // 通过字符串查找 Class
        clz = Class.forName("xyz.zerxoi.webserver.Apple");

        // 2.动态创建对象
        apple = (Apple) clz.getConstructor().newInstance();
        Method setMethod = clz.getMethod("setPrice", int.class);
        setMethod.invoke(apple, 3);
        Method gMethod = clz.getMethod("getPrice");
        System.out.println(gMethod.invoke(apple));

    }
}

class Apple {
    private int price;
    
    public Apple() {}

    public Apple(int price) {
        this.price = price;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}
