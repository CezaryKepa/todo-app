package pl.kul.todoapp.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kul.todoapp.enums.TaskStatusEnum;
import pl.kul.todoapp.models.Tasks;
import pl.kul.todoapp.repositories.TaskRepository;
import pl.kul.todoapp.validators.impl.TaskValidatorImpl;

import javax.xml.bind.ValidationException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    TaskRepository taskRepository;

    @Mock
    TaskValidatorImpl taskValidator;

    @InjectMocks
    TaskServiceImpl tasksService;

    @Test
    void changeStatus() throws ValidationException {
        //given
        Long taskId = 1L;
        final Timestamp updateDate = Timestamp.from(Instant.now());
        Tasks taskEntity = Tasks.builder()
                .title("Testy")
                .description("Dopisac testy")
                .creationDate(Timestamp.from(Instant.now()))
                .status(TaskStatusEnum.TODO)
                .build();
        Tasks taskEntitySaved = Tasks.builder()
                .id(1L)
                .title("Testy")
                .description("Dopisac testy")
                .creationDate(Timestamp.from(Instant.now()))
                .updateDate(updateDate)
                .status(TaskStatusEnum.DONE)
                .build();
        given(taskRepository.findById(taskId)).willReturn(Optional.of(taskEntity));
        given(taskRepository.save(taskEntity)).willReturn(taskEntitySaved);

        //when
        Optional<Tasks> task = tasksService.changeStatus(taskId, TaskStatusEnum.DONE);

        //then
        then(taskRepository).should().findById(taskId);
        assertThat(task).isNotEmpty();
        assertThat(task.get().getTitle()).isEqualTo("Testy");
        assertThat(task.get().getStatus()).isEqualTo(TaskStatusEnum.DONE);
        assertThat(task.get().getUpdateDate()).isEqualTo(updateDate);
    }

}
