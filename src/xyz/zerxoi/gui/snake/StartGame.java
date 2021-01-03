package xyz.zerxoi.gui.snake;

import javax.swing.*;

public class StartGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("贪吃蛇");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(200,50,900,720);
        frame.setContentPane(new GamePanel());
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
