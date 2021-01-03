package xyz.zerxoi.gui.snake;

public class Snake {
    int length;
    int direction; // 0 up 1 down 2 left 3 right
    int[] x = new int[816];
    int[] y = new int[816];

    void init() {
        length = 3;
        direction = 3;
        x[0] = 100; y[0] = 100;
        x[1] = 75; y[1] = 100;
        x[2] = 50; y[2] = 100;
    }
}
