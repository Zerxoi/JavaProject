package xyz.zerxoi.dynamic;

import java.lang.reflect.Method;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;

public class JavassistAPIDemo {
    public static void main(String[] args) throws Exception {
        // test1();
        // test2();
        // test3();
        // test4();
        // test5();
        test6();
    }

    // 获取类的信息
    static void test1() throws NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("xyz.zerxoi.dynamic.Emp");

        System.out.println(cc.getName()); // 获取类名
        System.out.println(cc.getSimpleName()); // 获取简要类名
        System.out.println(cc.getSuperclass()); // 获取父类
        System.out.println(cc.getInterfaces()); // 获取接口
    }

    // 添加新的方法
    static void test2() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("xyz.zerxoi.dynamic.Emp");

        CtMethod m = new CtMethod(CtClass.intType, "add", new CtClass[] { CtClass.intType, CtClass.intType }, cc);
        m.setModifiers(Modifier.PUBLIC);
        // $0 相当于 this, $1 是第一个参数, $2 是第二个参数
        m.setBody("{ return $1 + $2;}");
        // 和上面三条语句等价
        // CtMethod m = CtMethod.make("public int add(int a, int b) { return a + b; }",
        // cc);
        cc.addMethod(m);
        Class<?> clz = cc.toClass();
        Object obj = clz.getConstructor().newInstance();
        Method method = clz.getDeclaredMethod("add", int.class, int.class);
        Object res = method.invoke(obj, 1, 2);
        System.out.println(res);
    }

    // 修改已有方法
    static void test3() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("xyz.zerxoi.dynamic.Emp");

        CtMethod cm = cc.getDeclaredMethod("setName", new CtClass[] { pool.get("java.lang.String") });
        cm.insertBefore("System.out.println(\"My old name is \" + $0.name);");
        cm.insertAfter("System.out.println(\"My new name is \" + $0.name);");
        Class<?> clz = cc.toClass();
        Object obj = clz.getConstructor().newInstance();
        Method method = clz.getDeclaredMethod("setName", String.class);
        method.invoke(obj, "kaguya");
    }

    // 属性操作并添加相应的 get 和 set 方法
    static void test4() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("xyz.zerxoi.dynamic.Emp");

        // CtField f1 = CtField.make("private int salary;", cc);
        CtField f1 = new CtField(CtClass.intType, "salary", cc);
        f1.setModifiers(Modifier.PRIVATE);
        cc.addField(f1);

        // 增加相应的set和get方法
        cc.addMethod(CtNewMethod.getter("getSalary", f1));
        cc.addMethod(CtNewMethod.setter("setSalary", f1));
        Class<?> clz = cc.toClass();
        Object obj = clz.getConstructor().newInstance();
        Method method = clz.getDeclaredMethod("setSalary", int.class);
        method.invoke(obj, 5000);
        method = clz.getDeclaredMethod("getSalary");
        System.out.println(method.invoke(obj));
    }

    // 获取构造器
    static void test5() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("xyz.zerxoi.dynamic.Emp");

        CtConstructor[] constructors = cc.getConstructors();
        for (CtConstructor c: constructors) {
            System.out.println(c.getLongName());
        }
    }

    // 获取注解
    static void test6() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("xyz.zerxoi.dynamic.Emp");

        Object[] annotations = cc.getAnnotations();
        Author a = (Author) annotations[0];
        System.out.println("name " + a.name() + " year: " + a.year());
    }
}
