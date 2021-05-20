package pl.kul.todoapp.view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
