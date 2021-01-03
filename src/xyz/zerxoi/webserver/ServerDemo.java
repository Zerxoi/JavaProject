package xyz.zerxoi.webserver;

import xyz.zerxoi.webserver.core.Server;

public class ServerDemo {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.start();
        server.stop();
    }
}
