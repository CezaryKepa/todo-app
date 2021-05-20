package pl.kul.todoapp.view;

import javafx.stage.Stage;

public class TasksViewFactory {
    public TasksView create(Stage stage) {
        return new TasksView(stage, new TasksViewModel());
    }
}
