package xyz.zerxoi.dynamic;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

// 桌面的 Sample 类
// public class Sample  {
//     private Sample instance;

//     public void setSample(Object instance) {
//         this.instance = (Sample) instance;
//     }
// }


// 参考: https://developer.ibm.com/zh/articles/j-lo-classloader/#code-java-lang-classloader-code-%E7%B1%BB%E4%BB%8B%E7%BB%8D
public class ClassLoaderDemo {
    public static void main(String[] args) {
        String rootPath = "C:/Users/Zerxoi/Desktop";
        String className = "Sample";

        // # 1. 类加载器的树状组织结构

        // 除了引导类加载器之外, 所有的类加载器都有一个父类加载器.
        // 可以通过 `classLoader.getParent()` 方法获得父类加载器

        // 用户类加载器是开发人员通过继承 java.lang.ClassLoader 类的方式实现
        // 自己的类加载器, 以满足一些特殊的需求
        ClassLoader userClassLoader = new UserClassLoader(rootPath);
        System.out.println(userClassLoader);
        // 用户编写的类加载器的父类加载器是系统类加载器(又叫应用程序类加载器)
        // 他根据 Java 应用的类路径(CLASSPATH)来加载 Java 类.
        // 一般来说, Java 应用的类都是由它来完成加载的.
        // 可以通过 ClassLoader.getSystemClassLoader() 来获取它.
        ClassLoader appClassLoader = userClassLoader.getParent();
        System.out.println(appClassLoader);
        // 系统类加载器的父类加载器是扩展类加载器
        // 拓展类加载器用来加载 Java 的扩展库.
        // Java 虚拟机的实现会提供一个扩展库目录.
        // 该类加载器在此目录里面查找并加载 Java 类.
        ClassLoader extClassLoader = appClassLoader.getParent();
        System.out.println(extClassLoader);
        // 扩展类加载器的父类加载器是引导类加载器
        // 它用来加载 Java 的核心库, 是用原生代码来实现的, 并不继承自
        // java.lang.ClassLoader, 这也是为什么其输出结果是 null 的原因
        ClassLoader bootClassLoader = extClassLoader.getParent();
        System.out.println(bootClassLoader);

        // # 2. 类加载的代理模式

        // 类加载器在尝试自己去查找某个类的字节代码并定义它时, 会先代理给其父类
        // 加载器, 由父类加载器先去尝试加载这个类, 依次类推.

        // 在介绍代理模式背后的动机之前, 首先需要说明一下 Java 虚拟机是如何判
        // 定两个 Java 类是相同的.
        // Java 虚拟机不仅要看类的全名是否相同, 还要看加载此类的类加载器是否一样.
        // 只有两者都相同的情况, 才认为两个类是相同的.
        // 即便是同样的字节代码, 被不同的类加载器加载之后所得到的类, 也是不同的.
        ClassLoader ucl1 = new UserClassLoader(rootPath);
        ClassLoader ucl2 = new UserClassLoader(rootPath);
        try {
            Class<?> clz1 = ucl1.loadClass(className);
            Class<?> clz2 = ucl2.loadClass(className);
            Object obj1 = clz1.getConstructor().newInstance();
            Object obj2 = clz2.getConstructor().newInstance();
            Method setSampleMethod = clz1.getMethod("setSample", Object.class);
            setSampleMethod.invoke(obj1, obj2);
        } catch (Exception e) {
            // 运行时抛出了 java.lang.ClassCastException 异常. 虽然两个对象 obj1 和
            // obj2 的类的名字相同, 但是这两个类是由不同的类加载器实例来加载的, 因此不被 
            // Java 虚拟机认为是相同的.
            System.out.println("不同类加载器加载的相同类不一样");
        }

        // 代理模式的设计冬季就是为了保证Java核心库的类型安全

        // 通过代理模式, 对于 Java 核心库的类的加载工作由引导类加载器来统一完成,保证了
        // Java 应用所使用的都是同一个版本的 Java 核心库的类, 是互相兼容的.

        // 不同的类加载器为相同名称的类创建了额外的名称空间. 相同名称的类可以并存在
        // Java 虚拟机中, 只需要用不同的类加载器来加载它们即可.

        // # 3. 类加载过程
        // 根据类加载的代理模式可知, 类加载器会首先代理给其它类加载器来尝试加载某个类.
        // 这就意味着真正完成类的加载工作的类加载器和启动这个加载过程的类加载器, 有可能不是同一个.
        // 真正完成类的加载工作是通过调用 defineClass(定义加载器) 来实现的; 
        // 而启动类的加载过程是通过调用 loadClass(加载类加载器) 来实现的.
        // 在 Java 虚拟机判断两个类是否相同的时候, 使用的是类的定义加载器. 也就是说, 哪个类加载器
        // 启动类的加载过程并不重要, 重要的是最终定义这个类的加载器. 两种类加载器的关联之处在于: 一
        // 个类的定义加载器是它引用的其它类的初始加载器.


        // # 4. 线程上下文类加载器
        // 线程上下文类加载器（context class loader）是从 JDK 1.2 开始引入的. 类 java.lang.Thread
        // 中的方法 getContextClassLoader() 和 setContextClassLoader(ClassLoader cl) 用来
        // 获取和设置线程的上下文类加载器. 
        // 如果没有通过 setContextClassLoader(ClassLoader cl) 方法进行设置的话, 线程将继承其父线程
        // 的上下文类加载器.
        // Java 应用运行的初始线程的上下文类加载器是系统类加载器.
        // 在线程中运行的代码可以通过此类加载器来加载类和资源.

        // 在Java核心库中提供了很多服务提供者接口（Service Provider Interface，SPI）, 允许第三方为
        // 这些接口提供实现. 常见的SPI有JDBC, JCE, JAXP和JBI等. 
        // 这些SPI的接口都是由Java核心库来提供; 而这些SPI的实现代码很可能是作为Java应用所以来的jar包被包含进来,
        // 可以通过类路径(CLASSPATH)来找到.
        // 问题在于, SPI的接口是Java核心库的一部分, 由引导类加载器来加载; SPI实现的Java类一般是由系统类加载器来加载的.
        // SPI类被加载后, 其 定义类加载器 是 引导类加载器, SPI实现的启动加载器也就变成了引导类加载器. 
        // 引导类加载器只能加载核心类, 而无法加载第三方实现的SPI实现类, 所以类加载的代理模式无法解决该问题

        // 线程上下文类加载器正好解决了这个问题. 如果不做任何的设置, Java 应用的线程的上下文类加载器默认就是系统上下文类加载器.
    }
}

