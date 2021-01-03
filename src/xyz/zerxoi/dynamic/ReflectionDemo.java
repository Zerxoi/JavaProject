package xyz.zerxoi.dynamic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

// 利用反射API获取反射的信息(类的名字,属性,方法,构造器)
public class ReflectionDemo {
    public static void main(String[] args) throws Exception {
        Class<?> clz = Class.forName("xyz.zerxoi.dynamic.Student");
        System.out.println(clz.getClassLoader());
        // 获取类的名字
        // getSimpleName 和 getCaninicalName 的区别
        // https://blog.csdn.net/hustzw07/article/details/71108945
        System.out.println("getSimpleName: " + clz.getSimpleName());
        System.out.println("getName: " + clz.getName());
        System.out.println("getCanonicalName: " + clz.getCanonicalName());

        Field[] fields = clz.getFields();
        System.out.println("getFields's length: " + clz.getFields().length); // 获取 public 属性的数组
        fields = clz.getDeclaredFields();
        System.out.println("getFields's length: " + clz.getDeclaredFields().length); // 获取声明属性的数组
        for (Field f : fields) {
            // Modifier.toString(f.getModifiers()); // 属性修饰符
            // f.getType(); // 属性类型
            // f.getName(); // 属性标识符名称
            System.out.println("- " + f);
        }
        Field f = clz.getDeclaredField("name"); // 获取特定的属性
        System.out.println("getDeclaredField(\"name\"): " + Modifier.toString(f.getModifiers()) + " "
                + f.getType().getSimpleName() + " " + f.getName());

        Method[] methods = clz.getDeclaredMethods();
        System.out.println("getDeclaredMethods's length: " + methods.length);
        for (Method m : methods) {
            // m.getReturnType(); // 方法返回类型
            // m.getName(); // 方法名
            // Modifier.toString(m.getModifiers()); // 方法修饰符
            // m.getParameterTypes(); // 方法参数类型
            System.out.println("- " + m);
        }
        // 通过参数类型指定方法(重载)
        System.out.println("getDeclaredMethod(\"getName\"): " + clz.getDeclaredMethod("getName")); // 获取无参数的 getName 方法
        // 获取参数为 String 的 setName 方法
        System.out.println(
                "getDeclaredMethod(\"setName\", String.class): " + clz.getDeclaredMethod("setName", String.class));

        Constructor<?>[] constructors = clz.getDeclaredConstructors();
        for (Constructor<?> con : constructors) {
            // Modifier.toString(con.getModifiers()); // 构造器修饰符
            // con.getParameterTypes(); // 构造器参数类型
            // con.getName(); // 构造器修饰符名称
            System.out.println(con);
        }
        System.out.println("getDeclaredConstructor(int.class, String.class, int.class): "
                + clz.getDeclaredConstructor(int.class, String.class, int.class)); // 通过参数指定构造器
        System.out.println("getDeclaredConstructor(): " + clz.getDeclaredConstructor()); // 通过参数指定构造器

        Student student;
        // 通过反射获取无参构造器并创建对象
        student = (Student) clz.getConstructor().newInstance();
        System.out.println(student.getId() + "----" + student.getName() + "----" + student.getAge());
        // 通过参数列表指定构造器并创建对象
        student = (Student) clz.getConstructor(int.class, String.class, int.class).newInstance(10002, "kaguya", 17);
        System.out.println(student.getId() + "----" + student.getName() + "----" + student.getAge());

        // 通过反射调用方法
        Method m = clz.getDeclaredMethod("getName");
        System.out.println(m.invoke(student)); // 等价于 student.getName()
        m = clz.getDeclaredMethod("setAge", int.class);
        m.invoke(student, 19); // 等价于 student.setAge(19)
        m = clz.getDeclaredMethod("getAge");
        System.out.println(m.invoke(student)); // 等价于 student.getAge()

        f = clz.getDeclaredField("name");
        // 私有属性不能访问, 将 setAccessible 设置为 true 可以访问
        f.setAccessible(true); // 禁用访问安全检查的开关 提高了运行速度
        System.out.println(f.get(student)); // 等价于 student.name
        f.set(student, "tohru"); // 等价于 student.name = "tohru"
        System.out.println(f.get(student)); // 等价于 student.name
    }
}
