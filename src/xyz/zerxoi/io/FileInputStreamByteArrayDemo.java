package xyz.zerxoi.io;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamByteArrayDemo {
    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        for (int i = 0; i < args.length; i++) {
            try {
                fileInputStream = new FileInputStream(args[i]);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] byteArray = new byte[1024];
                while (fileInputStream.read(byteArray) != -1) {
                    byteArrayOutputStream.write(byteArray);
                }
                System.out.println(byteArrayOutputStream.toString());
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
            }

        }
    }    
}
