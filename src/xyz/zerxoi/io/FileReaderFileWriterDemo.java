package xyz.zerxoi.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderFileWriterDemo {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Please input at least two file path");
            return;
        }
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        char[] charArray = new char[1024];
        int len;
        try {
            fileReader = new FileReader(args[0]);
            fileWriter = new FileWriter(args[1]);
            while ((len = fileReader.read(charArray)) != -1) {
                fileWriter.write(charArray, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
