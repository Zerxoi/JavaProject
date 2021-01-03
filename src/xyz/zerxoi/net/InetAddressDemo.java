package xyz.zerxoi.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress addr;

        // 返回本机地址
        addr = InetAddress.getLocalHost();
        System.out.println(addr.getHostAddress()); // 返回IP地址
        System.out.println(addr.getHostName()); // 返回主机名
        
        // 根据域名解析地址
        addr = InetAddress.getByName("220.181.38.148");
        System.out.println(addr.getHostAddress()); // 返回IP地址
        System.out.println(addr.getHostName()); // 返回主机名
    }
}
