package xyz.zerxoi.net;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClientMulti {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8888);
        new Request(socket).send();
        String resp = new Response(socket).receive();
        System.out.println(resp);
        // socket 关闭会将与其关联的 InputStream 和 OutputStream 一起关闭
        socket.close();
    }
}

class Request {
    private DataOutputStream dos;

    public Request(Socket socket) throws IOException {
        dos = new DataOutputStream(socket.getOutputStream());
    }

    public void send() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dos.writeUTF(br.readLine());
        dos.writeUTF(br.readLine());
        br.close();
        // 不能关闭 dos, dos 关闭会将与其关联的 socket 也一起关闭, 只需关闭 socket 即可
        // dos.close();
    }
}

class Response {
    // 同理 dis 也不需要关闭
    private DataInputStream dis;

    public Response(Socket socket) throws IOException {
        dis = new DataInputStream(socket.getInputStream());
    }

    public String receive() throws IOException {
        return dis.readUTF();
    }
}