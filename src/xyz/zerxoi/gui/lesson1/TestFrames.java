package xyz.zerxoi.gui.lesson1;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestFrames {
    public static void main(String[] args) {
        MyFrame frame1 = new MyFrame(100, 100, 200, 200, Color.blue);
        MyFrame frame2 = new MyFrame(300, 100, 200, 200, Color.yellow);
        MyFrame frame3 = new MyFrame(100, 300, 200, 200, Color.red);
        MyFrame frame4 = new MyFrame(300, 300, 200, 200, Color.green);

        frame1.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame2.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame3.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame4.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}

class MyFrame extends Frame {
    private static final long serialVersionUID = -65013746513549540L;
    static int count;

    public MyFrame(int x, int y, int w, int h, Color c) {
        super("MyFrame" + (++count));
        setVisible(true);
        setBounds(x, y, w, h);
        setBackground(c);
    }
}