package xyz.zerxoi.dynamic;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileSystemClassLoader extends ClassLoader {

    public static void main(String[] args) throws ClassNotFoundException {
        FileSystemClassLoader loader1 = new FileSystemClassLoader("C:/Users/Zerxoi/Desktop");
        Class<?> c1 = loader1.loadClass("Hi"); // 初始加载器: 启动类的加载过程是通过调用 loadClass 来实现的
        Class<?> c2 = loader1.loadClass("Hi");
        System.out.println("loader1 " + c1 + "'s hashcode:" + c1.hashCode());
        System.out.println("loader1 " + c2 + "'s hashcode:" + c2.hashCode());
        FileSystemClassLoader loader2 = new FileSystemClassLoader("C:/Users/Zerxoi/Desktop");
        Class<?> c3 = loader2.loadClass("Hi");
        System.out.println("loader2 " + c3 + "'s hashcode:" + c3.hashCode());
        // 在 Java 虚拟机判断两个类是否相同的时候，使用的是类的定义加载器。
        // c1 和 c3 不同就是因为他们的类的定义加载器不同, c1 是 loader1 , 而 c2 是 loader2
        System.out.println(c3 + "'s class loader: " + c3.getClassLoader());
        Class<?> c4 = loader2.loadClass("java.lang.String");
        System.out.println("loader2 " + c4 + "'s hashcode:" + c4.hashCode());
        System.out.println(c4 + "'s class loader: " + c4.getClassLoader()); // null 代表是 引导加载器
        Class<?> c5 = loader2.loadClass("xyz.zerxoi.dynamic.Emp");
        System.out.println(c5 + "'s class loader: " + c5.getClassLoader()); // null 代表是 引导加载器

        // 两种类加载器的关联之处在于：一个类的定义加载器是它引用的其它类的初始加载器。如类 com.example.Outer 引用了类 com.example.Inner ，则由类 com.example.Outer 的定义加载器负责启动类 com.example.Inner 的加载过程。
        // 这是也为什么 SPI 是核心类, 它的定义加载类是 引导类加载器, 而引导加载类是不能加载它的实现类的
    }
    

    private String root;

    public FileSystemClassLoader(String root) {
        this.root = root;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> c = findLoadedClass(name);
        if (c == null) {
            ClassLoader parent = this.getParent();
            try {
                parent.loadClass(name);
            } catch (Exception e) {
                System.out.println("---------parent ClassLoader can not load this class---------");
            }
            if (c == null) {
                byte[] classData = getClassData(name);
                if (classData == null) {
                    throw new ClassNotFoundException();
                } else {
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
            fis = new FileInputStream(root + '/' + name.replace('.', '/') + ".class");
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
