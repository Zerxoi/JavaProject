package xyz.zerxoi.gui.lesson2;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestCalc {
    public static void main(String[] args) {
        new Calculator();
    }
}

class Calculator extends Frame {
    private static final long serialVersionUID = -889309627219169286L;

    Calculator() {
        super("TextCalc");
        addWindowClosingListener();
        
        TextField num1 = new TextField(10);
        TextField num2 = new TextField(10);
        TextField num3 = new TextField(11);

        Button btn = new Button("=");

        Label label = new Label("+");

        setLayout(new FlowLayout());
        add(num1);
        add(label);
        add(num2);
        add(btn);
        add(num3);

        btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                double res = Double.parseDouble(num1.getText()) + Double.parseDouble(num2.getText());
                num1.setText("");
                num2.setText("");
                num3.setText(String.valueOf(res));
            }
        });

        setVisible(true);
        pack();
    }

    void addWindowClosingListener() {
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
