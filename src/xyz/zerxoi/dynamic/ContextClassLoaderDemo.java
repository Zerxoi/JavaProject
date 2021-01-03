package xyz.zerxoi.dynamic;

public class ContextClassLoaderDemo {
    public static void main(String[] args) {
        ClassLoader loader = ContextClassLoaderDemo.class.getClassLoader();
        System.out.println("ContextClassLoaderDemo's ClassLoader: "+ loader);
        System.out.println("String ClassLoader: " + String.class.getClassLoader());

        System.out.println(java.sql.DriverManager.class.getClassLoader());
        ClassLoader loader2 = Thread.currentThread().getContextClassLoader();
        System.out.println(loader2);
        Thread.currentThread().setContextClassLoader(new FileSystemClassLoader("C:/Users/Zerxoi/Desktop"));
        System.out.println(Thread.currentThread().getContextClassLoader());
        Class<?> clz = null;
        try {
            clz = Thread.currentThread().getContextClassLoader().loadClass("java.lang.String");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(clz.getClassLoader());
    }
}
