# 📝 TodoList - Spring Boot Project

## 📌 Giới thiệu

**TodoList** là một ứng dụng CRUD nhỏ được phát triển bằng **Spring Boot**, nhằm:

* Làm quen với cấu trúc dự án Spring Boot.
* Thực hành tạo **Controller, Service, Repository, Entity**.
* Kết nối với MySQL và triển khai các API cơ bản.

---

## Hiện trạng dự án

✔️ Đã hoàn thành:

* Cấu hình Spring Boot + Maven.
* Entity `Todo`.
* Repository `TodoRepository` sử dụng Spring Data JPA.
* Service `TodoService` để xử lý logic.
* Controller `TodoController` với các API CRUD cơ bản.
* File `application.properties.example` cho cấu hình mẫu DB.

❌ Chưa có:

* **Error Handling** (GlobalExceptionHandler).
* **Validation** (dùng `@Valid` và `javax.validation`).
* **Spring Security** (Authentication & Authorization).
* **Test cases** (JUnit, MockMvc).

---

## 📂 Cấu trúc dự án (tạm thời)

```
TodoList/
 ├── src/main/java/com/example/todolist/
 │   ├── controller/TodoController.java
 │   ├── entity/Todo.java
 │   ├── repository/TodoRepository.java
 │   └── service/TodoService.java
 ├── src/main/resources/
 │   └── application.properties.example
 └── pom.xml
```

---

## 🌐 API hiện có

* `GET /todos` → Lấy danh sách todo
* `GET /todos/{id}` → Lấy chi tiết todo
* `POST /todos` → Thêm todo
* `PUT /todos/{id}` → Cập nhật todo
* `DELETE /todos/{id}` → Xóa todo

