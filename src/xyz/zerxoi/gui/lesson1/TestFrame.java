package xyz.zerxoi.gui.lesson1;

import java.awt.*;

public class TestFrame {
    public static void main(String[] args) {
        Frame frame = new Frame("测试窗口");
        frame.setVisible(true);
        frame.setSize(400, 400);
        frame.setBackground(Color.CYAN);
        frame.setLocation(200, 200);
    }
}