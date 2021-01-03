package xyz.zerxoi.basic;

import java.util.ArrayList;
import java.util.List;

public class ListTestDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add(new String("a"));
        list.add("a");
        System.out.println(list.size());
        list.remove("a"); // Objects.equals(o, e) 判断相等
        System.out.println(list.size());
        list.remove("a");
        System.out.println(list.size());
        list.remove("a");
        System.out.println(list.size());
    }

}
