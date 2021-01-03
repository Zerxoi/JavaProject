package xyz.zerxoi.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerMultiDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                new Thread(new Channel(socket)).start();
            } catch (IOException e) { // 有异常退出循环
                e.printStackTrace();
                break;
            }
        }
        serverSocket.close();
    }
}

class Channel implements Runnable {
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;

    public Channel(Socket socket) throws IOException {
        this.socket = socket;
        // 不需要关闭 dis, dos , 因为关闭可能会关闭与其所关联的 socket
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        String username;
        String password;
        try {
            username = dis.readUTF();
            System.out.println(socket.getPort() + "@username:" + username);
            password = dis.readUTF();
            System.out.println(socket.getPort() + "@password:" + password);

            if (username.equals("zerxoi") && password.equals("123456")) {
                dos.writeUTF("登录成功");
            } else {
                dos.writeUTF("登录失败");
            }
            // 关闭 socket 会自动关闭与其关联的 InputStream 和 OutputStream
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}