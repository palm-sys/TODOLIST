package dto;
import entity.Todo;
import entity.Todo;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class TodoDTO {
    private Long id;

    @NotBlank(message = "标题不能为空")
    private String title;

    private String description;

    private Boolean completed = false;

    private Todo.Category category = Todo.Category.OTHER;

    private Integer priority = 3;

    private LocalDateTime dueDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // 转换方法
    public static TodoDTO fromEntity(Todo todo) {
        TodoDTO dto = new TodoDTO();
        dto.setId(todo.getId());
        dto.setTitle(todo.getTitle());
        dto.setDescription(todo.getDescription());
        dto.setCompleted(todo.getCompleted());
        dto.setCategory(todo.getCategory());
        dto.setPriority(todo.getPriority());
        dto.setDueDate(todo.getDueDate());
        dto.setCreatedAt(todo.getCreatedAt());
        dto.setUpdatedAt(todo.getUpdatedAt());
        return dto;
    }

    public Todo toEntity() {
        Todo todo = new Todo();
        todo.setId(this.id);
        todo.setTitle(this.title);
        todo.setDescription(this.description);
        todo.setCompleted(this.completed);
        todo.setCategory(this.category);
        todo.setPriority(this.priority);
        todo.setDueDate(this.dueDate);
        return todo;
    }
}
