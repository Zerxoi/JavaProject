package xyz.zerxoi.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileInputStreamFileOutputStreamDemo {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Please input at least two file path");
            return;
        }
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        byte[] byteArray = new byte[1024];
        int len;
        try {
            fileInputStream = new FileInputStream(args[0]);
            fileOutputStream = new FileOutputStream(args[1]);
            while ((len = fileInputStream.read(byteArray)) != -1) {
                fileOutputStream.write(byteArray, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
