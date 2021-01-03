package xyz.zerxoi.thread.lesson2;

import java.util.Timer;
import java.util.TimerTask;

public class TimingScheduleDemo {
    public static void main(String[] args) {
        TimerTask task =  new TimerTask(){
            @Override
            public void run() {
                System.out.println("Timer Task");
            }
        };
        Timer timer = new Timer();

        timer.schedule(task, 0,1000);
    }    
}

