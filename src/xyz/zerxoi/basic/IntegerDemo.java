package xyz.zerxoi.basic;

public class IntegerDemo {
    public static void main(String[] args) {
        // 包装类与基本数据类型
        Integer a = Integer.valueOf(10);
        // 自动装箱 在编译时使用 Integer.valueOf() 创建对象
        a = 10; 
        int b = a.intValue();
        // 自动拆箱 在编译时使用 a.intValue()
        b= a;

        // 包装类与字符串
        a = Integer.parseInt("9");

        String d = a.toString();

        // 字符串与基本数据类型
        d = String.valueOf(b);
        d = Integer.toString(b);

        b = Integer.parseInt(d);


        // 缓存 [-128, 127] 之间的数
        // 实际上是系统初始化的时候创建了 [-128, 127] 之间的缓存数组
        // 当调用 valueOf 时首先检查数字是否在 [-128, 127] 之间
        // 如果在这之间，直接从返回数组中对应元素，如果不在则新创建一个对象
        Integer aInteger = -128;
        Integer bInteger = -128;
        System.out.println(aInteger == bInteger);
        System.out.println(aInteger.equals(bInteger));

        aInteger = 1234;
        bInteger = 1234;
        System.out.println(aInteger == bInteger);
        System.out.println(aInteger.equals(bInteger));
    }
}

