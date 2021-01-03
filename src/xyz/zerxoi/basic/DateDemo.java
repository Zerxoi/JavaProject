package xyz.zerxoi.basic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDemo {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        System.out.println(sdf.format(new Date()));
        try {
            Date date = sdf.parse("2012/9/10 上午11:11");
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
