package xyz.zerxoi.dynamic;

import java.lang.annotation.Annotation;

public class AnnotationParserDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException {
        Class<?> clz = Class.forName("xyz.zerxoi.annotation.Student");

        // 获取 clz 类上的全部注解
        Annotation[] annotations = clz.getAnnotations();
        for (Annotation a : annotations) {
            System.out.println(a);
        }

        // 获取 clz 类上的指定的注解
        StudentTable table = (StudentTable) clz.getAnnotation(StudentTable.class);
        // 获取注解的值
        System.out.println(table.value());

        // 获取 clz 类上 name 字段的 StudentField 注解
        StudentField field = (StudentField) clz.getDeclaredField("name").getAnnotation(StudentField.class);
        // 读取注解的值
        System.out.println("column: " +field.column());
        System.out.println("type: " +field.type());
        System.out.println("length: " +field.length());

    }
}
