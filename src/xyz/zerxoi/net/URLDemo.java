package xyz.zerxoi.net;

import java.net.MalformedURLException;
import java.net.URL;

// 
public class URLDemo {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://zerxoi@baidu.com:80/index.html?username=zerxoi#chapter1");
        System.out.println("URL: " + url);
        System.out.println("getProtocol: " + url.getProtocol());
        System.out.println("getUserInfo: " + url.getUserInfo());
        System.out.println("getAuthority: " + url.getAuthority());
        System.out.println("getHost: " + url.getHost());
        System.out.println("getPath: " + url.getPath());
        System.out.println("getQuery: " + url.getQuery());
        System.out.println("getFile: " + url.getFile());
        System.out.println("getRef: " + url.getRef());
    }
}
