package xyz.zerxoi.net;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReceiverDemo {
    public static void main(String[] args) throws IOException {
        // 接受方UDP套接字
        DatagramSocket socket = new DatagramSocket(10000);
        // 创建接收数据包
        byte[] buf = new byte[8192];
        DatagramPacket packet = new DatagramPacket(buf, 0, buf.length);
        // 接收文件输出流
        FileOutputStream fos = new FileOutputStream("4.new.jpg");
        while (true) {
            // 从接收套接字中接收数据, 如果未收到数据则阻塞
            socket.receive(packet);
            System.out.println("offset: "+ packet.getOffset()+", length: " + packet.getLength());
            // 将接收的数据接入输出流
            fos.write(packet.getData(), packet.getOffset(), packet.getLength());
            // 如果数据报大小小于缓存长度, 说明文件读取完毕, 退出循环
            if (packet.getLength() < buf.length) {
                break;
            }
        }
        fos.close();
        socket.close();
    }
}
