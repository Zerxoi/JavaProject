package xyz.zerxoi.dynamic;

import java.lang.reflect.Method;

public class SetAccessibleDemo {
    public static void main(String[] args) throws Exception {
        test1();
        test2();
        test3();
    }

    public static void test1() {
        Student s = new Student();

        long begin = System.currentTimeMillis();

        for (int i = 0; i < 1000000000L; i++) {
            s.getName();
        }

        long end = System.currentTimeMillis();
        System.out.println("普通方法调用执行10亿次耗时: " + (end - begin) + " ms");
    }

    public static void test2() throws Exception {
        Student s = new Student();
        Class<?> clz = s.getClass();
        Method m = clz.getDeclaredMethod("getName");
        long begin = System.currentTimeMillis();

        for (int i = 0; i < 1000000000L; i++) {
            m.invoke(s);
        }

        long end = System.currentTimeMillis();
        System.out.println("反射动态方法调用执行10亿次耗时: " + (end - begin) + " ms");
    }

    public static void test3() throws Exception {
        Student s = new Student();
        Class<?> clz = s.getClass();
        Method m = clz.getDeclaredMethod("getName");
        m.setAccessible(true);
        long begin = System.currentTimeMillis();

        for (int i = 0; i < 1000000000L; i++) {
            m.invoke(s);
        }

        long end = System.currentTimeMillis();
        System.out.println("反射动态方法禁用安全检查调用执行10亿次耗时: " + (end - begin) + " ms");
    }
}
