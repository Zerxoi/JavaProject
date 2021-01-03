package xyz.zerxoi.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

// 序列化/反序列化
// 保留数据类型
public class ObjectOutputStreamObjectInputStreamDemo {
    public static void main(String[] args) {
        File f = new File("test.txt");
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(f));
            ois = new ObjectInputStream(new FileInputStream(f));
            oos.writeUTF("UTF8字符串");
            oos.writeUTF("梅开二度");
            oos.writeInt(1024);
            oos.writeObject(f);
            oos.writeObject(new Date());
            // 可能缓存未满,flush 写入文件
            oos.flush();
            System.out.println(ois.readUTF());
            System.out.println(ois.readUTF());
            System.out.println(ois.readInt());
            System.out.println(ois.readObject());
            System.out.println(ois.readObject());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
