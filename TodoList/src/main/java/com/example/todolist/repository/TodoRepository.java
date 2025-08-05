package com.example.todolist.repository;

import com.example.todolist.entity.Todo;
import com.example.todolist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findByTitleAndUser(String title, User user);
    List<Todo> findAllByUser(User user);
    Optional<Todo> findByIdAndUser(Long id, User user);
}
