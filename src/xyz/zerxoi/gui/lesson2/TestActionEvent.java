package xyz.zerxoi.gui.lesson2;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestActionEvent {
    public static void main(String[] args) {
        Frame frame = new Frame("TestActionEvent");
        Button btn1 = new Button("Button1");
        Button btn2 = new Button("Button2");


        // btn1.addActionListener(new ActionListener(){
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         System.out.println("actionPerformed");
        //     }
        // });
        
        // 多个按钮共享一个监听
        MyActionListener actionListener = new MyActionListener();
        btn1.addActionListener(actionListener);
        btn2.addActionListener(actionListener);

        addWindowClosingListener(frame);
        frame.setSize(100,200);
        frame.add(btn1, BorderLayout.NORTH);
        frame.add(btn2, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    static void addWindowClosingListener(Frame frame) {
        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}

class MyActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Button1")) {
            System.out.println("Button1");
        } else {
            System.out.println("Button2");
        }
    }
}