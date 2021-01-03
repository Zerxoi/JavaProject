package xyz.zerxoi.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedInputStreamBufferedOutputStreamDemo {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Please input at least two file path");
            return;
        }
        long t1;
        long t2;
        t1 = System.currentTimeMillis();
        copy(args[0], args[1]);
        t2 = System.currentTimeMillis();
        System.out.println("copy 用时：" + (t2 - t1) + "ms");

        t1 = System.currentTimeMillis();
        copyWithBbuffer(args[0], args[2]);
        t2 = System.currentTimeMillis();
        System.out.println("copyWithBbuffer 用时：" + (t2 - t1) + "ms");

        t1 = System.currentTimeMillis();
        bufferedCopy(args[0], args[3]);
        t2 = System.currentTimeMillis();
        System.out.println("bufferedCopy 用时：" + (t2 - t1) + "ms");

        t1 = System.currentTimeMillis();
        bufferedCopyWithBuffer(args[0], args[4]);
        t2 = System.currentTimeMillis();
        System.out.println("bufferedCopyWithBuffer 用时：" + (t2 - t1) + "ms");
    }

    static void copy(String src, String dest) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        int tmp;
        try {
            fileInputStream = new FileInputStream(src);
            fileOutputStream = new FileOutputStream(dest);
            while ((tmp = fileInputStream.read()) != -1) {
                fileOutputStream.write(tmp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static void copyWithBbuffer(String src, String dest) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        byte[] byteArray = new byte[8192];
        int len;
        try {
            fileInputStream = new FileInputStream(src);
            fileOutputStream = new FileOutputStream(dest);
            while ((len = fileInputStream.read(byteArray)) != -1) {
                fileOutputStream.write(byteArray, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static void bufferedCopy(String src, String dest) {
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        int tmp;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(src));
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(dest));
                while ((tmp = bufferedInputStream.read()) != -1) {
                    bufferedOutputStream.write(tmp);
                }
           
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static void bufferedCopyWithBuffer(String src, String dest) {
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        byte[] byteArray = new byte[8192];
        int len;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(src));
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(dest));
                while ((len = bufferedInputStream.read(byteArray)) != -1) {
                    bufferedOutputStream.write(byteArray, 0 , len);
                }
           
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
