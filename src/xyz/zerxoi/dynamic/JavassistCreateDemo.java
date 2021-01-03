package xyz.zerxoi.dynamic;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

public class JavassistCreateDemo {
    public static void main(String[] args) throws CannotCompileException, NotFoundException, IOException {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("xyz.zerxoi.dynamic.Emp");

        // 创建属性
        CtField f1 = CtField.make("private int empno;", cc);
        CtField f2 = CtField.make("private String name;", cc);
        cc.addField(f1);
        cc.addField(f2);

        // 创建方法
        CtMethod m1 = CtMethod.make("public int getEmpno() { return this.empno; }", cc);
        CtMethod m2 = CtMethod.make("public void setEmpno(int empno) { this.empno = empno; }", cc);
        CtMethod m3 = CtMethod.make("public String getName() { return this.name; }", cc);
        CtMethod m4 = CtMethod.make("public void setName(String name) { this.name = name; }", cc);
        cc.addMethod(m1);
        cc.addMethod(m2);
        cc.addMethod(m3);
        cc.addMethod(m4);

        // 添加构造器
        CtConstructor constructor = new CtConstructor(new CtClass[]{CtClass.intType, pool.get("java.lang.String")}, cc);
        constructor.setBody("{ this.empno = empno; this.name = name; }");
        cc.addConstructor(constructor);

        cc.writeFile("C:/Users/Zerxoi/Desktop");
        System.out.println("生成类成功");
    }
}
