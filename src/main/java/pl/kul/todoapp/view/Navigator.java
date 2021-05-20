package pl.kul.todoapp.view;

import javafx.stage.Stage;


public class Navigator {
    private final Stage primaryStage;
    private final TasksViewFactory tasksViewFactory;

    public Navigator(Stage primaryStage, TasksViewFactory tasksViewFactory) {
        this.primaryStage = primaryStage;
        this.tasksViewFactory = tasksViewFactory;
    }

    public void showMainWindow() {
        tasksViewFactory.create(primaryStage);
    }
}
