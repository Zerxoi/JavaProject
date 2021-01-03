# Java数据类型

## Introductions

Java是**强类型语言**。

Java的数据类型可以分为两大类

1. 基本类型
    - 数值类型
        - 整数类型
            - `byte` (1B)
            - `short` (2B)
            - `int` (4B)
            - `long` (8B)
        - 浮点类型
            - `float` (4B)
            - `double` (8B)
        - 字符类型
            - `char` (2B)
    - 布尔类型
        - `boolean` (1b)
2. 引用类型
    - 类 `class`
    - 接口 `interface`
    - 数组

## Notes

1. 浮点数是能表示的数字的位是有限的,存在舍入误差，因此存在一般不能用浮点数直接进行比较，最好使用`BigDecimal`类进行比较。
2. 字符类使用Unicode编码(`\U0000` - `\UFFFF`)

## Questions

### Q1: `String`字面量与`new String()`对象

```java
String s1 = new String("This is a string.");
String s2 = new String("This is a string.");
System.out.println(s1 == s2); // false

String s3 = "This is a string.";
String s4 = "This is a string.";
System.out.println(s3 == s4); // true
```

### A1

参考文献：

1. [java中String与new String的区别](https://blog.csdn.net/dreamzuora/article/details/79464081)
2. [String直接赋字符串和new String的区别](https://blog.csdn.net/zcl_love_wx/article/details/51504224) <sub>虽然后面不太对</sub>

> Returns a canonical representation for the string object.
>
> A pool of strings, initially empty, is maintained privately by the class `String`.
>
> When the `intern` method is invoked, if the pool already contains a string equal to this `String` object as determined by the `equals(Object)` method, then the string from the pool is returned. Otherwise, this `String` object is added to the pool and a reference to this `String` object is returned.

```java
String s1 = "Hello, World!";
String s2 = new String("Hello, World!");
String s3 = new String("Hello, World!");
System.out.println(s1 == s2); // false
System.out.println(s1 == s2.intern()); // true
System.out.println(s2 == s3); // false
System.out.println(s2.intern() == s3.intern()); // true
```

![String字面量与`new String()`对象](String.png)

## References

1. [强弱类型](https://zh.wikipedia.org/wiki/%E5%BC%B7%E5%BC%B1%E5%9E%8B%E5%88%A5)
2. [Java Data Types](https://www.w3schools.com/java/java_data_types.asp)
