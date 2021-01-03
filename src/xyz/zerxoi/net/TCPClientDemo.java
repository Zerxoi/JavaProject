package xyz.zerxoi.net;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClientDemo {
    public static void main(String[] args) throws IOException {
        // 客户端与本地端口8888的服务端连接
        Socket clientSocket = new Socket("localhost", 8888);
        System.out.println("服务端连接成功");
        // 获取连接的输出流, 用于向服务器发送数据
        OutputStream os = clientSocket.getOutputStream();
        // 用DataInputStream包装输入流, 读取服务端发送过来的数据
        DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
        // 创建文件读取流, 读取文件数据
        FileInputStream fis = new FileInputStream("4.jpg");
        byte[] buf = new byte[8192];
        int len;
        // 文件有末尾时可以结束的
        while ((len = fis.read(buf)) != -1) {
            // 向服务端发送文件数据
            os.write(buf, 0, len);
        }
        System.out.println("文件传输结束");
        // 读取服务端传送过来的数据
        System.out.println(dis.readUTF());
        fis.close();
        clientSocket.close();
    }
}
