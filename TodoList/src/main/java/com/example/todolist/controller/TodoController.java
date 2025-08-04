package com.example.todolist.controller;

import com.example.todolist.entity.Todo;
import com.example.todolist.exception.TodoNotFoundException;
import com.example.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getTodos() {
        return todoService.getAllTodos();
    }

    @PostMapping
    public Todo addTodo(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

    @GetMapping("/search")
    public Todo getTodoByTitle(@RequestParam String title){
        return todoService.getTodoByTitle(title).orElseThrow(() -> new TodoNotFoundException("Todo title = " +title+ "không tồn tại"));
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable Long id){
        return todoService.getTodoById(id).orElseThrow(() -> new TodoNotFoundException(" Todo id = " +id + "không tồn tại"));
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo updatedTodo) {
        Todo existingTodo = todoService.getTodoById(id).orElseThrow(() -> new TodoNotFoundException(" Todo id = " +id + "không tồn tại"));
        existingTodo.setTitle(updatedTodo.getTitle());
        existingTodo.setDescription(updatedTodo.getDescription());
        existingTodo.setCompleted(updatedTodo.isCompleted());

        return todoService.saveTodo(existingTodo);
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoViaGet(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return "Đã xóa todo có id = " + id;
    }
}
