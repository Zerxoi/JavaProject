# Java 常量池梳理

## [JVM 常量池中存储的是对象还是引用呢？](https://www.zhihu.com/question/57109429/answer/151717241)

运行时常量池（runtime constant pool）存放引用，实际的对象还是存在Java heap上。

HotSpot VM的StringTable的本体在native memory里。它持有String对象的引用而不是String对象的本体。被引用的String还是在Java heap里。一直到JDK6，这些被intern的String在permgen里，JDK7开始改为放在普通Java heap里。

结论：运行时常量池和字符串常量池都存放的是引用。

猜想：String对象本体在JDK6及之前都存放在permgen中，在JDK7开始改为存放在Java heap里。

## [Java 中new String("字面量") 中 "字面量" 是何时进入字符串常量池的?](https://www.zhihu.com/question/55994121/answer/147296098)

常量池分为class文件常量池、运行时常量池和全局字符串常量池。

Class文件中除了有类的版本、字段、方法、接口等描述信息外，还有常量池(Constant Pool Table)，存放编译期生成的各种字面量和符号引用，这部分内容将在类加载后进入方法区的运行时常量池。

全局字符串常量池就是上面所提到的StringTable。

JVM规范里Class文件的常量池项的类型，有两种东西：
CONSTANT_Utf8
CONSTANT_String
后者是String常量的类型，但它并不直接持有String常量的内容，而是只持有一个index，这个index所指定的另一个常量池项必须是一个CONSTANT_Utf8类型的常量，这里才真正持有字符串的内容。

在HotSpot VM中，运行时常量池里，
CONSTANT_Utf8 -> Symbol*（一个指针，指向一个Symbol类型的C++对象，内容是跟Class文件同样格式的UTF-8编码的字符串）
CONSTANT_String -> java.lang.String（一个实际的Java对象的引用，C++类型是oop）

CONSTANT_Utf8会在类加载的过程中就全部创建出来，而CONSTANT_String则是lazy resolve的，例如说在第一次引用该项的ldc指令被第一次执行到的时候才会resolve。那么在尚未resolve的时候，HotSpot VM把它的类型叫做JVM_CONSTANT_UnresolvedString，内容跟Class文件里一样只是一个index；等到resolve过后这个项的常量类型就会变成最终的JVM_CONSTANT_String，而内容则变成实际的那个oop。resolve时是以Symbol对象的内容来创建出内容匹配的java.lang.String对象。

ldc指令，简单地说，它用于将int、float或String型常量值从常量池中推送至栈顶。

执行ldc指令就是触发这个lazy resolution动作的条件 ldc字节码在这里的执行语义是：**到当前类的运行时常量池（runtime constant pool，HotSpot VM里是ConstantPool + ConstantPoolCache）去查找该index对应的项，如果该项尚未resolve则resolve之，并返回resolve后的内容。**
在遇到String类型常量时，resolve的过程如果发现StringTable已经有了内容匹配的java.lang.String的引用，则直接返回这个引用，反之，如果StringTable里尚未有内容匹配的String实例的引用，则会在Java堆里创建一个对应内容的String对象，然后在StringTable记录下这个引用，并返回这个引用出去。

可见，ldc指令是否需要创建新的String实例，全看在第一次执行这一条ldc指令时，StringTable是否已经记录了一个对应内容的String的引用。
