package com.example.todolist;

import com.example.todolist.entity.Todo;
import com.example.todolist.entity.User;
import com.example.todolist.repository.TodoRepository;
import com.example.todolist.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

import static com.example.todolist.entity.Role.*;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository,
                                   TodoRepository todoRepository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {
                User admin = new User(null, "admin", passwordEncoder.encode("admin123"), "admin@example.com", ROLE_ADMIN);
                User user = new User(null, "user", passwordEncoder.encode("oc123"), "oc@example.com", ROLE_USER);
                userRepository.save(admin);
                userRepository.save(user);
                System.out.println("✅ Seed user mẫu thành công!");
            }

            if (todoRepository.count() == 0) {
                User user = userRepository.findByName("user").orElseThrow();
                todoRepository.save(new Todo(null, "Học Spring Boot", "Làm dự án TodoList", false, LocalDateTime.now(), user));
                todoRepository.save(new Todo(null, "Cà phê", "The cup", false, LocalDateTime.now(), user));
                todoRepository.save(new Todo(null, "Đọc sách", "Cách yêu IT", true, LocalDateTime.now(), user));
                System.out.println("✅ Seed todo mẫu thành công!");
            } else {
                System.out.println("ℹ️ Dữ liệu đã tồn tại, bỏ qua seed.");
            }
        };
    }
}
