## 📝 **Mô tả Dự án TodoList (Spring Boot)**

**TodoList** là một ứng dụng web RESTful được xây dựng bằng **Spring Boot** nhằm quản lý các công việc (todos). Ứng dụng hỗ trợ người dùng thực hiện các thao tác CRUD (Create, Read, Update, Delete) đối với các công việc, đồng thời tích hợp bảo mật và xử lý ngoại lệ cơ bản.

### 🚀 **Công nghệ sử dụng**

* **Java 21**
* **Spring Boot 3.3.3** (Spring Web, Spring Data JPA, Spring Security)
* **Hibernate JPA**
* **MySQL** làm cơ sở dữ liệu
* **Lombok** để giảm boilerplate code
* **Maven** để quản lý dependencies

### 📌 **Các tính năng chính**

* **Xem danh sách todos**
* **Tìm kiếm todo theo ID hoặc tiêu đề**
* **Thêm todo mới**
* **Cập nhật thông tin todo**
* **Xóa todo**
* **Xử lý lỗi (TodoNotFoundException, ...)**
* **Cấu hình bảo mật cơ bản với Spring Security**
* **Tạo dữ liệu mẫu tự động với DataLoader**

### 🗂 **Cấu trúc dự án**

```
com.example.todolist
├── controller         # REST API endpoints
├── entity             # Định nghĩa entity Todo
├── repository         # Giao tiếp với database (JPA Repository)
├── service            # Business logic xử lý todos
├── exception          # Xử lý lỗi tùy chỉnh
├── security           # Cấu hình bảo mật Spring Security
├── DataLoader.java    # Load dữ liệu ban đầu
└── App.java           # Main Application
```

### 🧩 **API Endpoints**

| HTTP Method | Endpoint                  | Mô tả                      |
| ----------- | ------------------------- | -------------------------- |
| `GET`       | `/todos`                  | Lấy danh sách tất cả todos |
| `GET`       | `/todos/{id}`             | Lấy todo theo ID           |
| `GET`       | `/todos/search?title=...` | Tìm todo theo tiêu đề      |
| `POST`      | `/todos`                  | Tạo todo mới               |
| `PUT`       | `/todos/{id}`             | Cập nhật todo              |
| `DELETE`    | `/todos/{id}`             | Xóa todo                   |

---
