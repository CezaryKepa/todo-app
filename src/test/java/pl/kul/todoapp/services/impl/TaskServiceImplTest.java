package pl.kul.todoapp.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kul.todoapp.enums.TaskStatusEnum;
import pl.kul.todoapp.models.Tasks;
import pl.kul.todoapp.repositories.TaskRepository;
import pl.kul.todoapp.validators.impl.TaskValidatorImpl;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
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
    void create() {
        //given
        final Long taskId = 1L;
        final Timestamp creationDate = Timestamp.from(Instant.now());
        final String title = "Testy";
        final String description = "Dopisac testy";
        Tasks taskCreate = Tasks.builder()
                .title(title)
                .description(description)
                .status(TaskStatusEnum.TODO)
                .build();
        Tasks taskEntityNew = Tasks.builder()
                .id(taskId)
                .title(title)
                .description(description)
                .creationDate(creationDate)
                .status(TaskStatusEnum.TODO)
                .build();
        given(taskRepository.save(taskCreate)).willReturn(taskEntityNew);

        //when
        Optional<Tasks> task = Assertions.assertDoesNotThrow(
                () -> tasksService.create(title, description));

        //then
        then(taskRepository).should().save(taskCreate);
        assertThat(task).isNotEmpty();
        assertThat(task.get().getTitle()).isEqualTo(title);
        assertThat(task.get().getDescription()).isEqualTo(description);
        assertThat(task.get().getStatus()).isEqualTo(TaskStatusEnum.TODO);
        assertThat(task.get().getCreationDate()).isToday();
        assertThat(task.get().getUpdateDate()).isNull();
    }

    @Test
    void get() {
        //given
        final Long taskId = 1L;
        final Timestamp creationDate = Timestamp
                .valueOf(LocalDateTime.of(2021, 1, 1, 10, 10, 31));
        Tasks taskByIdEntity = Tasks.builder()
                .id(taskId)
                .title("Testy")
                .description("Dopisac testy")
                .creationDate(creationDate)
                .status(TaskStatusEnum.TODO)
                .build();
        given(taskRepository.findById(taskId)).willReturn(Optional.of(taskByIdEntity));

        //when
        Optional<Tasks> task = Assertions.assertDoesNotThrow(
                () -> tasksService.get(taskId));

        //then
        then(taskRepository).should().findById(taskId);
        assertThat(task).isNotEmpty();
        assertThat(task.get().getTitle()).isEqualTo("Testy");
        assertThat(task.get().getDescription()).isEqualTo("Dopisac testy");
        assertThat(task.get().getStatus()).isEqualTo(TaskStatusEnum.TODO);
        assertThat(task.get().getCreationDate()).isEqualTo(creationDate);
        assertThat(task.get().getUpdateDate()).isNull();
    }

    @Test
    void update() {
        //given
        final Long taskId = 1L;
        final Timestamp creationDate = Timestamp
                .valueOf(LocalDateTime.of(2021, 1, 1, 10, 10, 31));
        Tasks taskByIdEntity = Tasks.builder()
                .id(taskId)
                .title("Testy")
                .description("Dopisac testy")
                .creationDate(creationDate)
                .status(TaskStatusEnum.TODO)
                .build();
        Tasks taskEntityNew = Tasks.builder()
                .id(taskId)
                .title("Nowy tytul")
                .description("Nowa tresc")
                .creationDate(creationDate)
                .updateDate(Timestamp.from(Instant.now()))
                .status(TaskStatusEnum.IN_PROGRESS)
                .build();
        given(taskRepository.findById(taskId)).willReturn(Optional.of(taskByIdEntity));
        given(taskRepository.save(taskEntityNew)).willReturn(taskEntityNew);

        //when
        Optional<Tasks> task = Assertions.assertDoesNotThrow(
                () -> tasksService.update(taskEntityNew));

        //then
        then(taskRepository).should().findById(taskId);
        then(taskRepository).should().save(taskEntityNew);
        assertThat(task).isNotEmpty();
        assertThat(task.get().getTitle()).isEqualTo("Nowy tytul");
        assertThat(task.get().getDescription()).isEqualTo("Nowa tresc");
        assertThat(task.get().getStatus()).isEqualTo(TaskStatusEnum.IN_PROGRESS);
        assertThat(task.get().getCreationDate()).isEqualTo(creationDate);
        assertThat(task.get().getUpdateDate()).isToday();
    }

    @Test
    void changeStatus() {
        //given
        final Long taskId = 1L;
        final Timestamp creationDate = Timestamp
                .valueOf(LocalDateTime.of(2021, 1, 1, 10, 10, 31));
        Tasks taskEntity = Tasks.builder()
                .id(taskId)
                .title("Testy")
                .description("Dopisac testy")
                .creationDate(creationDate)
                .status(TaskStatusEnum.TODO)
                .build();
        Tasks taskEntitySaved = Tasks.builder()
                .id(taskId)
                .title("Testy")
                .description("Dopisac testy")
                .creationDate(creationDate)
                .updateDate(Timestamp.from(Instant.now()))
                .status(TaskStatusEnum.DONE)
                .build();
        given(taskRepository.findById(taskId)).willReturn(Optional.of(taskEntity));
        given(taskRepository.save(taskEntity)).willReturn(taskEntitySaved);

        //when
        Optional<Tasks> task = Assertions.assertDoesNotThrow(
                () -> tasksService.changeStatus(taskId, TaskStatusEnum.DONE));

        //then
        then(taskRepository).should().findById(taskId);
        then(taskRepository).should().save(taskEntity);
        assertThat(task).isNotEmpty();
        assertThat(task.get().getTitle()).isEqualTo("Testy");
        assertThat(task.get().getStatus()).isEqualTo(TaskStatusEnum.DONE);
        assertThat(task.get().getCreationDate()).isEqualTo(creationDate);
        assertThat(task.get().getUpdateDate()).isToday();
    }

    @Test
    void delete() {
        //given
        final Long taskId = 1L;

        //when
        Assertions.assertDoesNotThrow(
                () -> tasksService.delete(taskId));

        //then
        then(taskRepository).should().deleteById(taskId);
        assertThat(taskRepository.findById(taskId)).isNotPresent();
    }

}
