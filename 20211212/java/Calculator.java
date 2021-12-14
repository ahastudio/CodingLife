import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator {
    private String currentOperator = "";
    private int currentNumber = 0;
    private int accumulator = 0;

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.run();
    }

    private void run() {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField display = new JTextField("0");
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(display, BorderLayout.PAGE_START);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(5, 3));
        frame.add(buttons);

        for (int i = 0; i < 10; i += 1) {
            int number = (i + 1) % 10;
            JButton button = new JButton(Integer.toString(number));
            button.addActionListener(e -> {
                currentNumber *= 10;
                currentNumber += number;
                display.setText(Integer.toString(currentNumber));
            });
            buttons.add(button);
        }

        String[] operators = new String[]{"+", "-", "*", "/", "="};
        for (String operator : operators) {
            JButton button = new JButton(operator);
            button.addActionListener(e -> {
                switch (currentOperator) {
                    case "" -> accumulator = currentNumber;
                    case "+" -> accumulator += currentNumber;
                    case "-" -> accumulator -= currentNumber;
                    case "*" -> accumulator *= currentNumber;
                    case "/" -> accumulator /= currentNumber;
                }
                currentOperator = operator;
                currentNumber = 0;
                display.setText(Integer.toString(accumulator));
            });
            buttons.add(button);
        }

        frame.pack();
        frame.setVisible(true);
    }
}