// 继承 java.lang.ClassLoader 类的方式实现自己的类加载器
class UserClassLoader extends ClassLoader {
    private String root;

    public UserClassLoader(String root) {
        this.root = root;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 查看该类加载器是否加载过该类
        Class<?> c = findLoadedClass(name);
        // 如果没有加载过
        if (c == null) {
            // 现委托给父类加载器加载
            ClassLoader parent = this.getParent();
            try {
                c = parent.loadClass(name);
            } catch (Exception e) {
                System.out.println("父类加载器不能加载此类");
            }
            // 如果父类为加载到该类则由该类加载器加载
            if (c == null) {
                // 获取类文件字节码
                byte[] classData = getClassData(name);
                if (classData == null) {
                    throw new ClassNotFoundException();
                } else {
                    // 将字节数组转化为 Class 对象实例
                    c = defineClass(name, classData, 0, classData.length); // 类的定义加载器: 真正完成类的加载工作是通过调用 defineClass 来实现的
                }
            }
        }
        return c;
    }

    private byte[] getClassData(String name) {
        InputStream fis = null;
        ByteArrayOutputStream baos = null;
        try {
            fis = new FileInputStream(root + File.separatorChar + name.replace('.', File.separatorChar) + ".class");
            baos = new ByteArrayOutputStream();
            byte[] buf = new byte[8192];
            int len;
            while ((len = fis.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            // try 中有 return , finally 照常执行
            return baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 字节流没有打开文件, 可以不关闭
            // if (baos != null) {
            // try {
            // baos.close();
            // } catch (IOException e) {
            // e.printStackTrace();
            // }
            // }
        }
        return null;
    }
}