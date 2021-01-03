# File 和 getResourceAsStream 的区别

`new File` 的相对路径是 java 运行目录（命令行所在目录）

`classloader.getResourceAsStream` 是 **CLASSPATH** 根目录
可以通过 `class.getResource("/")` 获取 **CLASSPATH** 根目录

`class.getResourceAsStream` 是在 **CLASSPATH** 根目录后加上类路径， 即 **类** 所在目录
资源名前面加 `/` 表示 **CLASSPATH** 根目录

```
命令行目录
│       
├───note
│           
└───src (CLASSPATH)
    │               
    └───xyz
        └───zerxoi
            │       
            └───dynamic (类目录)
                    ScriptDemo.java
                    scriptDemo.js
```

如果 `ScriptDemo.java` 要加载 `scriptDemo.js` 可以采用以下三种方式：

```java
new FileInputStream("src/xyz/zerxoi/dynamic/scriptDemo.js");
ScriptDemo.class.getClassLoader().getResourceAsStream("xyz/zerxoi/dynamic/scriptDemo.js");
ScriptDemo.class.getResourceAsStream("scriptDemo.js");
```