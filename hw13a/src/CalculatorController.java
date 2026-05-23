package src;

public class CalculatorController {
    private CalculatorModel model;
    private CalculatorView view;

    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;

        bindDigitButtons();
        bindOperatorButtons();
        bindSpecialButtons();
    }

    private void bindDigitButtons() {
        String[] digits = {
                "0", "1", "2", "3", "4",
                "5", "6", "7", "8", "9"
        };

        for (String digit : digits) {
            view.getButton(digit).addActionListener(e -> {
                model.inputDigit(digit);
                updateDisplay();
            });
        }
    }

    private void bindOperatorButtons() {
        String[] operators = {
                "+", "-", "*", "/", "%"
        };

        for (String operator : operators) {
            view.getButton(operator).addActionListener(e -> {
                model.inputOperator(operator.charAt(0));
                updateDisplay();
            });
        }
    }

    private void bindSpecialButtons() {
        view.getButton("=").addActionListener(e -> {
            model.inputEquals();
            updateDisplay();
        });

        view.getButton("C").addActionListener(e -> {
            model.clear();
            updateDisplay();
        });

        view.getButton("DEL").addActionListener(e -> {
            model.delete();
            updateDisplay();
        });
    }

    private void updateDisplay() {
        view.setDisplayText(model.getDisplayText());
    }
}