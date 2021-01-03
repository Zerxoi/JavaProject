package xyz.zerxoi.gui.lesson2;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestKeyListener {
    public static void main(String[] args) {
        new MyKeyListener();
    }
}

class MyKeyListener extends Frame {
    private static final long serialVersionUID = 2785991248366966863L;

    MyKeyListener() {
        setVisible(true);
        setBounds(200, 200, 400, 300);
        setBackground(Color.GRAY);
        addWindowClosingListener();

        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    System.out.println("UP");
                }
            }
        });
    }

    void addWindowClosingListener() {
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}