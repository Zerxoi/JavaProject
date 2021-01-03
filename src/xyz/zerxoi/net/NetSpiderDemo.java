package xyz.zerxoi.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetSpiderDemo {
    public static void main(String[] args) throws IOException {
        URL url;
        InputStream is;
        FileOutputStream fos;
        byte[] b = new byte[8192];
        int len;

        url = new URL("https://www.baidu.com");
        is = url.openStream();
        fos = new FileOutputStream(new File("baidu.html"));
        while ((len = is.read(b)) != -1) {
            fos.write(b, 0, len);
        }
        is.close();
        fos.close();

        url = new URL("http://jandan.net/pic");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        is = conn.getInputStream();
        fos = new FileOutputStream(new File("jandan.html"));
        while ((len = is.read(b)) != -1) {
            fos.write(b, 0, len);
        }
        is.close();
        fos.close();
    }
}
