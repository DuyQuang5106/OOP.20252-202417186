package src;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class DateView {
    private JFrame frame;
    private JTextField dateDisplay;
    private JLabel selectedFieldLabel;
    private JTextField inputField;
    private Map<String, JButton> buttons;

    public DateView() {
        buttons = new HashMap<>();

        createFrame();
        createDisplay();
        createButtons();
        layoutComponents();
    }

    private void createFrame() {
        frame = new JFrame("Date Changer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);
        frame.setLocationRelativeTo(null);
    }

    private void createDisplay() {
        dateDisplay = new JTextField("22 / 05 / 2026");
        dateDisplay.setEditable(false);
        dateDisplay.setHorizontalAlignment(JTextField.CENTER);
        dateDisplay.setFont(new Font("Arial", Font.BOLD, 30));

        selectedFieldLabel = new JLabel("Selected: DAY", SwingConstants.CENTER);
        selectedFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 18));
    }

    private void createButtons() {
        String[] labels = {
                "UP", "DOWN", "LEFT", "RIGHT", "SET"
        };

        for (String label : labels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            buttons.put(label, button);
        }

        buttons.get("UP").setText("↑");
        buttons.get("DOWN").setText("↓");
        buttons.get("LEFT").setText("←");
        buttons.get("RIGHT").setText("→");
    }

    private void layoutComponents() {
        frame.setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        topPanel.add(dateDisplay);
        topPanel.add(selectedFieldLabel);

        JPanel arrowPanel = new JPanel(new BorderLayout(5, 5));
        arrowPanel.add(buttons.get("UP"), BorderLayout.NORTH);
        arrowPanel.add(buttons.get("DOWN"), BorderLayout.SOUTH);
        arrowPanel.add(buttons.get("LEFT"), BorderLayout.WEST);
        arrowPanel.add(buttons.get("RIGHT"), BorderLayout.EAST);

        JLabel centerLabel = new JLabel("Change selected field", SwingConstants.CENTER);
        centerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        arrowPanel.add(centerLabel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.add(new JLabel("Input value: "), BorderLayout.WEST);
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(buttons.get("SET"), BorderLayout.EAST);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(arrowPanel, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);
    }

    public void setDateText(String text) {
        dateDisplay.setText(text);
    }

    public void setSelectedFieldText(String text) {
        selectedFieldLabel.setText("Selected: " + text);
    }

    public String getInputValue() {
        return inputField.getText();
    }

    public void clearInputValue() {
        inputField.setText("");
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    public JButton getButton(String label) {
        return buttons.get(label);
    }

    public void show() {
        frame.setVisible(true);
    }
}