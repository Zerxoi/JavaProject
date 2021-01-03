package xyz.zerxoi.gui.lesson3;

import java.awt.Container;

import javax.swing.*;

public class JListDemo extends JFrame {
    private static final long serialVersionUID = -1710822629189207218L;

    public JListDemo() {
        Container contentPane = getContentPane();
        String[] strs = {"进击的巨人", "排球少年", "凉宫春日的忧郁", "邻座怪同学"};
        JList<String> jlist = new JList<String>(strs);
        contentPane.add(jlist);
        setVisible(true);
        setBounds(200, 200, 500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new JListDemo();
    }
}
