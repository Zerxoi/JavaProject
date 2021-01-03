package xyz.zerxoi.net;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPSenderDemo {
    public static void main(String[] args) throws IOException {
        // 发送方UDP套接字(随意指定发送端口)
        DatagramSocket socket = new DatagramSocket();
        // 接收方UDP套接字
        InetSocketAddress address = new InetSocketAddress("localhost",10000);
        // 发送文件的输入流
        FileInputStream fis = new FileInputStream("4.jpg");
        // 创建发送数据报(设置接收方套接字)
        byte[] buf = new byte[8192];
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address);
        // 从输入流读取数据
        int len;
        while ((len = fis.read(buf))!=-1) {
            // 将数据写入发送数据报中
            packet.setData(buf, 0, len);
            System.out.println("len: " + len);
            // 发送报文
            socket.send(packet);
        }
        fis.close();
        socket.close();
    }
}
