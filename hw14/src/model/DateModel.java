package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DateModel {
    private final ObjectProperty<LocalDate> selectedDate = new SimpleObjectProperty<>(LocalDate.now());
    private final ObjectProperty<LocalDate> resultDate = new SimpleObjectProperty<>(LocalDate.now());
    private final StringProperty selectedDateFormat = new SimpleStringProperty("dd/MM/yyyy");
    private final StringProperty errorMessage = new SimpleStringProperty("");

    public ObjectProperty<LocalDate> selectedDateProperty() {
        return selectedDate;
    }

    public LocalDate getSelectedDate() {
        return selectedDate.get();
    }

    public void setSelectedDate(LocalDate selectedDate) {
        this.selectedDate.set(selectedDate);
    }

    public ObjectProperty<LocalDate> resultDateProperty() {
        return resultDate;
    }

    public LocalDate getResultDate() {
        return resultDate.get();
    }

    public void setResultDate(LocalDate resultDate) {
        this.resultDate.set(resultDate);
    }

    public StringProperty selectedDateFormatProperty() {
        return selectedDateFormat;
    }

    public String getSelectedDateFormat() {
        return selectedDateFormat.get();
    }

    public void setSelectedDateFormat(String format) {
        this.selectedDateFormat.set(format);
    }

    public StringProperty errorMessageProperty() {
        return errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage.get();
    }

    public void setErrorMessage(String message) {
        this.errorMessage.set(message);
    }

    public void calculateResult(int amount, Direction direction, DateUnit unit) {
        LocalDate current = getSelectedDate();
        if (current == null) {
            setErrorMessage("Please choose a base date.");
            return;
        }

        LocalDate result;
        if (direction == Direction.AFTER) {
            result = switch (unit) {
                case DAYS -> current.plusDays(amount);
                case MONTHS -> current.plusMonths(amount);
                case YEARS -> current.plusYears(amount);
            };
        } else {
            result = switch (unit) {
                case DAYS -> current.minusDays(amount);
                case MONTHS -> current.minusMonths(amount);
                case YEARS -> current.minusYears(amount);
            };
        }

        setErrorMessage("");
        setResultDate(result);
    }

    public String formatDate(LocalDate date) {
        if (date == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getSelectedDateFormat());
        return date.format(formatter);
    }
}
