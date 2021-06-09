package pl.kul.todoapp.validators.impl;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pl.kul.todoapp.enums.TaskStatusEnum;
import pl.kul.todoapp.models.Tasks;
import pl.kul.todoapp.validators.TaskValidator;

import javax.xml.bind.ValidationException;

@Component
public class TaskValidatorImpl implements TaskValidator {

    @Override
    public void validate(final String title, final String desc) throws ValidationException {
        validateTitle(title);
        validateDesc(desc);
    }

    @Override
    public void validateRequest(final Tasks task) throws ValidationException {
        if (ObjectUtils.isEmpty(task)) {
            throw new ValidationException("Task is empty");
        }
        if (ObjectUtils.isEmpty(task.getId())) {
            throw new ValidationException("Task ID is empty");
        }
    }

    @Override
    public boolean validateTitle(final String title) throws ValidationException {
        if (Strings.isEmpty(title)) {
            throw new ValidationException("Title is empty");
        }
        if (title.length() > 30) {
            throw new ValidationException("The length cannot exceed 30 letters");
        }
        return true;
    }

    @Override
    public boolean validateDesc(final String desc) throws ValidationException {
        if (Strings.isEmpty(desc)) {
            throw new ValidationException("Description is empty");
        }
        if (desc.length() > 150) {
            throw new ValidationException("The length cannot exceed 150 letters");
        }
        return true;
    }


    @Override
    public void validateStatusChange(final Long taskId, final TaskStatusEnum newStatus) throws ValidationException {
        if (ObjectUtils.isEmpty(taskId)) {
            throw new ValidationException("Task ID is empty");
        }
        if (taskId < 0L) {
            throw new ValidationException("Task ID is invalid");
        }
        if (ObjectUtils.isEmpty(newStatus)) {
            throw new ValidationException("Task status is empty");
        }
    }

    @Override
    public boolean validateStatus(final TaskStatusEnum status) {
        return !ObjectUtils.isEmpty(status);
    }

}
