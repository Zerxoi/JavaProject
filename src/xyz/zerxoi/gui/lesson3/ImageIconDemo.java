package xyz.zerxoi.gui.lesson3;

import java.awt.Container;
import java.net.URL;

import javax.swing.*;

public class ImageIconDemo extends JFrame{
    private static final long serialVersionUID = -7172708040050212175L;

    public ImageIconDemo() {
        URL url = ImageIconDemo.class.getResource("liz.jfif");
        ImageIcon image = new ImageIcon(url);
        JLabel label = new JLabel(image, SwingConstants.CENTER);
        Container contentPane = getContentPane();
        contentPane.add(label);
        
        setVisible(true);
        setBounds(100,100,500,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new ImageIconDemo();
    }
}
