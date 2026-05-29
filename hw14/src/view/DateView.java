package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DateView {
    private final DatePicker datePicker = new DatePicker();
    private final ComboBox<String> formatComboBox = new ComboBox<>();
    private final TextField amountField = new TextField();
    private final RadioButton beforeRadio = new RadioButton("Before");
    private final RadioButton afterRadio = new RadioButton("After");
    private final ComboBox<String> unitComboBox = new ComboBox<>();
    private final Button applyButton = new Button("Apply");
    private final Label selectedDateValueLabel = new Label();
    private final Label resultValueLabel = new Label();
    private final Label errorLabel = new Label();
    private final BorderPane root = new BorderPane();

    public DateView() {
        initializeView();
    }

    private void initializeView() {
        selectedDateValueLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
        resultValueLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-size: 12;");

        formatComboBox.setItems(FXCollections.observableArrayList(
                "dd/MM/yyyy",
                "MM/dd/yyyy",
                "dd.MM.yyyy",
                "yyyy-MM-dd"
        ));
        formatComboBox.setValue("dd/MM/yyyy");

        unitComboBox.setItems(FXCollections.observableArrayList("Days", "Months", "Years"));
        unitComboBox.setValue("Days");

        ToggleGroup directionGroup = new ToggleGroup();
        beforeRadio.setToggleGroup(directionGroup);
        afterRadio.setToggleGroup(directionGroup);
        afterRadio.setSelected(true);

        amountField.setPromptText("Enter days/months/years");
        amountField.setPrefColumnCount(6);

        GridPane selectedPane = new GridPane();
        selectedPane.setHgap(12);
        selectedPane.setVgap(10);
        selectedPane.setPadding(new Insets(16));
        selectedPane.add(new Label("Base Date:"), 0, 0);
        selectedPane.add(datePicker, 1, 0);
        selectedPane.add(new Label("Date Format:"), 0, 1);
        selectedPane.add(formatComboBox, 1, 1);
        selectedPane.add(new Label("Selected Date:"), 0, 2);
        selectedPane.add(selectedDateValueLabel, 1, 2);

        HBox directionBox = new HBox(12, beforeRadio, afterRadio);
        directionBox.setAlignment(Pos.CENTER_LEFT);

        GridPane operationPane = new GridPane();
        operationPane.setHgap(12);
        operationPane.setVgap(10);
        operationPane.setPadding(new Insets(16));
        operationPane.add(new Label("Amount:"), 0, 0);
        operationPane.add(amountField, 1, 0);
        operationPane.add(new Label("Direction:"), 0, 1);
        operationPane.add(directionBox, 1, 1);
        operationPane.add(new Label("Unit:"), 0, 2);
        operationPane.add(unitComboBox, 1, 2);
        operationPane.add(applyButton, 1, 3);

        VBox resultPane = new VBox(10);
        resultPane.setPadding(new Insets(16));
        resultPane.getChildren().addAll(
                new Label("Result Date:"),
                resultValueLabel,
                errorLabel
        );

        VBox center = new VBox(14, selectedPane, operationPane, resultPane);
        center.setPadding(new Insets(12));
        root.setCenter(center);
    }

    public Parent getRoot() {
        return root;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public ComboBox<String> getFormatComboBox() {
        return formatComboBox;
    }

    public TextField getAmountField() {
        return amountField;
    }

    public RadioButton getBeforeRadio() {
        return beforeRadio;
    }

    public RadioButton getAfterRadio() {
        return afterRadio;
    }

    public ComboBox<String> getUnitComboBox() {
        return unitComboBox;
    }

    public Button getApplyButton() {
        return applyButton;
    }

    public Label getSelectedDateValueLabel() {
        return selectedDateValueLabel;
    }

    public Label getResultValueLabel() {
        return resultValueLabel;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }
}
