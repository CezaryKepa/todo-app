package pl.kul.todoapp;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.kul.todoapp.view.Navigator;
import pl.kul.todoapp.view.TasksViewFactory;


public class FxApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Navigator navigator = new Navigator(
            primaryStage,
            new TasksViewFactory()
        );
        navigator.showMainWindow();
    }
}
