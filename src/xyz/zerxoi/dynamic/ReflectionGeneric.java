package xyz.zerxoi.dynamic;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

// https://blog.csdn.net/f641385712/article/details/88789847
public class ReflectionGeneric {
    public void test1(int x, Map<String, Student> map, List<Student> list) {
        System.out.println("test1");
        System.out.println(x);
        System.out.println(map);
        System.out.println(list);
    }

    public static Map<Integer, Student> test2() {
        System.out.println("test2");
        return null;
    }

    public static void main(String[] args) throws Exception {

        Class<?> clz = ReflectionGeneric.class;
        Method m = clz.getMethod("test1", int.class, Map.class, List.class);
        Type[] types;
        // https://blog.csdn.net/f641385712/article/details/88789847
        // getGenericParameterTypes 返回 Type[], 可获得类型的参数及泛型
        // getParameterTypes 返回 Class<?>[], 只能获得参数类型
        types = m.getGenericParameterTypes();
        for (Type type : types) {
            System.out.println("参数类型: "+type);
            if (type instanceof ParameterizedType) {
                Type[] genericTypes = ((ParameterizedType) type).getActualTypeArguments();
                
                for (Type genericType : genericTypes) {
                    System.out.println("    参数类型泛型: " + genericType);
                }
            }
        }
        m = ReflectionGeneric.class.getMethod("test2");
        Type type = m.getGenericReturnType();
        if (type instanceof ParameterizedType) {
            System.out.println("返回类型: " + type);
            Type[] genericTypes = ((ParameterizedType) type).getActualTypeArguments();
            for (Type t : genericTypes) {
                System.out.println("    返回类型泛型" + t);
            }
        }
    }
}
