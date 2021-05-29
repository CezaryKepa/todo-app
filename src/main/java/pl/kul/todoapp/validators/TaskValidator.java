package pl.kul.todoapp.validators;

import pl.kul.todoapp.enums.TaskStatusEnum;
import pl.kul.todoapp.models.Tasks;

import javax.xml.bind.ValidationException;

public interface TaskValidator {

    void validate(final String title, final String desc) throws ValidationException;

    void validateRequest(final Tasks task) throws ValidationException;

    void validateStatusChange(final Long taskId, final TaskStatusEnum newStatus) throws ValidationException;

    boolean validateTitle(final String title) throws ValidationException;

    boolean validateDesc(final String desc) throws ValidationException;

    boolean validateStatus(final TaskStatusEnum status);

}
