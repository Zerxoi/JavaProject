package xyz.zerxoi.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

// 序列化/反序列化
// 保留数据类型
public class DataInputStreamDataOuputStreamDemo {
    public static void main(String[] args) {
        File f = new File("test.txt");
        DataOutputStream dos = null;
        DataInputStream dis = null;
        try {
            dos = new DataOutputStream(new FileOutputStream(f));
            dis = new DataInputStream(new FileInputStream(f));
            dos.writeInt(1024);
            dos.writeUTF("UTF-8文字");
            dos.writeUTF("梅开二度");
            dos.writeBoolean(true);
            System.out.println(dis.readInt());
            System.out.println(dis.readUTF());
            System.out.println(dis.readUTF());

            System.out.println(dis.readBoolean());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
