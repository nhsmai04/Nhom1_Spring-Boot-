package com.example.todolist.service;

import com.example.todolist.entity.Todo;
import com.example.todolist.entity.User;
import com.example.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo createTodo(Todo todo, User currentUser) {
        todo.setUser(currentUser);
        return todoRepository.save(todo);
    }

    public List<Todo> getTodosByUser(User user) {
        return todoRepository.findAllByUser(user);
    }

    public Optional<Todo> findTodoByIdForUser(Long id, User user) {
        return todoRepository.findByIdAndUser(id, user);
    }

    public void deleteTodoForUser(Long id, User user) {
        Todo todo = todoRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new SecurityException("Bạn không có quyền hoặc Todo không tồn tại"));
        todoRepository.delete(todo);
    }

    public Todo updateTodoForUser(Long id, Todo updatedTodo, User user) {
        Todo existingTodo = todoRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new SecurityException("Bạn không có quyền hoặc Todo không tồn tại"));

        existingTodo.setTitle(updatedTodo.getTitle());
        existingTodo.setDescription(updatedTodo.getDescription());
        existingTodo.setCompleted(updatedTodo.isCompleted());

        return todoRepository.save(existingTodo);
    }

    public Optional<Todo> findByTitleForUser(String title, User user){
        return todoRepository.findByTitleAndUser(title, user);
    }
}

