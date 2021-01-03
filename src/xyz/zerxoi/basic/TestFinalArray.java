package xyz.zerxoi.basic;

// final修饰的类不能被继承
// final修饰的变量只能初始化一次
public class TestFinalArray {
    final char[] arr;
    final int v;

    TestFinalArray(char[] arr, int v) {
        this.arr = arr;
        this.v =v;
    }

    public void print() {
        System.out.println(arr);
        System.out.println(v);

    }
    public static void main(String[] args) {
        char[] arr = {'a', 'b', 'c'};
        new TestFinalArray(arr, 1).print();

    }
}
