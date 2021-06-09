package pl.kul.todoapp.view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TaskFormView {
    private final TaskFormViewModel taskFormViewModel;

    public TaskFormView(TaskFormViewModel taskFormViewModel,Stage primaryStage) {
        this.taskFormViewModel = taskFormViewModel;
        start(primaryStage);
    }

    public void start(Stage primaryStage) {
        Label secondLabel = new Label("I'm a Label on new Window");

        TextField title = new TextField();
        TextField description = new TextField();
        VBox titleLayout = new VBox(title);
        VBox descriptionLayout = new VBox(description);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(gridPane.getRowCount(), new Label("Title:"), titleLayout);
        gridPane.addRow(gridPane.getRowCount(), new Label("Description:"), descriptionLayout);



        Scene secondScene = new Scene(gridPane, 230, 100);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Second Stage");
        newWindow.setScene(secondScene);

        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(primaryStage);

        // Set position of second window, related to primary window.
        newWindow.setX(primaryStage.getX() + 200);
        newWindow.setY(primaryStage.getY() + 100);

        newWindow.show();
    }
}
