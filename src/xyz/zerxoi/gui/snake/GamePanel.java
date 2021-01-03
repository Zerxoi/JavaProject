package xyz.zerxoi.gui.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel {
    private static final long serialVersionUID = 2751452651814046052L;
    Snake snake = new Snake();
    Food food = new Food();
    int score;
    boolean started;
    boolean defeated;
    Timer timer;
    public GamePanel() {
        setFocusable(true);
        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_SPACE) {
                    if (defeated) {
                        defeated = false;
                        snake.init();
                        score = 0;
                    } else{
                        started = !started;
                    }
                } else if (keyCode == KeyEvent.VK_UP) {
                    snake.direction = 0;
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    snake.direction = 1;
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    snake.direction = 2;
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    snake.direction = 3;
                }
                repaint();
            }
        });
        timer = new Timer(100, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (started && !defeated) {
                    if (snake.x[0] == food.x && snake.y[0] == food.y) {
                        snake.length++;
                        score+=10;
                        food.init();
                    }

                    for (int i = snake.length-1; i > 0; i--) {
                        snake.x[i] = snake.x[i-1];
                        snake.y[i] = snake.y[i-1];
                    }
                    switch (snake.direction) {
                        case 0:
                            snake.y[0] -= 25;
                            break;
                        case 1:
                            snake.y[0] += 25;
                            break;
                        case 2:
                            snake.x[0] -= 25;
                            break;
                        case 3:
                            snake.x[0] += 25;
                            break;
                    }
                    if (snake.x[0]> 850) {
                        snake.x[0] =25;
                    } else if (snake.x[0]<25) {
                        snake.x[0] = 850;
                    }

                    if (snake.y[0]> 650) {
                        snake.y[0] =75;
                    } else if (snake.y[0]<75) {
                        snake.y[0] = 650;
                    }

                    for (int i = 1; i < snake.length; i++) {
                        if (snake.x[i] == snake.x[0] && snake.y[i] == snake.y[0])
                            defeated = true;
                    }
                    repaint();
                }
            }
        });
        score = 0;
        snake.init();
        food.init();
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(Color.WHITE);
        Data.header.paintIcon(this, g, 25, 11);
        g.fillRect(25, 75, 850, 600);

        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        g.drawString("长度："+snake.length, 750, 35);
        g.drawString("分数："+score, 750, 50);

        paintFood(g);
        paintSnake(g);

        if (!started) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("按下空格开始游戏！", 300, 300);
        }

        if (defeated) {
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("游戏结束，按下空格重新开始！", 200, 300);
        }
    }

    void paintSnake(Graphics g) {
        switch (snake.direction) {
            case 0:
                Data.up.paintIcon(this, g, snake.x[0], snake.y[0]);
                break;
            case 1:
                Data.down.paintIcon(this, g, snake.x[0], snake.y[0]);
                break;
            case 2:
                Data.left.paintIcon(this, g, snake.x[0], snake.y[0]);
                break;
            case 3:
                Data.right.paintIcon(this, g, snake.x[0], snake.y[0]);
                break;
        }
        for (int i = 1; i < snake.length; i++) {
            Data.body.paintIcon(this, g, snake.x[i], snake.y[i]);
        }
    }

    void paintFood(Graphics g) {
        Data.food.paintIcon(this, g, food.x, food.y);
    }
}