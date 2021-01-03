package xyz.zerxoi.io;

import java.io.File;
import java.io.IOException;

public class FileDemo {
    public static void main(String[] args) {
        File dir = new File("a/b/c");
        File file = new File("a/b/c/d.txt");
        try {
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        File[] files = dir.listFiles();
        for (File f : files) {
            System.out.println(f.getAbsolutePath());
        }

    }
}
