import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class morph {
    public static void main(String[] args) {
        JFrame frame = new JFrame("ComboBox Morph Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JComboBox<String> comboBox1 = new JComboBox<>(new String[]{"Select Option", "Fruits"});
        JPanel dynamicPanel = new JPanel(); // Create a panel to hold dynamic components
        JTextField textField = new JTextField(15); // Create a text field
        JComboBox<String> comboBox2 = new JComboBox<>(new String[]{"Apple", "Banana", "Orange"});

        // Initially, add comboBox2 to the dynamic panel
        dynamicPanel.add(comboBox2);
        dynamicPanel.setVisible(false); // Initially, hide the panel

        // Add ActionListener to the first JComboBox
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox1.getSelectedItem();

                if ("Fruits".equals(selectedOption)) {
                    // Show the panel with comboBox2
                    dynamicPanel.removeAll();
                    dynamicPanel.add(comboBox2);
                    dynamicPanel.setVisible(true);
                } else {
                    // Show the panel with the text field
                    dynamicPanel.removeAll();
                    dynamicPanel.add(textField);
                    dynamicPanel.setVisible(true);
                }
                frame.pack();
            }
        });

        frame.add(comboBox1);
        frame.add(dynamicPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
