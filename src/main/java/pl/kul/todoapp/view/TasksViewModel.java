package pl.kul.todoapp.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import pl.kul.todoapp.TodoAppApplication;
import pl.kul.todoapp.enums.TaskStatusEnum;
import pl.kul.todoapp.models.Tasks;
import pl.kul.todoapp.services.TaskService;


import java.util.List;

public class TasksViewModel {
    List<Tasks> tasks;

    TaskService taskService = TodoAppApplication.ctx.getBean(TaskService.class);

    public TasksViewModel() {
        this.tasks = taskService.getAll();
    }

    ObservableList<StackPane> observableTodoList = FXCollections.observableArrayList();
    ObservableList<StackPane> observableDoneList = FXCollections.observableArrayList();
    ObservableList<StackPane> observableInProgressList = FXCollections.observableArrayList();


    public ObservableList<StackPane> getTodo() {
        tasks.stream()
                .filter(task -> task.getStatus() == TaskStatusEnum.TODO)
                .forEach(task -> observableTodoList.add(buildStackPane(task)));

        return observableTodoList;
    }

    public ObservableList<StackPane> getInProgress() {
        tasks.stream()
                .filter(task -> task.getStatus() == TaskStatusEnum.IN_PROGRESS)
                .forEach(task -> observableInProgressList.add(buildStackPane(task)));
        return observableInProgressList;
    }

    public ObservableList<StackPane> getDone() {
        tasks.stream()
                .filter(task -> task.getStatus() == TaskStatusEnum.DONE)
                .forEach(task -> observableDoneList.add(buildStackPane(task)));

        return observableDoneList;
    }


    private StackPane buildStackPane(Tasks task) {
        StackPane stp= new StackPane();
        Rectangle rectangle = createRectangle("#ff00ff", "#ff88ff");
        Text text = new Text(task.getTitle());
        stp.getChildren().addAll(rectangle, text);
        stp.setOnDragDetected((MouseEvent event) -> {
            Image image = new Image("https://image.flaticon.com/icons/png/128/733/733579.png");
            Dragboard db = stp.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString(String.valueOf(task.getId()));
            db.setContent(content);
            db.setDragView(image);
        });
        stp.setOnMouseDragged((MouseEvent event) -> {
            event.setDragDetect(true);
        });
        return stp;
    }

    private Rectangle createRectangle(String strokeColor, String fillColor) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(50);
        rectangle.setWidth(230);
        rectangle.setStroke(Color.valueOf(strokeColor));
        rectangle.setStrokeWidth(5);
        rectangle.setFill(Color.valueOf(fillColor));
        return rectangle;
    }

    public void setTodo(DragEvent event) {
        StackPane stackPane = (StackPane) event.getGestureSource();

        if (!observableTodoList.contains(stackPane)) {
            observableInProgressList.remove(stackPane);
            observableDoneList.remove(stackPane);

            observableTodoList.add(stackPane);
            event.setDropCompleted(true);
        } else {
            event.setDropCompleted(false);
        }
        event.consume();
    }

    public void setInProgress(DragEvent event) {
        StackPane stackPane = (StackPane) event.getGestureSource();

        if (!observableInProgressList.contains(stackPane)) {
            observableDoneList.remove(stackPane);
            observableTodoList.remove(stackPane);

            observableInProgressList.add(stackPane);
            event.setDropCompleted(true);
        } else {
            event.setDropCompleted(false);
        }
        event.consume();
    }

    public void setDone(DragEvent event) {
        StackPane stackPane = (StackPane) event.getGestureSource();

        if (!observableDoneList.contains(stackPane)) {
            observableInProgressList.remove(stackPane);
            observableTodoList.remove(stackPane);

            observableDoneList.add(stackPane);
            event.setDropCompleted(true);
        } else {
            event.setDropCompleted(false);
        }
        event.consume();
    }
}
