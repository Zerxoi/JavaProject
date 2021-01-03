package xyz.zerxoi.gui.lesson2;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class TestMouseListener {
    public static void main(String[] args) {
        new MyFrame("TestMouseListener").loadFrame();
    }
}

class MyFrame extends Frame {
    private static final long serialVersionUID = 1606482596186072461L;
    ArrayList<Point> points;

    MyFrame(String title) {
        setTitle(title);
    }

    public void loadFrame() {
        points = new ArrayList<Point>();
        setVisible(true);
        setBounds(200, 200, 800, 600);
        addWindowClosingListener();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                points.add(e.getPoint());

                repaint();
            }
        });

    }

    @Override
    public void paint(Graphics g) {
        Iterator<Point> iterator = points.iterator();
        while (iterator.hasNext()) {
            Point p = iterator.next();
            g.fillOval(p.x, p.y, 5, 5);
        }
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