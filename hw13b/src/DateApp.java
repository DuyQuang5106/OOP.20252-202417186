package src;

import javax.swing.SwingUtilities;

public class DateApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DateModel model = new DateModel();
            DateView view = new DateView();
            new DateController(model, view);

            view.show();
        });
    }
}