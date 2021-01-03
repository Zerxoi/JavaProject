package xyz.zerxoi.gui.lesson3;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class JFrameDemo {
    public static void main(String[] args) {
        new MyJFrame().init();
    }
}

class MyJFrame extends JFrame {
    private static final long serialVersionUID = 8314225917064587436L;

    public void init() {
        setVisible(true);
        setBounds(200, 200, 400, 300);

        setBackground(Color.GREEN);
        Container container = getContentPane();
        container.setBackground(Color.YELLOW);

        JLabel label = new JLabel("JLabel");

        add(label);
        // container.setVisible(false);

        label.setHorizontalAlignment(SwingConstants.CENTER);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}