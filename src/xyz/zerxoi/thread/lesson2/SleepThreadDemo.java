package xyz.zerxoi.thread.lesson2;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SleepThreadDemo {
    public static void main(String[] args) {
        Date date;
        while (true) {
            date = new Date(System.currentTimeMillis());
            System.out.println(new SimpleDateFormat("HH:mm:ss").format(date));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
