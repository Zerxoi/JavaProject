package xyz.zerxoi.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TryWithDemo {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 2) {
            System.err.println("Please input at least two file path");
            return;
        }
        byte[] byteArray = new byte[1024];

        int len;
        try (FileInputStream fileInputStream = new FileInputStream(args[0]);
                FileOutputStream fileOutputStream = new FileOutputStream(args[1])) {
            while ((len = fileInputStream.read(byteArray)) != -1) {
                fileOutputStream.write(byteArray, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
