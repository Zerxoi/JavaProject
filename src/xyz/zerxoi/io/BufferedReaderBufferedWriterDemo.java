package xyz.zerxoi.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// BufferedReader 相比 Reader 新增 ReadLine 方法
// BufferedWriter 相比 Writer 新增 NewLine 方法
public class BufferedReaderBufferedWriterDemo {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Please input at least two file path");
            return;
        }
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        String line;
        try {
            bufferedReader = new BufferedReader(new FileReader(args[0]));
            bufferedWriter = new BufferedWriter(new FileWriter(args[1]));
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
}
