package basic;

/**
 * Operators
 */
public class Operators {

    public static void main(String[] args) {
        byte a = 8;
        short b = 16;
        char c = 16;
        int d = 32;
        long e = 64;
        float f = 32.0f;
        double g = 64.0;

        b = a; // byte -> short 低位转高位
        d = b; // short -> int 低位转高位
        d = c; // char -> int 低位转高位
        e = d; // int -> long 低位转高位
        f = e; // long -> float 整型转浮点
        g = f; // float -> double 低位转高位

        // 例外
        c = a; // byte -> char 无法转换
        b = c; // char -> short 无法转换
        c = b; // short -> char 无法转换
    }
}
