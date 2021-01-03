package xyz.zerxoi.webserver.core;

import java.io.IOException;
import java.net.ServerSocket;


public class Server {
    ServerSocket serverSocket;
    
    public void start() throws Exception {
        serverSocket = new ServerSocket(8888);

        while (true) {
            try {
                Dispatcher dispatcher = new Dispatcher(serverSocket.accept());
                new Thread(dispatcher).start();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void stop() throws IOException {
        serverSocket.close();
    }
}
