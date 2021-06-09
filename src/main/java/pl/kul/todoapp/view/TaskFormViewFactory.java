package pl.kul.todoapp.view;

import javafx.stage.Stage;

public class TaskFormViewFactory {
    public TaskFormView create(Stage stage) {
        return new TaskFormView(new TaskFormViewModel(),stage);
    }
}
