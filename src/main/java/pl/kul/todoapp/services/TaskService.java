package pl.kul.todoapp.services;

import pl.kul.todoapp.enums.TaskStatusEnum;
import pl.kul.todoapp.models.Tasks;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

public interface TaskService {

    Optional<Tasks> create(final String title, final String desc) throws ValidationException;

    Optional<Tasks> get(final long taskId);

    List<Tasks> getAll();

    Optional<Tasks> update(final Tasks requestTask) throws ValidationException;

    Optional<Tasks> changeStatus(final Long taskId, final TaskStatusEnum newTaskStatus) throws ValidationException;

    void delete(final Long taskId);

}
