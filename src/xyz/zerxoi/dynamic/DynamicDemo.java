package xyz.zerxoi.dynamic;

import java.io.BufferedReader;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class DynamicDemo {
    public static void main(String[] args) throws Exception {
        // 动态编译
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        int res = javaCompiler.run(null, null, null, "C:/Users/Zerxoi/Desktop/Hi.java");
        System.out.println(res == 0 ? "编译成功" : "编译失败");

        // 动态运行(Runtime.getRuntime启动新的进程运行)
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("java -cp C:/Users/Zerxoi/Desktop Hi");
        InputStream in = process.getInputStream();
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(in));
        String line = null;
        System.out.println("Runtime 运行结果:");
        while ((line = br.readLine()) != null) {
        System.out.println(line);
        }

        // 通过发射运行编译好的类
        URL[] urls = { new URL("file:/" + "C:/Users/Zerxoi/Desktop/") };
        URLClassLoader loader = new URLClassLoader(urls);
        Class<?> c = loader.loadClass("Hi");
        Method m = c.getMethod("main", String[].class);
        // 静态方法不用传入对象
        System.out.println("反射运行结果:");
        // m.invoke(null, (Object) new String[] { "a", "b" }); // 等价于 main(new String[] { "a", "b" })
        // m.invoke(null, new String[] { "a", "b" }); // 如果不加 Object 强转型等价于: main("a", "b")
        m.invoke(null, (Object) new String[] {});
        loader.close();
    }
}
