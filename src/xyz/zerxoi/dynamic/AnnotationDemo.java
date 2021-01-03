package xyz.zerxoi.dynamic;

import java.util.ArrayList;
import java.util.List;

@MyAnnotation
public class AnnotationDemo {

    @Deprecated
    public static void deprecated() {
        System.out.println("不推荐使用");
    }

    
    // 重写父类方法
    @Override
    public String toString() {
        return super.toString();
    }

    @SuppressWarnings("all")
    public static void suppressWarning() {
        List list = new ArrayList<>();
    }

    public static void main(String[] args) {
        deprecated();
    }
}
