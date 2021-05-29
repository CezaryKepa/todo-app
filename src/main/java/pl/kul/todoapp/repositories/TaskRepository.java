package pl.kul.todoapp.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.kul.todoapp.models.Tasks;

public interface TaskRepository extends CrudRepository<Tasks, Long> {
}
