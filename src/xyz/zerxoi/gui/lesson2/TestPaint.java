package xyz.zerxoi.gui.lesson2;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestPaint {
    public static void main(String[] args) {
        new MyPaint().loadFrame();
    }
}

class MyPaint extends Frame {
    private static final long serialVersionUID = 794575140517155383L;

    public void loadFrame() {
        setVisible(true);
        setBounds(200,200,400,500);
        addWindowClosingListener();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.green);
        g.drawOval(100, 100, 100, 100);
        g.setColor(Color.yellow);
        g.fillOval(200, 200, 100, 100);
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