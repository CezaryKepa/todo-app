package pl.kul.todoapp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kul.todoapp.enums.TaskStatusEnum;
import pl.kul.todoapp.models.Tasks;
import pl.kul.todoapp.repositories.TaskRepository;
import pl.kul.todoapp.services.TaskService;
import pl.kul.todoapp.validators.TaskValidator;

import javax.xml.bind.ValidationException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskValidator taskValidator;

    @Autowired
    public TaskServiceImpl(final TaskRepository taskRepository, final TaskValidator taskValidator) {
        this.taskRepository = taskRepository;
        this.taskValidator = taskValidator;
    }

    @Override
    public Optional<Tasks> create(final String title, final String desc) throws ValidationException {
        taskValidator.validate(title, desc);
        Tasks task = Tasks.builder()
                .title(title)
                .description(desc)
                .status(TaskStatusEnum.TODO)
                .build();
        return Optional.of(taskRepository.save(task));
    }

    @Override
    public Optional<Tasks> get(final long taskId) {
        return taskRepository.findById(taskId);
    }

    @Override
    public Optional<Tasks> update(final Tasks requestTask) throws ValidationException {
        taskValidator.validateRequest(requestTask);
        Tasks task = taskRepository.findById(requestTask.getId()).orElseThrow();
        updateTaskValues(requestTask, task);
        return Optional.of(taskRepository.save(requestTask));
    }

    @Override
    public Optional<Tasks> changeStatus(final Long taskId, final TaskStatusEnum newTaskStatus) throws ValidationException {
        taskValidator.validateStatusChange(taskId, newTaskStatus);
        Tasks task = taskRepository.findById(taskId).orElseThrow();
        task.setStatus(newTaskStatus);
        return Optional.of(taskRepository.save(task));
    }

    @Override
    public void delete(final Long taskId) {
        taskRepository.deleteById(taskId);
    }

    private void updateTaskValues(final Tasks source, Tasks destination) throws ValidationException {
        if (taskValidator.validateDesc(source.getDescription())) {
            destination.setDescription(source.getDescription());
        }
        if (taskValidator.validateTitle(source.getTitle())) {
            destination.setTitle(source.getTitle());
        }
        if (taskValidator.validateStatus(source.getStatus())) {
            destination.setTitle(source.getTitle());
        }
        destination.setUpdateDate(Timestamp.from(Instant.now()));
    }

    @Override
    public List<Tasks> getAll() {
        return taskRepository.findAll();
    }
}
