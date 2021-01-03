package xyz.zerxoi.gui.lesson3;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.*;

public class IconDemo extends JFrame {
    private static final long serialVersionUID = -5045687871842844373L;

    public IconDemo() {
        JLabel label =  new JLabel("JLabel", new MyIcon(15, 15), SwingConstants.CENTER);

        Container contentPane = getContentPane();
        contentPane.add(label);

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new IconDemo();
    }
}

class MyIcon implements Icon{
    int width;
    int height;

    public MyIcon(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.fillOval(x, y, width, height);
    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public int getIconHeight() {
        return height;
    }
}
