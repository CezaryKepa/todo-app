package pl.kul.todoapp.view;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class TasksView {
    private final TasksViewModel viewModel;

    public TasksView(Stage stage, TasksViewModel viewModel) {
        this.viewModel = viewModel;
        start(stage);

    }
    public void start(Stage primaryStage) {

        ListView<StackPane> todoListView = new ListView<>();
        ListView<StackPane> inProgressListView = new ListView<>();
        ListView<StackPane> doneListView = new ListView<>();

        todoListView.setItems(viewModel.getTodo());
        inProgressListView.setItems(viewModel.getInProgress());
        doneListView.setItems(viewModel.getDone());

        todoListView.setOnDragOver(event -> {
            if (event.getGestureSource() != todoListView && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });
        inProgressListView.setOnDragOver(event -> {
            if (event.getGestureSource() != inProgressListView && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });
        doneListView.setOnDragOver(event -> {
            if (event.getGestureSource() != doneListView && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        todoListView.setOnDragDropped(viewModel::setTodo);
        inProgressListView.setOnDragDropped(viewModel::setInProgress);
        doneListView.setOnDragDropped(viewModel::setDone);

        VBox panel = new VBox();
        panel.setPadding(new Insets(1));
        panel.setSpacing(1);


        HBox hBox = new HBox(todoListView, inProgressListView, doneListView);

        Button taskButton = new Button("add task");
        taskButton.setStyle("-fx-font-size: 2em; ");
        taskButton.setOnAction(event -> {
            TaskFormViewFactory taskFormViewFactory = new TaskFormViewFactory();
            taskFormViewFactory.create(primaryStage);

        });


        HBox buttons = new HBox(taskButton);
        panel.getChildren().addAll(
                hBox,
                buttons
        );

        Scene scene = new Scene(panel, 750, 800);
        primaryStage.setTitle("2D Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
