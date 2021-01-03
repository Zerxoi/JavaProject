package xyz.zerxoi.gui.lesson3;

import java.awt.Container;

import javax.swing.*;

public class JScrollDemo extends JFrame {
    private static final long serialVersionUID = -8197004794581153794L;

    public JScrollDemo() {
        Container contentPane = getContentPane();
        JTextArea textArea = new JTextArea(30, 50);
        textArea.setText("请输入文字");
        JScrollPane scrollPane = new JScrollPane(textArea);
        contentPane.add(scrollPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(200,200,300,400);
    }

    public static void main(String[] args) {
        new JScrollDemo();
    }
}