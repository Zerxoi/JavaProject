package xyz.zerxoi.gui.lesson3;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.*;

public class JRadioButtonDemo extends JFrame {
    private static final long serialVersionUID = -1395844064719496295L;

    public JRadioButtonDemo() {
        JRadioButton male = new JRadioButton("男");
        JRadioButton female = new JRadioButton("女");

        ButtonGroup group = new ButtonGroup();
        group.add(male);
        group.add(female);

        Container contentPane = getContentPane();
        contentPane.add(male, BorderLayout.WEST);
        contentPane.add(female, BorderLayout.EAST);
        
        setVisible(true);
        setBounds(200, 200, 500, 300);
    }

    public static void main(String[] args) {
        new JRadioButtonDemo();
    }
}
