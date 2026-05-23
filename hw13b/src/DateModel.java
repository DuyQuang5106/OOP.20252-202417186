package src;
import java.time.LocalDate;
public class DateModel {
    private int day;
    private int month;
    private int year;
    private DateField selectedField;

    public DateModel() {
        LocalDate currentDate = LocalDate.now();

        this.day = currentDate.getDayOfMonth();
        this.month = currentDate.getMonthValue();
        this.year = currentDate.getYear();

        this.selectedField = DateField.DAY;
    }

    public DateModel(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.selectedField = DateField.DAY;
        normalizeDate();
    }

    public void selectNextField() {
        if (selectedField == DateField.DAY) {
            selectedField = DateField.MONTH;
        } else if (selectedField == DateField.MONTH) {
            selectedField = DateField.YEAR;
        } else {
            selectedField = DateField.DAY;
        }
    }

    public void selectPreviousField() {
        if (selectedField == DateField.DAY) {
            selectedField = DateField.YEAR;
        } else if (selectedField == DateField.MONTH) {
            selectedField = DateField.DAY;
        } else {
            selectedField = DateField.MONTH;
        }
    }

    public void increaseSelectedField() {
        if (selectedField == DateField.DAY) {
            day++;
        } else if (selectedField == DateField.MONTH) {
            month++;
        } else if (selectedField == DateField.YEAR) {
            year++;
        }

        normalizeDate();
    }

    public void decreaseSelectedField() {
        if (selectedField == DateField.DAY) {
            day--;
        } else if (selectedField == DateField.MONTH) {
            month--;
        } else if (selectedField == DateField.YEAR) {
            year--;
        }

        normalizeDate();
    }

    public void setSelectedFieldValue(int value) {
        if (selectedField == DateField.DAY) {
            day = value;
        } else if (selectedField == DateField.MONTH) {
            month = value;
        } else if (selectedField == DateField.YEAR) {
            year = value;
        }

        normalizeDate();
    }

    public String getDisplayDate() {
        return String.format("%02d / %02d / %04d", day, month, year);
    }

    public String getSelectedFieldName() {
        if (selectedField == DateField.DAY) {
            return "DAY";
        }

        if (selectedField == DateField.MONTH) {
            return "MONTH";
        }

        return "YEAR";
    }

    public DateField getSelectedField() {
        return selectedField;
    }

    private void normalizeDate() {
        if (year < 1) {
            year = 1;
        }

        if (month < 1) {
            month = 12;
            year--;
            if (year < 1) {
                year = 1;
            }
        }

        if (month > 12) {
            month = 1;
            year++;
        }

        int maxDay = getMaxDayOfMonth(month, year);

        if (day < 1) {
            month--;

            if (month < 1) {
                month = 12;
                year--;
                if (year < 1) {
                    year = 1;
                }
            }

            day = getMaxDayOfMonth(month, year);
        }

        if (day > maxDay) {
            day = maxDay;
        }
    }

    private int getMaxDayOfMonth(int month, int year) {
        if (month == 1 || month == 3 || month == 5 || month == 7 ||
                month == 8 || month == 10 || month == 12) {
            return 31;
        }

        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }

        if (isLeapYear(year)) {
            return 29;
        }

        return 28;
    }

    private boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        }

        if (year % 100 == 0) {
            return false;
        }

        return year % 4 == 0;
    }
}