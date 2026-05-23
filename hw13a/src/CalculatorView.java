package src;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CalculatorView {
    private JFrame frame;
    private JTextField display;
    private Map<String, JButton> buttons;

    public CalculatorView() {
        buttons = new HashMap<>();

        createFrame();
        createDisplay();
        createButtons();
        layoutComponents();
    }

    private void createFrame() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 450);
        frame.setLocationRelativeTo(null);
    }

    private void createDisplay() {
        display = new JTextField("0");
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 32));
    }

    private void createButtons() {
        String[] labels = {
                "C", "DEL", "%", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", "="
        };

        for (String label : labels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 22));
            buttons.put(label, button);
        }
    }

    private void layoutComponents() {
        frame.setLayout(new BorderLayout());

        frame.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttonOrder = {
                "C", "DEL", "%", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", "=", "", ""
        };

        for (String label : buttonOrder) {
            if (label.equals("")) {
                buttonPanel.add(new JLabel());
            } else {
                buttonPanel.add(buttons.get(label));
            }
        }

        frame.add(buttonPanel, BorderLayout.CENTER);
    }

    public void setDisplayText(String text) {
        display.setText(text);
    }

    public JButton getButton(String label) {
        return buttons.get(label);
    }

    public void show() {
        frame.setVisible(true);
    }
}