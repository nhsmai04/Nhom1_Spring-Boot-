package com.example.todolist.controller;

import com.example.todolist.entity.Role;
import com.example.todolist.entity.Todo;
import com.example.todolist.entity.User;
import com.example.todolist.exception.TodoNotFoundException;
import com.example.todolist.service.TodoService;
import com.example.todolist.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public List<Todo> getTodos() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User không tồn tại"));

        if (currentUser.getRole() == Role.ROLE_ADMIN) {
            return todoService.getAllTodos();
        }
        return todoService.getTodosByUser(currentUser);
    }


    @PostMapping
    public Todo addTodo(@AuthenticationPrincipal User currentUser, @RequestBody Todo todo) {
        User user = userService.findByName(currentUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User không tồn tại"));

        return todoService.createTodo(todo, user);
    }

    @GetMapping("/search")
    public Todo getTodoByTitle(@RequestParam String title, @AuthenticationPrincipal User currentUser) {

        if (currentUser == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bạn chưa đăng nhập");
        }
        return todoService.findByTitleForUser(title, currentUser)
                .orElseThrow(() -> new TodoNotFoundException("Todo có title: " + title + " không tồn tại"));
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable Long id, @AuthenticationPrincipal(expression = "username") String username) {

        User user = userService.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User không tồn tại"));
        return todoService.findTodoByIdForUser(id, user)
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
