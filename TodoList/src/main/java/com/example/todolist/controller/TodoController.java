package com.example.todolist.controller;

import com.example.todolist.entity.Role;
import com.example.todolist.entity.Todo;
import com.example.todolist.entity.User;
import com.example.todolist.exception.TodoNotFoundException;
import com.example.todolist.service.TodoService;
import com.example.todolist.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;
    private final UserService userService;

    public TodoController(TodoService todoService, UserService userService) {
        this.todoService = todoService;
        this.userService = userService;
    }

    @GetMapping
    public List<Todo> getTodos(@AuthenticationPrincipal User currentUser) {
        if (currentUser.getRole() == Role.ADMIN) {
            return todoService.getAllTodos();
        }
        return todoService.getTodosByUser(currentUser);
    }


    @PostMapping
    public Todo addTodo(@AuthenticationPrincipal User currentUser, @RequestBody Todo todo) {
        User user = userService.findByUsername(currentUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User không tồn tại"));

        return todoService.createTodo(todo, user);
    }

    @GetMapping("/search")
    public Todo getTodoByTitle(@RequestParam String title, @AuthenticationPrincipal User currentUser) {
        return todoService.findByTitleForUser(title, currentUser)
                .orElseThrow(() -> new TodoNotFoundException("Todo có title: " + title + " không tồn tại"));
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable Long id, @AuthenticationPrincipal User currentUser) {
        return todoService.findTodoByIdForUser(id, currentUser)
                .orElseThrow(() -> new TodoNotFoundException("Todo có id: " + id + " không tồn tại"));
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo updatedTodo, @AuthenticationPrincipal User currentUser) {
        return todoService.updateTodoForUser(id, updatedTodo, currentUser);
    }

    @DeleteMapping("/{id}")
    public String deleteTodo(@PathVariable Long id, @AuthenticationPrincipal User currentUser) {
        todoService.deleteTodoForUser(id, currentUser);
        return "Đã xóa todo có id = " + id;
    }
}
