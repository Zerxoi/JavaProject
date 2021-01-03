package xyz.zerxoi.net;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerDemo {
    public static void main(String[] args) throws IOException {
        // 在本地端口8888上创建服务端
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket clientSocket = serverSocket.accept();
        System.out.println("一个客户端已连接");
        // 获取输入流, 用于读取从客户端发来的文件数据
        InputStream is = clientSocket.getInputStream();
        // 用DataOutputStream封装输出流, 用于向客户端发送数据
        DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
        // 将从客户端读来的数据写入文件流中
        FileOutputStream fos = new FileOutputStream("tcp.jpg");
        byte[] buf = new byte[8192];
        int len;
        // 因为输入流没有结束, 所以用buf缓存是否满判断数据是否接收完毕
        while ((len = is.read(buf)) == buf.length) {
            // 将文件数据写入文件
            fos.write(buf, 0, len);
        }
        System.out.println("文件接收完毕");
        fos.close();
        // 向客户端发送数据以告知文件接收完毕
        dos.writeUTF("收到文件");
        serverSocket.close();
    }
}
