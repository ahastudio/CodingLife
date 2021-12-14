import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class HelloWorld {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1));

        JLabel label = new JLabel("Hello, world!");
        frame.add(label);

        JTextField textField = new JTextField(10);
        frame.add(textField);

        JButton button = new JButton("Update");
        button.addActionListener(e -> {
            String name = textField.getText();
            label.setText("Hello, " + name + "!");
        });
        frame.add(button);

        frame.pack();
        frame.setVisible(true);
    }
}
