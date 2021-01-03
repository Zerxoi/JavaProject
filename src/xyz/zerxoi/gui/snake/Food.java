package xyz.zerxoi.gui.snake;

import java.util.Random;

public class Food {
    int x;
    int y;
    Random random = new Random();

    void init() {
        x = 25 + 25 * random.nextInt(34);
        y = 75 + 25 * random.nextInt(24);
    }
}
