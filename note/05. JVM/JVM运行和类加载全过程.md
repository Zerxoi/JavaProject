# 类加载

## 类加载机制

JVM 把 class 文件加载到内存, 并对数据进行校验, 解析和初始化, 最终形成 JVM  可以直接使用的 Java 类的过程.

- 加载
    - 这个过程需要类加载器参与
    - 将class文件字节码内容加载到内存中, 并将这些静态数据转换成方法区中的运行时数据结构, 在堆中生成一个代表这个类的 java.lang.Class 对象, 作为方法区类数据的访问入口
- 链接: 将 Java 类的二进制代码合并到 JVM 运行状态之中的过程
    - 验证: 确保加载的类信息符合JVM规范, 没有安全方面的问题
    - 准备: 正式为类变量(static变量)分配内存并设置类变量初始值的阶段, 这些内存都在方法区中分配
    - 解析: 虚拟机常量池内的符号引用替换为直接引用的过程
- 初始化
    - 初始化阶段是执行类构造器 `<clinit>()` 方法的过程. 类构造器 `<clinit>()` 方法是由编译器自动收集类中的所有类变量的复制动作和静态语句块(static)中的语句合并产生的.
    - 当初始化一个类的时候, 如果发现其父类还没有进行过初始化, 则需要先触发其父类的初始化
    - 虚拟机会保证一个类的 `<clinit>()` 方法在多线程环境中被正确加锁和同步
    - 当访问一个 Java 类的静态锁时, 只有真正声明这个域的类才会被初始化.


类的静态块只加载一次

- 类的主动引用(一定会发生类的初始化)
    - new 一个类的对象
    - 调用类的静态常量(除了final常量)和静态方法
    - 使用java.lang.reflect包的方法对类进行反射调用
    - 当虚拟机启动, 一定会初始化main方法所在的类
    - 初始化一个类的时候, 如果父类没有初始化, 则先初始化父类
- 类的被动引用(不会发生类的初始化)
    - 当访问一个静态域时, 只有真正声明这个域的类才会被初始化
        - 通过子类引用父类的静态变量, 不会导致子类的初始化
    - 通过数组定义类引用, 不会发生此类的初始化
    - 引用常量不会发生此类的初始化(常量再编译阶段就存入类的常量池中了)

## 类加载器

[深入探讨 Java 类加载器](https://developer.ibm.com/zh/articles/j-lo-classloader/)

类加载器的层次结构(树状结构)

- 引导加载器(bootstrap class loader)
    - 加载 Java 的核心类, 使用原生代码(C++)来实现的, 并不继承自 java.lang.ClassLoader
- 扩展类加载器(extensions class loader)
    - 加载 Java 的扩展库, Java 虚拟机的实现会提供一个拓展库目录. 该类加载器在此目录里面查找并加载 Java 类
    - 由 sun.misc.Launcher$ExtClassLoader 实现
- 应用程序类加载器(application class loader)
    - 根据 Java 应用的类的路径(classpath, java.class.path 路径类) `System,getProperty("java.class.path")`
    - 一般来说, Java 应用的类都是由它来完成加载的
    - 由 sun.misc.Launcher$AppClassLoader 实现
- 自定义类加载器
    - 开发人员可以通过继承 java.lang.ClassLoader 类的方式实现自己的类加载器, 以满足一些特殊的需求

java.lang.ClassLoader类介绍

- java.lang.ClassLoader 类的基本职责就是根据一个指定的类的名称, 找到或者生成其对应的字节代码, 然后从这些字节代码中定义出一个 Java 类, 即 java.lang.Class 类的一个实例.
- 除此之外, ClassLoader 还负责加载 Java 应用所需的资源, 如图像文件或者配置文件等.
