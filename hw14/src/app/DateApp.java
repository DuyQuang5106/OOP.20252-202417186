package app;

import controller.DateController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DateModel;
import view.DateView;

public class DateApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        DateModel model = new DateModel();
        DateView view = new DateView();
        new DateController(model, view);

        Scene scene = new Scene(view.getRoot(), 620, 430);
        primaryStage.setTitle("Date Display GUI 2");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
