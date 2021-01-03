package xyz.zerxoi.gui.lesson2;

import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestTextField {
    public static void main(String[] args) {
        new MyTextField();
    }
}

class MyTextField extends Frame {
    private static final long serialVersionUID = -5300123231897141534L;

    MyTextField() {
        super("TestTextField");
        TextField textField = new TextField();
        add(textField);
        setVisible(true);
        pack();

        addWindowClosingListener();

        textField.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                TextField tf = (TextField) e.getSource();
                System.out.println(tf.getText());
                tf.setText("");
            }
        });
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