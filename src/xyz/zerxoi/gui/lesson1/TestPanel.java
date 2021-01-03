package xyz.zerxoi.gui.lesson1;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestPanel {
    public static void main(String[] args) {
        Frame frame =  new Frame("窗口");
        Panel panel = new Panel();

        frame.setVisible(true);

        // Panel 默认是流式布局
        frame.setLayout(null);
        frame.setBounds(200, 200, 500, 500);
        frame.setBackground(Color.BLUE);

        panel.setBounds(50, 50, 400, 400);
        panel.setBackground(Color.YELLOW);

        frame.add(panel);

        /**关闭事件
         * WindowListener 是一个接口， new WindowListener 创建匿名内部类要重写所有
         * WindowListener 接口的所有方法。WindowAdapter 是一个用空方法实现
         * WindowListener 接口方法的一个抽象类类， 重写所需的方法即可
         */

        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        
    }
}