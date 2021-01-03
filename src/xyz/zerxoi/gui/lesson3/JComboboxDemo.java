package xyz.zerxoi.gui.lesson3;

import java.awt.Container;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class JComboboxDemo extends JFrame {
    private static final long serialVersionUID = 198165435762358390L;

    public JComboboxDemo() {
        Container contentPane = getContentPane();
        JComboBox<String> combobox = new JComboBox<String>();
        combobox.addItem(null);
        combobox.addItem("JOJO的奇妙冒险");
        combobox.addItem("进击的巨人");
        combobox.addItem("排球少年");
        combobox.addItem("邻座怪同学");

        // combobox.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         JComboBox<String> cb = (JComboBox<String>) e.getSource();
        //         System.out.println(cb.getSelectedItem());
        //     }
        // });

        contentPane.add(combobox);
        setVisible(true);
        setBounds(200,200,100,80);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new JComboboxDemo();
    }
}