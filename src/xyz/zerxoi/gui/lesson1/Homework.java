package xyz.zerxoi.gui.lesson1;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Homework {
    public static void main(String[] args) {
        Frame frame = new Frame("LayoutHomework");
        Button btn1 = new Button("btn1");
        Button btn2 = new Button("btn2");
        Button btn3 = new Button("btn3");
        Button btn4 = new Button("btn4");
        Button btn5 = new Button("btn5");
        Button btn6 = new Button("btn6");
        Button btn7 = new Button("btn7");
        Button btn8 = new Button("btn8");
        Button btn9 = new Button("btn9");
        Button btn10 = new Button("btn10");

        Panel panel1 = new Panel(new BorderLayout());
        Panel panel2 = new Panel(new GridLayout(2, 1));
        Panel panel3 = new Panel(new BorderLayout());
        Panel panel4 = new Panel(new GridLayout(2, 2));

        frame.setLayout(new GridLayout(2, 1));
        panel1.add(btn1, BorderLayout.WEST);
        panel1.add(btn4, BorderLayout.EAST);
        panel2.add(btn2);
        panel2.add(btn3);
        panel1.add(panel2, BorderLayout.CENTER);
        panel3.add(btn5, BorderLayout.WEST);
        panel3.add(btn10, BorderLayout.EAST);
        panel4.add(btn6);
        panel4.add(btn7);
        panel4.add(btn8);
        panel4.add(btn9);
        panel3.add(panel4, BorderLayout.CENTER);
        frame.add(panel1);
        frame.add(panel3);
        
        frame.pack();
        frame.setVisible(true);
        frame.setSize(400, 300);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}