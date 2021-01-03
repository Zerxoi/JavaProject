package xyz.zerxoi.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class UDPChatDemo {
    public static void main(String[] args) throws NumberFormatException, IOException {
        // args[0] 是发送器的接收端口号 args[1]是接收器的接收端口号
        // 创建此程序的发送套接字
        DatagramSocket sender = new DatagramSocket();
        // 创建此程序的接收线程, 并设置为守护线程, 否则发送进程结束, 接收进程仍旧阻塞
        UDPChatReceiver receiver = new UDPChatReceiver(Integer.parseInt(args[0]));
        Thread receiverThread = new Thread(receiver);
        receiverThread.setDaemon(true);
        receiverThread.start();
        // 创建聊天程序的套接字地址
        InetSocketAddress addr = new InetSocketAddress("localhost", Integer.parseInt(args[1]));
        byte[] buf = new byte[8192];
        DatagramPacket packet = new DatagramPacket(buf, buf.length, addr);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String line;
        // 读取行数据, 如果行数据是 exit , 程序退出
        while (!(line = input.readLine()).equals("exit")) {
            // 行数据封装, 发送给聊天程序的接收套接字
            byte[] b = line.getBytes();
            packet.setData(b, 0, b.length);
            sender.send(packet);
        }
        sender.close();
    }
}

// UDP接收线程 Runnable
class UDPChatReceiver implements Runnable {
    int port;
    // 配置接收端口
    public UDPChatReceiver(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        // 创建接收套接字
        // try-with 语句自动关闭套接字
        try (DatagramSocket socket = new DatagramSocket(port)) {
            byte[] buf = new byte[8192];
            // 创建接收数据报
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            while (true) {
                // 接收到数据报就打印
                socket.receive(packet);
                System.out.println("reply: " + new String(packet.getData(), 0, packet.getLength()));
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}