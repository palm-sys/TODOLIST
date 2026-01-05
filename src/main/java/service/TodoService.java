package service;
import dto.TodoDTO;
import entity.Todo;
import repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    // 获取所有待办事项
    public List<TodoDTO> getAllTodos() {
        return todoRepository.findAll().stream()
                .map(TodoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 获取单个待办事项
    public TodoDTO getTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("待办事项不存在"));
        return TodoDTO.fromEntity(todo);
    }

    // 创建待办事项
    @Transactional
    public TodoDTO createTodo(TodoDTO todoDTO) {
        Todo todo = todoDTO.toEntity();
        Todo savedTodo = todoRepository.save(todo);
        return TodoDTO.fromEntity(savedTodo);
    }

    // 更新待办事项
    @Transactional
    public TodoDTO updateTodo(Long id, TodoDTO todoDTO) {
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("待办事项不存在"));

        existingTodo.setTitle(todoDTO.getTitle());
        existingTodo.setDescription(todoDTO.getDescription());
        existingTodo.setCompleted(todoDTO.getCompleted());
        existingTodo.setCategory(todoDTO.getCategory());
        existingTodo.setPriority(todoDTO.getPriority());
        existingTodo.setDueDate(todoDTO.getDueDate());

        Todo updatedTodo = todoRepository.save(existingTodo);
        return TodoDTO.fromEntity(updatedTodo);
    }

    // 删除待办事项
    @Transactional
    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new RuntimeException("待办事项不存在");
        }
        todoRepository.deleteById(id);
    }

    // 切换完成状态
    @Transactional
    public TodoDTO toggleComplete(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("待办事项不存在"));

        todo.setCompleted(!todo.getCompleted());
        Todo updatedTodo = todoRepository.save(todo);
        return TodoDTO.fromEntity(updatedTodo);
    }

    // 根据状态筛选
    public List<TodoDTO> getTodosByStatus(Boolean completed) {
        return todoRepository.findByCompleted(completed).stream()
                .map(TodoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 根据分类筛选
    public List<TodoDTO> getTodosByCategory(Todo.Category category) {
        return todoRepository.findByCategory(category).stream()
                .map(TodoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 按优先级排序
    public List<TodoDTO> getTodosOrderByPriority() {
        return todoRepository.findAllByOrderByPriorityAsc().stream()
                .map(TodoDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
