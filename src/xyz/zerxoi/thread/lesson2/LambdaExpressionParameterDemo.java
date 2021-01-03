
package xyz.zerxoi.thread.lesson2;

public class LambdaExpressionParameterDemo {
    public static void main(String[] args) {
        // ILove love = (String name) -> {
        // System.out.println("I love " + name);
        // };
        // love.love("Zerxoi");

        // 简化1：可以省略函数式接口方法的参数类型（要省略就省略全部）
        // ILove love = (name) -> {
        // System.out.println("I love " + name);
        // };
        // love.love("Zerxoi");

        // 简化2：简化括号（只有一个参数）
        // ILove love = name -> {
        //     System.out.println("I love " + name);
        // };
        // love.love("Zerxoi");

        // 简化3：去掉花括号（只有一条语句）
        ILove love = name -> System.out.println("I love " + name);
        love.love("Zerxoi");
    }
}

interface ILove {
    void love(String name);
}