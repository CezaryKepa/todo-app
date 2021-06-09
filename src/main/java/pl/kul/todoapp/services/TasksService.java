package pl.kul.todoapp.services;

import pl.kul.todoapp.enums.TaskStatusEnum;
import pl.kul.todoapp.models.Tasks;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface TasksService {

    Tasks create(final String title, final String desc) throws ValidationException;

    Tasks get(final long taskId);

    List<Tasks> getAll();

    Tasks update(final Tasks requestTask) throws ValidationException;

    Tasks changeStatus(final Long taskId, final TaskStatusEnum newTaskStatus) throws ValidationException;

    void delete(final Long taskId);

}
