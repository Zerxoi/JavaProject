package xyz.zerxoi.thread.lesson1;

import java.io.File;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class ImageDownloadDemo extends Thread {
    String url;
    String name;

    ImageDownloadDemo(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        new WebDownloader().download(url, name);
        System.out.println("下载文件名为" + name);
    }

    public static void main(String[] args) {
        ImageDownloadDemo d1 = new ImageDownloadDemo("http://wx3.sinaimg.cn/mw600/00893JKXly1gigwwiid0ej30u00d9gnh.jpg", "1.jpg");
        ImageDownloadDemo d2 = new ImageDownloadDemo("http://wx1.sinaimg.cn/mw600/6ea67c04ly1gifwzkdmh7j20j60bp793.jpg", "2.jpg");
        ImageDownloadDemo d3 = new ImageDownloadDemo("http://wx4.sinaimg.cn/mw600/006F8z5Oly1gicms8cjyqj30if0fnn13.jpg", "3.jpg");
        ImageDownloadDemo d4 = new ImageDownloadDemo("http://wx1.sinaimg.cn/mw600/00893JKXly1gifyd7wszlj30go3cme81.jpg", "4.jpg");

        d1.start();
        d2.start();
        d3.start();
        d4.start();
    }
}


class WebDownloader {
    public void download(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("IO异常，downloader方法出现问题");
        }
    }
}