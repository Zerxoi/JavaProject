package xyz.zerxoi.gui.lesson2;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestWindow {
    public static void main(String[] args) {
        new WindowFrame();
    }
}

class WindowFrame extends Frame {
    private static final long serialVersionUID = 4721665568796383881L;

    WindowFrame() {
        setVisible(true);
        setBounds(200, 200, 400, 300);
        setBackground(Color.GRAY);

        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("windowClosing");
            }

            @Override
            public void windowActivated(WindowEvent e) {
                System.out.println("windowActivated");
            }
        });
    }
}