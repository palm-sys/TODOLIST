package controller;

import dto.TodoDTO;
import entity.Todo;
import lombok.extern.slf4j.Slf4j;
import service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
@Slf4j
// Vue 开发服务器地址
public class TodoController {

    private final TodoService todoService;

    // 获取所有待办事项
    @GetMapping
    public ResponseEntity<List<TodoDTO>> getAllTodos() {
        List<TodoDTO> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    // 获取单个待办事项
    @GetMapping("/{id}")
    public ResponseEntity<TodoDTO> getTodoById(@PathVariable Long id) {
        TodoDTO todo = todoService.getTodoById(id);
        return ResponseEntity.ok(todo);
    }

    // 创建待办事项
    @PostMapping
    public ResponseEntity<TodoDTO> createTodo(@Valid @RequestBody TodoDTO todoDTO) {
        log.info("xxxxxxxxxxx");
        //补充创建/更新时间
        todoDTO.setCreatedAt(LocalDateTime.now());
        todoDTO.setUpdatedAt(LocalDateTime.now());

        TodoDTO createdTodo = todoService.createTodo(todoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
    }

    // 更新待办事项
    @PutMapping("/{id}")
    public ResponseEntity<TodoDTO> updateTodo(
            @PathVariable Long id,
            @Valid @RequestBody TodoDTO todoDTO) {
        TodoDTO updatedTodo = todoService.updateTodo(id, todoDTO);
        return ResponseEntity.ok(updatedTodo);
    }

    // 删除待办事项
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    // 切换完成状态
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<TodoDTO> toggleComplete(@PathVariable Long id) {
        TodoDTO updatedTodo = todoService.toggleComplete(id);
        return ResponseEntity.ok(updatedTodo);
    }

    // 根据状态筛选
    @GetMapping("/status/{completed}")
    public ResponseEntity<List<TodoDTO>> getTodosByStatus(@PathVariable Boolean completed) {
        List<TodoDTO> todos = todoService.getTodosByStatus(completed);
        return ResponseEntity.ok(todos);
    }

    // 根据分类筛选
    @GetMapping("/category/{category}")
    public ResponseEntity<List<TodoDTO>> getTodosByCategory(
            @PathVariable Todo.Category category) {
        List<TodoDTO> todos = todoService.getTodosByCategory(category);
        return ResponseEntity.ok(todos);
    }

    // 按优先级排序
    @GetMapping("/sorted/priority")
    public ResponseEntity<List<TodoDTO>> getTodosOrderByPriority() {
        List<TodoDTO> todos = todoService.getTodosOrderByPriority();
        return ResponseEntity.ok(todos);
    }
}
