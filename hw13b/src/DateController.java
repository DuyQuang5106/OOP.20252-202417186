package src;

public class DateController {
    private DateModel model;
    private DateView view;

    public DateController(DateModel model, DateView view) {
        this.model = model;
        this.view = view;

        bindEvents();
        updateView();
    }

    private void bindEvents() {
        view.getButton("LEFT").addActionListener(e -> {
            model.selectPreviousField();
            updateView();
        });

        view.getButton("RIGHT").addActionListener(e -> {
            model.selectNextField();
            updateView();
        });

        view.getButton("UP").addActionListener(e -> {
            model.increaseSelectedField();
            updateView();
        });

        view.getButton("DOWN").addActionListener(e -> {
            model.decreaseSelectedField();
            updateView();
        });

        view.getButton("SET").addActionListener(e -> {
            handleSetButton();
        });
    }

    private void handleSetButton() {
        String input = view.getInputValue();

        if (input == null || input.trim().isEmpty()) {
            view.showErrorMessage("Please enter a number.");
            return;
        }

        try {
            int value = Integer.parseInt(input.trim());
            model.setSelectedFieldValue(value);
            view.clearInputValue();
            updateView();
        } catch (NumberFormatException ex) {
            view.showErrorMessage("Invalid input. Please enter an integer.");
        }
    }

    private void updateView() {
        view.setDateText(model.getDisplayDate());
        view.setSelectedFieldText(model.getSelectedFieldName());
    }
}