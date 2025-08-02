# Nhom1_Spring-Boot - Library Book Management

Ứng dụng quản lý sách thư viện đơn giản, được xây dựng bằng **Spring Boot + Spring Security + Thymeleaf** để demo các chủ đề quan trọng trong phát triển web hiện đại.

---

## ✅ Mục tiêu kỹ thuật

Dự án này dùng để demo các nội dung sau:

### 🔐 1. Cấu hình bảo mật Spring Security

- Xác thực người dùng bằng form login (`/login`)
- Phân quyền dựa trên role (`ROLE_USER`, `ROLE_ADMIN`)
- Chặn URL theo quyền (ví dụ: `/api/books/**` chỉ admin mới truy cập)
  ✅ **Đã hoàn thành**

---

### 🔄 2. Xây dựng RESTful API (GET, POST, PUT, DELETE)

- `/api/books` – Lấy danh sách sách (GET)
- `/api/books/{id}` – Lấy chi tiết 1 sách (GET)
- `/api/books` – Thêm sách (POST)
- `/api/books/{id}` – Cập nhật sách (PUT)
- `/api/books/{id}` – Xoá sách (DELETE)
  🟡 **Đang triển khai**

---

### 📡 3. Sử dụng đúng HTTP Status Codes

Ứng dụng áp dụng các mã HTTP theo chuẩn REST:

| Mã                 | Ý nghĩa                      | Khi nào dùng?             |
| ------------------ | ---------------------------- | ------------------------- |
| `200 OK`           | Thành công                   | GET, PUT thành công       |
| `201 Created`      | Tạo mới thành công           | POST tạo sách             |
| `204 No Content`   | Xoá thành công               | DELETE                    |
| `400 Bad Request`  | Dữ liệu đầu vào không hợp lệ | Form trống, sai định dạng |
| `401 Unauthorized` | Chưa đăng nhập               | Truy cập API chưa login   |
| `403 Forbidden`    | Không đủ quyền               | Đăng nhập nhưng sai role  |
| `404 Not Found`    | Không tìm thấy               | ID sách không tồn tại     |

✅ **Đang triển khai**

---

### 🧑‍💻 4. Giao diện động với Thymeleaf

- Trang chủ hiển thị sách nổi bật & mới cập nhật
- Trang đăng nhập tùy chỉnh (`login.html`)
- View chia fragment: `header.html`, `footer.html`, `latest-books.html`
- Sử dụng `th:each`, `th:if`, `th:replace`, `th:action`, `th:field`...
- Tích hợp bảo mật vào view (`sec:authorize` – sẽ thêm sau)
  ✅ **Đã hoàn thành**

---

## 💡 Công nghệ sử dụng

- Spring Boot
- Spring Security
- Spring MVC + Thymeleaf
- Lombok
- JPA + MySQL
- Bootstrap-style CSS tùy chỉnh

---

## 🏗️ Cấu trúc dự án

```
src/
├── main/
│   ├── java/
│   │   └── com/example/library/
│   │       ├── config/           # Cấu hình ban đầu (InitUsersConfig,...)
│   │       ├── controller/       # Web & REST controllers
│   │       ├── service/          # Business logic
│   │       ├── repository/       # JPA Repositories
│   │       ├── model/            # Entity classes
│   │       └── security/         # Security config
│   └── resources/
│       ├── templates/            # Thymeleaf HTML templates
│       └── static/               # CSS, JS, Images
└── DemoApplication.java          # Main Spring Boot class
```

---

## 📌 Ghi chú

- Tài khoản mặc định:
  - admin / admin123 → ROLE_ADMIN
  - user / user123 → ROLE_USER
- Có thể cấu hình thêm user mới qua form đăng ký (tùy chọn sau)
