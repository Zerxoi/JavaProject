package xyz.zerxoi.webserver.exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class HTTPResponseDemo {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket("www.baidu.com", 80);
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();
        os.write("GET / HTTP/1.1\r\n\r\n".getBytes());
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        int length = 0;
        // String type;
        while ((line = br.readLine()) != null && !line.equals("")) {
            String[] kv = line.split(":");
            if (kv[0].equalsIgnoreCase("content-length")) {
                length = Integer.parseInt(kv[1].trim());
            }

            // else if (kv[0].equalsIgnoreCase("content-type")) {
            // type = kv[1];
            // }
        }

        // TODO: 不完善， length是字节长度
        char[] buf = new char[length];
        br.read(buf);
        // while (length != 0) {
        // int len = is.read(buf);
        // length -= len;
        System.out.println(new String(buf));
        // }

        socket.close();
    }
}
