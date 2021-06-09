package pl.kul.todoapp.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.kul.todoapp.enums.TaskStatusEnum;
import pl.kul.todoapp.models.Tasks;
import pl.kul.todoapp.repositories.TaskRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TasksBootStrap implements CommandLineRunner {

    private final TaskRepository taskRepository;

    @Override
    public void run(String... args) throws Exception {
        Tasks taskTodo = Tasks.builder()
                .title("testTODO")
                .id(1L)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .updateDate(Timestamp.valueOf(LocalDateTime.now()))
                .status(TaskStatusEnum.TODO)
                .description("TEST")
                .build();
        Tasks taskInProgress = Tasks.builder()
                .title("testInProgress")
                .id(2L)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .updateDate(Timestamp.valueOf(LocalDateTime.now()))
                .status(TaskStatusEnum.IN_PROGRESS)
                .description("TEST")
                .build();
        Tasks taskDone = Tasks.builder()
                .title("testDone")
                .id(3L)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .updateDate(Timestamp.valueOf(LocalDateTime.now()))
                .status(TaskStatusEnum.DONE)
                .description("tes")
                .build();
        Tasks task2Done = Tasks.builder()
                .title("testDone2")
                .id(4L)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .updateDate(Timestamp.valueOf(LocalDateTime.now()))
                .status(TaskStatusEnum.DONE)
                .description("tes")
                .build();
        List<Tasks> tasks = new ArrayList<>();

        tasks.add(taskTodo);
        tasks.add(taskInProgress);
        tasks.add(taskDone);
        tasks.add(task2Done);

        taskRepository.saveAll(tasks);
    }
}
