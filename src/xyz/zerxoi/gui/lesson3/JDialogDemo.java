package xyz.zerxoi.gui.lesson3;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class JDialogDemo {
    public static void main(String[] args) {
        new MyFrame().init();
    }
}

class MyFrame extends JFrame {
    private static final long serialVersionUID = 6287482579387923731L;

    public void init() {
        setVisible(true);
        setBounds(200, 200, 400, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton btn = new JButton("点击弹框");
        btn.setBounds(30, 30, 100, 50);
        Container container = getContentPane();
        container.setLayout(null);
        container.add(btn);


        btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new MyJDialog();
            }
        });
    }
}

class MyJDialog extends JDialog {
    private static final long serialVersionUID = 9141509873108492334L;

    MyJDialog() {
        setVisible(true);
        setBounds(500,500,300,200);
        Container container = getContentPane();
        JLabel label = new JLabel("Text");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(label);
    }
}