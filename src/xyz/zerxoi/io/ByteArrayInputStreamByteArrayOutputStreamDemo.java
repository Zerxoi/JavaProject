package xyz.zerxoi.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ByteArrayInputStreamByteArrayOutputStreamDemo {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 8192; i++) {
            sb.append(i);
        }
        byte[] buf = new byte[1024];
        int len;
        ByteArrayInputStream bais = new ByteArrayInputStream(sb.toString().getBytes());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            while ((len = bais.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(baos.toString());
    }
}
