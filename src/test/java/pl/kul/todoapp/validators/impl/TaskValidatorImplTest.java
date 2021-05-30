package pl.kul.todoapp.validators.impl;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.kul.todoapp.enums.TaskStatusEnum;
import pl.kul.todoapp.models.Tasks;

import javax.xml.bind.ValidationException;
import java.sql.Timestamp;
import java.time.Instant;

class TaskValidatorImplTest {

    final String OVER_30_SIGNS = "OVER_30_SIGNS_12345678901234567";
    final String OVER_150_SIGNS = "OVER_150_SIGNS_123456789012345678901234567890123456789012345678901234" +
            "5678901234567890123456789012345678901234567890123456789012345678901234567890123456";

    static TaskValidatorImpl taskValidator;
    static Tasks taskEntity;

    @BeforeAll
    static void init() {
        taskValidator = new TaskValidatorImpl();

        taskEntity = Tasks.builder()
                .id(1L)
                .title("Testy")
                .description("Dopisac testy")
                .creationDate(Timestamp.from(Instant.now()))
                .status(TaskStatusEnum.TODO)
                .build();
    }

    @Test
    @DisplayName("validRequest")
    void validRequest() {
        Assertions.assertDoesNotThrow(() -> taskValidator.validateRequest(taskEntity));
    }

    @Test
    @DisplayName("valid")
    void valid() {
        Assertions.assertDoesNotThrow(
                () -> taskValidator.validate(taskEntity.getTitle(), taskEntity.getDescription()));
        Assertions.assertThrows(ValidationException.class,
                () -> taskValidator.validate(Strings.EMPTY, taskEntity.getDescription()));
        Assertions.assertThrows(ValidationException.class,
                () -> taskValidator.validate(taskEntity.getTitle(), Strings.EMPTY));
        Assertions.assertThrows(ValidationException.class,
                () -> taskValidator.validate(OVER_30_SIGNS, taskEntity.getDescription()));
        Assertions.assertThrows(ValidationException.class,
                () -> taskValidator.validate(taskEntity.getTitle(), OVER_150_SIGNS));
    }

    @Test
    @DisplayName("validTitle")
    void validTitle() {
        Assertions.assertDoesNotThrow(
                () -> taskValidator.validateTitle(taskEntity.getTitle()));
        Assertions.assertThrows(ValidationException.class,
                () -> taskValidator.validateTitle(Strings.EMPTY));
        Assertions.assertThrows(ValidationException.class,
                () -> taskValidator.validateTitle(OVER_30_SIGNS));
    }

    @Test
    @DisplayName("validDesc")
    void validDesc() {
        Assertions.assertDoesNotThrow(
                () -> taskValidator.validateDesc(taskEntity.getDescription()));
        Assertions.assertThrows(ValidationException.class,
                () -> taskValidator.validateDesc(Strings.EMPTY));
        Assertions.assertThrows(ValidationException.class,
                () -> taskValidator.validateDesc(OVER_150_SIGNS));
    }

    @Test
    @DisplayName("validStatus")
    void validStatus() {
        Assertions.assertDoesNotThrow(
                () -> taskValidator.validateStatus(taskEntity.getStatus()));
        Assertions.assertFalse(
                () -> taskValidator.validateStatus(null));
        Assertions.assertDoesNotThrow(
                () -> taskValidator.validateStatusChange(taskEntity.getId(), taskEntity.getStatus()));
        Assertions.assertThrows(ValidationException.class,
                () -> taskValidator.validateStatusChange(-25L, taskEntity.getStatus()));
        Assertions.assertThrows(ValidationException.class,
                () -> taskValidator.validateStatusChange(null, taskEntity.getStatus()));
        Assertions.assertThrows(ValidationException.class,
                () -> taskValidator.validateStatusChange(taskEntity.getId(), null));
    }

    @Test
    @DisplayName("creationDateBeforeNow")
    void validCreationDate() {
        Assertions.assertTrue(() -> taskEntity.getCreationDate().before(Timestamp.from(Instant.now())));
    }

}
