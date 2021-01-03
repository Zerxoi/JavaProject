package xyz.zerxoi.io;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

public class CommonsIODemo {
    public static void main(String[] args) {
        System.out.println("xyz/zerxoi/basic 目录大小" + FileUtils.sizeOf(new File("xyz/zerxoi/basic")) + "字节");
        Collection<File> files = FileUtils.listFiles(new File("xyz"), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        for (File f : files) {
            System.out.println(f.getAbsolutePath());
        }
        try {
            System.out.println(FileUtils.readFileToString(new File("a.txt"), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileUtils.copyDirectory(new File("./"), new File("testdir"), new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return !pathname.isDirectory();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 文件复制
        FileInputStream src = null;
        FileOutputStream dest = null;
        try {
            src = new FileInputStream("a.txt");
            dest = new FileOutputStream("b.txt");
            IOUtils.copy(src, dest);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (src != null) {
                try {
                    src.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (dest != null) {
                try {
                    dest.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}