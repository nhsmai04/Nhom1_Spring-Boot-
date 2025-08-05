package com.example.todolist.controller;

import com.example.todolist.entity.Role;
import com.example.todolist.entity.User;
import com.example.todolist.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(User user) {
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete/users/{id}")
    public String deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "Đã xóa user có id: " +id;
    }

    @PutMapping("/user/{id}/role")
    public User updateUserRole(@PathVariable Long id, @RequestParam Role newRole) {
        return userService.updateUserRole(id, newRole);
    }
}
