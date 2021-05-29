package pl.kul.todoapp.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TasksView {
    private final TasksViewModel viewModel;

    public TasksView(Stage stage, TasksViewModel viewModel) {
        this.viewModel = viewModel;

        VBox panel = new VBox();
        panel.setPadding(new Insets(10));
        panel.setSpacing(10);

        stage.setScene(new Scene(panel));
        stage.sizeToScene();
        stage.show();
    }
}
