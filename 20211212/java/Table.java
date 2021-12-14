import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Table {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Table example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new FlowLayout());

        DefaultTableModel tableModel = new DefaultTableModel();

        tableModel.addColumn("Name");
        tableModel.addColumn("Korean");
        tableModel.addColumn("English");
        tableModel.addColumn("Mathematics");

        tableModel.addRow(new Object[]{"아샬", 100, 100, 100});

        JTable table = new JTable(tableModel);
        frame.add(table.getTableHeader());
        frame.add(table);

        JPanel panel = new JPanel();
        frame.add(panel);

        List<JTextField> textFields = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            JTextField textField = new JTextField(5);
            panel.add(textField);
            textFields.add(textField);
        }

        JButton button = new JButton("추가");
        button.addActionListener(e -> {
            List<Object> values = new ArrayList<>();

            for (int i = 0; i < 4; i++) {
                JTextField textField = textFields.get(i);
                values.add(textField.getText());
            }

            tableModel.addRow(values.toArray());
        });
        panel.add(button);

        frame.setVisible(true);
    }
}
