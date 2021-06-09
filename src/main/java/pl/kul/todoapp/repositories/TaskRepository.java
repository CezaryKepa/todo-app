package pl.kul.todoapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kul.todoapp.models.Tasks;

public interface TaskRepository extends JpaRepository<Tasks, Long> {
}
