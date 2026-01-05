package repository;

import entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByCompleted(Boolean completed);
    List<Todo> findByCategory(Todo.Category category);
    List<Todo> findAllByOrderByPriorityAsc();
    List<Todo> findAllByOrderByDueDateAsc();
}
