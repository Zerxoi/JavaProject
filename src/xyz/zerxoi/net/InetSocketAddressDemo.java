package xyz.zerxoi.net;

import java.net.InetSocketAddress;

public class InetSocketAddressDemo {
    public static void main(String[] args) {
        InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 8080);
        System.out.println(addr.getAddress());
        System.out.println(addr.getPort());
    }
}
