package xyz.zerxoi.gui.lesson1;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * TestFlowLayout
 */
public class TestFlowLayout {

    public static void main(String[] args) {
        Frame frame = new Frame();
        
        frame.setBounds(200, 200, 500, 500);
        // 流式布局
        frame.setLayout(new FlowLayout(FlowLayout.LEFT));

        Button button1 = new Button("Button1");
        Button button2 = new Button("Button2");
        Button button3 = new Button("Button3");

        frame.add(button1);
        frame.add(button2);
        frame.add(button3);

        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }
}