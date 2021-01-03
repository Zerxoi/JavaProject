package xyz.zerxoi.net;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatRoomClient {
    public static void main(String[] args) throws UnknownHostException, IOException {
        // 与端口为8888的服务器连接获取套接字
        Socket socket = new Socket("localhost", 8888);
        // 封装接收流, 创建一个新线程以接收消息
        Thread thread = new Thread(new Receiver(socket));
        // 设置成守护线程, 发送线程关闭, 接收县城也会自动关闭
        thread.setDaemon(true);
        // 开启接收线程
        thread.start();
        // 主线程时发送线程
        // 从命令行读取数据
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 将套接字封装成发送器
        Sender sender = new Sender(socket);
        String line;
        // 读取行数据, 并通过发送器发送
        // 如果行数据是 exit, 退出循环
        while (!(line = br.readLine()).equals("exit")) {
            sender.send(line);
        }
        // 套接字关闭
        socket.close();
    }    
}

// 将套接字的输出流封装成发送器
class Sender {
    DataOutputStream dos;
    public Sender(Socket socket) throws IOException {
        dos = new DataOutputStream(socket.getOutputStream());
    }

    public void send(String msg) throws IOException {
        dos.writeUTF(msg);
    }
}

// 将套接字的输入流封装成接收流
class Receiver implements Runnable {
    DataInputStream dis;
    public Receiver(Socket socket) throws IOException {
        dis = new DataInputStream(socket.getInputStream());
    }

    public String receive() throws IOException {
        return dis.readUTF();
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(receive());
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        try {
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}