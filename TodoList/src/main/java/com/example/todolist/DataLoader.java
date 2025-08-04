package com.example.todolist;

import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(TodoRepository todoRepository) {
        return args -> {
            if (todoRepository.count() == 0) {
                todoRepository.save(new Todo(null, "Học Spring Boot", "Làm dự án TodoList", false, LocalDateTime.now()));
                todoRepository.save(new Todo(null, "Cà phê", "The cup", false, LocalDateTime.now()));
                todoRepository.save(new Todo(null, "Đọc sách", "Cách iu IT", true, LocalDateTime.now()));
                System.out.println("✅ Seed dữ liệu mẫu thành công!");
            } else {
                System.out.println("ℹ️ Dữ liệu đã tồn tại, bỏ qua seed.");
            }
        };
    }
}
