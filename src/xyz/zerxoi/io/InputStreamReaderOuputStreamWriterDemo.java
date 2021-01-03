package xyz.zerxoi.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 转换流
// System.in是字节流对象，代表键盘的输入，如果我们想按行接收用户的输入时，就必须用到缓冲字符流BufferedReader
// 特有的方法readLine()，但是经过观察会发现在创建BufferedReader的构造方法的参数必须是一个Reader对象，这时候
// 我们的转换流InputStreamReader就派上用场了。
// System.out也是字节流对象，代表输出到显示器，按行读取用户的输入后，并且要将读取的一行字符串直接显示到控
// 制台，就需要用到字符流的write(String str)方法，所以我们要使用OutputStreamWriter将字节流转化为字符流。
public class InputStreamReaderOuputStreamWriterDemo {
    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        String line;
        try {
            while (!(line = input.readLine()).equals("")) {
                output.write(line);
                output.newLine();
                // 缓存未满不向 System.out 流中写数据, 手动刷新
                output.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
