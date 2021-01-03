package xyz.zerxoi.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatRoomServer {
    // Channel 容器
    static List<ChatRoomChannel> list = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws IOException {
        // 开启服务端口
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            try {
                // 接受客户端的连接, 并将接受的套接字封装成 channel
                ChatRoomChannel channel = new ChatRoomChannel(serverSocket.accept());
                // 将 channel 添加到容器 list 中
                list.add(channel);
                // 开启新的线程用于实现消息群发与私聊
                new Thread(new MsgDispatcher(channel)).start();
            } catch (IOException e) { // 如果出现问题, 退出循环, 不再接受连接
                e.printStackTrace();
                break;
            }
        }
        serverSocket.close(); // 关闭服务套接字
    }

    // 消息分发器
    static class MsgDispatcher implements Runnable {
        // 发送消息的 Channel 对象
        ChatRoomChannel channel;

        public MsgDispatcher(ChatRoomChannel channel) {
            this.channel = channel;
        }

        // 群发
        void groupMsg(String msg) throws IOException {
            // 向除了自己以外的用户发送消息
            for (int i = 0; i < list.size(); i++) {
                ChatRoomChannel ch = list.get(i);
                if (ch != channel)
                    ch.send(channel.socket.getPort() + ":" + msg);
            }
        }

        // 私聊
        void atMsg(int port, String msg) throws IOException {
            // 向指定用户发送消息
            for (int i = 0; i < list.size(); i++) {
                ChatRoomChannel ch = list.get(i);
                if (ch.socket.getPort() == port) {
                    ch.send(channel.socket.getPort() + "@" + msg);
                    return;
                }
            }
            // 如果没有该端口, 提示端口不存在
            channel.send("port " + port + " is not exist");
        }

        // 关闭连接, 维护 list 容器
        void clientDisconnect() throws IOException {
            list.remove(channel);
            for (int i = 0; i < list.size(); i++) {
                ChatRoomChannel ch = list.get(i);
                if (ch != channel)
                    list.get(i).send(channel.socket.getPort() + " logout");
            }
            channel.close();
        }

        // 开启线程
        @Override
        public void run() {
            while (true) {
                try {
                    String msg = channel.receive(); // 接收消息
                    int index = msg.indexOf(':');
                    if (msg.charAt(0) == '@' && index != -1) { // 可能是私聊
                        try {
                            int port = Integer.parseInt(msg.substring(1, index));
                            atMsg(port, msg.substring(index + 1, msg.length()));
                        } catch (NumberFormatException e) { // 如果不是私聊, 变为群发
                            groupMsg(msg);
                        }
                    } else // 不是私聊就是群发
                        groupMsg(msg);
                } catch (IOException e) { // 如果有客户端断开连接, 退出循环
                    break;
                }
            }
            try {
                clientDisconnect(); // 断开客户端连接
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class ChatRoomChannel {
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;

    public ChatRoomChannel(Socket socket) throws IOException {
        this.socket = socket;
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
    }

    public void close() throws IOException {
        socket.close();
    }

    public void send(String msg) throws IOException {
        dos.writeUTF(msg);
    }

    public String receive() throws IOException {
        return dis.readUTF();
    }
}
