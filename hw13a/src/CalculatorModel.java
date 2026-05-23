package src;

public class CalculatorModel {
    private String currentInput;
    private char operator;
    private boolean waitingForSecondOperand;
    private double storedValue;
    private boolean justCalculated;
    private boolean error;

    public CalculatorModel() {
        clear();
    }

    public void inputDigit(String digit) {
        if (error) {
            clear();
        }

        if (justCalculated) {
            currentInput = "";
            storedValue = 0.0;
            operator = '\0';
            justCalculated = false;
        }

        if (waitingForSecondOperand) {
            currentInput = "";
            waitingForSecondOperand = false;
        }

        currentInput += digit;
    }

    public void inputOperator(char newOperator) {
        if (error) {
            return;
        }

        if (justCalculated) {
            operator = newOperator;
            currentInput = "";
            waitingForSecondOperand = true;
            justCalculated = false;
            return;
        }

        // Case 1: no current number
        if (currentInput.isEmpty()) {
            // Example: 12 + -
            // Replace the previous operator.
            if (waitingForSecondOperand) {
                operator = newOperator;
            }

            // Example: + at the start
            // Ignore it.
            return;
        }

        double inputValue = Double.parseDouble(currentInput);

        // Case 2: first operator, example: 12 +
        if (operator == '\0') {
            storedValue = inputValue;
        }

        // Case 3: chained operation, example: 12 + 3 +
        else {
            storedValue = calculate(storedValue, inputValue, operator);
        }

        operator = newOperator;
        currentInput = "";
        waitingForSecondOperand = true;
        justCalculated = false;
    }

    public void inputEquals() {
        if (error) {
            return;
        }

        // Example: pressing = at start, or 12 + =
        if (operator == '\0' || currentInput.isEmpty()) {
            return;
        }

        double inputValue = Double.parseDouble(currentInput);
        storedValue = calculate(storedValue, inputValue, operator);

        if (error) {
            currentInput = "";
            operator = '\0';
            waitingForSecondOperand = false;
            justCalculated = true;
            return;
        }

        currentInput = format(storedValue);
        operator = '\0';
        waitingForSecondOperand = false;
        justCalculated = true;
    }

    public void clear() {
        currentInput = "";
        operator = '\0';
        waitingForSecondOperand = false;
        storedValue = 0.0;
        justCalculated = false;
        error = false;
    }

    public void delete() {
        if (error || justCalculated || waitingForSecondOperand) {
            return;
        }

        if (!currentInput.isEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
        }
    }

    public String getDisplayText() {
        if (error) {
            return "Error";
        }

        if (!currentInput.isEmpty()) {
            return currentInput;
        }

        return format(storedValue);
    }

    private double calculate(double a, double b, char op) {
        if (op == '+') {
            return a + b;
        }

        if (op == '-') {
            return a - b;
        }

        if (op == '*') {
            return a * b;
        }

        if (op == '/') {
            if (b == 0) {
                error = true;
                return 0.0;
            }

            return a / b;
        }
        
        if (op == '%') // Modulo
        {
        	if(b == 0)
        	{
        		error = true;
        		return 0.0;
        	}
        		return a % b;
        }
        	
        
        return b;
    }

    private String format(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value);
        }

        return String.valueOf(value);
    }
}