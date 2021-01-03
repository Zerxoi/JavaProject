package xyz.zerxoi.gui.snake;

import java.net.URL;

import javax.swing.ImageIcon;

public class Data {
    private static URL headerURL = Data.class.getResource("statics/header.png");
    private static URL upURL = Data.class.getResource("statics/up.png");
    private static URL downURL = Data.class.getResource("statics/down.png");
    private static URL leftURL = Data.class.getResource("statics/left.png");
    private static URL rightURL = Data.class.getResource("statics/right.png");
    private static URL bodyURL = Data.class.getResource("statics/body.png");
    private static URL foodURL = Data.class.getResource("statics/food.png");

    public static ImageIcon header = new ImageIcon(headerURL);
    public static ImageIcon up = new ImageIcon(upURL);
    public static ImageIcon down = new ImageIcon(downURL);
    public static ImageIcon left = new ImageIcon(leftURL);
    public static ImageIcon right = new ImageIcon(rightURL);
    public static ImageIcon body = new ImageIcon(bodyURL);
    public static ImageIcon food = new ImageIcon(foodURL);
}
