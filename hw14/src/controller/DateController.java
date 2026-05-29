package controller;

import java.time.LocalDate;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringBinding;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import model.DateModel;
import model.DateUnit;
import model.Direction;
import view.DateView;

public class DateController {
    private final DateModel model;
    private final DateView view;

    public DateController(DateModel model, DateView view) {
        this.model = model;
        this.view = view;
        initialize();
    }

    private void initialize() {
        DatePicker datePicker = view.getDatePicker();
        ComboBox<String> formatComboBox = view.getFormatComboBox();
        ComboBox<String> unitComboBox = view.getUnitComboBox();
        TextField amountField = view.getAmountField();
        RadioButton beforeRadio = view.getBeforeRadio();
        RadioButton afterRadio = view.getAfterRadio();
        Button applyButton = view.getApplyButton();

        datePicker.setValue(model.getSelectedDate());
        formatComboBox.setItems(FXCollections.observableArrayList(
                "dd/MM/yyyy",
                "MM/dd/yyyy",
                "dd.MM.yyyy",
                "yyyy-MM-dd"
        ));
        formatComboBox.setValue(model.getSelectedDateFormat());
        unitComboBox.setItems(FXCollections.observableArrayList("Days", "Months", "Years"));
        unitComboBox.setValue("Days");

        ToggleGroup directionGroup = new ToggleGroup();
        beforeRadio.setToggleGroup(directionGroup);
        afterRadio.setToggleGroup(directionGroup);
        afterRadio.setSelected(true);

        datePicker.valueProperty().addListener((obs, oldDate, newDate) -> {
            if (newDate != null) {
                model.setSelectedDate(newDate);
            }
        });

        formatComboBox.valueProperty().addListener((obs, oldFormat, newFormat) -> {
            if (newFormat != null) {
                model.setSelectedDateFormat(newFormat);
            }
        });

        StringBinding selectedDateText = Bindings.createStringBinding(() -> {
            LocalDate date = model.getSelectedDate();
            return date == null ? "" : model.formatDate(date);
        }, model.selectedDateProperty(), model.selectedDateFormatProperty());
        view.getSelectedDateValueLabel().textProperty().bind(selectedDateText);

        StringBinding resultDateText = Bindings.createStringBinding(() -> {
            LocalDate date = model.getResultDate();
            return date == null ? "" : model.formatDate(date);
        }, model.resultDateProperty(), model.selectedDateFormatProperty());
        view.getResultValueLabel().textProperty().bind(resultDateText);

        amountField.addEventFilter(KeyEvent.KEY_TYPED, e -> {
            String character = e.getCharacter();
            if (!character.matches("[0-9]")) {
                e.consume();
            }
        });

        BooleanBinding inputValid = Bindings.createBooleanBinding(() -> {
            String text = amountField.getText();
            if (text == null || text.isBlank()) {
                model.setErrorMessage("Enter a positive integer amount.");
                return false;
            }
            try {
                int value = Integer.parseInt(text);
                if (value <= 0) {
                    model.setErrorMessage("Amount must be greater than zero.");
                    return false;
                }
            } catch (NumberFormatException ex) {
                model.setErrorMessage("Enter a valid integer amount.");
                return false;
            }
            model.setErrorMessage("");
            return true;
        }, amountField.textProperty());

        applyButton.disableProperty().bind(inputValid.not());
        view.getErrorLabel().textProperty().bind(model.errorMessageProperty());

        applyButton.setOnAction(event -> {
            if (!inputValid.get()) {
                return;
            }
            int amount = Integer.parseInt(amountField.getText());
            Direction direction = beforeRadio.isSelected() ? Direction.BEFORE : Direction.AFTER;
            DateUnit unit = switch (unitComboBox.getValue()) {
                case "Months" -> DateUnit.MONTHS;
                case "Years" -> DateUnit.YEARS;
                default -> DateUnit.DAYS;
            };
            model.calculateResult(amount, direction, unit);
        });
    }
}
