# 包

为了更好地组织类，Java 提供了包机制，用于区别类名的命名空间。

## 包的作用

- 把功能相似或相关的类或接口组织在同一个包中，方便类的查找和使用。
- 如同文件夹一样，包也采用了树形目录的存储方式。同一个包中的类名字是不同的，不同的包中的类的名字是可以相同的，当同时调用两个不同包中相同类名的类时，应该加上包名加以区别。因此，包可以避免名字冲突。
- 包也限定了访问权限，拥有包访问权限的类才能访问某个包中的类。

Java 使用包（`package`）这种机制是为了防止命名冲突，访问控制，提供搜索和定位类（`class`）、接口、枚举（`enumerations`）和注释（`annotation`）等。

语法格式：

```java
package pkg1[．pkg2[．pkg3…]];
```

## 创建包

创建包的时候，你需要为这个包取一个合适的名字。之后，如果其他的一个源文件包含了这个包提供的类、接口、枚举或者注释类型的时候，都必须将这个包的声明放在这个源文件的开头。

包声明应该在源文件的第一行，每个源文件只能有一个包声明，这个文件中的每个类型都应用于它。

如果一个源文件中没有使用包声明，那么其中的类，函数，枚举，注释等将被放在一个无名的包（`unnamed package`）中。

## `import` 关键字

为了能够使用某一个包的成员，我们需要在 Java 程序中明确导入该包。使用 "import" 语句可完成此功能。

在 java 源文件中 import 语句应位于 package 语句之后，所有类的定义之前，可以没有，也可以有多条，其语法格式为：

```java
import package1[.package2…].(classname|*);
```

如果在一个包中，一个类想要使用本包中的另一个类，那么该包名可以省略。

## `package` 的目录结构

类放在包中会有两种主要的结果：

- 包名成为类名的一部分，正如我们前面讨论的一样。
- 包名必须与相应的字节码所在的目录结构相吻合。

## `CLASSPATH` 系统变量

编译之后的 `.class` 文件应该和 `.java` 源文件一样，它们放置的目录应该跟包的名字对应起来。但是，并不要求 `.class` 文件的路径跟相应的 `.java` 的路径一样。你可以分开来安排源码和类的目录。

```bash
<path-one>\sources\com\runoob\test\Runoob.java
<path-two>\classes\com\runoob\test\Runoob.class
```

这样，你可以将你的类目录分享给其他的编程人员，而不用透露自己的源码。用这种方法管理源码和类文件可以让编译器和Java 虚拟机（JVM）可以找到你程序中使用的所有类型。

类目录的绝对路径叫做 `class path`。设置在系统变量 `CLASSPATH` 中。编译器和 Java 虚拟机通过将 `package` 名字加到 `class path` 后来构造 `.class` 文件的路径。

`<path-two>\classes` 是 `class path`，`package` 名字是 `com.runoob.test`,而编译器和 JVM 会在 `<path-two>\classes\com\runoob\test` 中找 `.class` 文件。

一个 `class path` 可能会包含好几个路径，多路径应该用分隔符分开。默认情况下，编译器和 JVM 查找当前目录。JAR 文件按包含 Java 平台相关的类，所以他们的目录默认放在了 class path 中。

## References

1. [Java 包(package)](https://www.runoob.com/java/java-package.html)
2. [java中package（包）的使用理解](https://blog.csdn.net/FengGLA/article/details/54869858)
