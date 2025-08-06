### 📄 **Mô tả dự án TodoList với Spring Boot**

#### 1. **Giới thiệu**

Ứng dụng **TodoList** được xây dựng bằng **Spring Boot** với tính năng quản lý công việc (Todo) và phân quyền người dùng. Hệ thống bao gồm 2 vai trò chính:

* **USER**: người dùng thông thường, có thể quản lý công việc của chính mình.
* **ADMIN**: quản trị viên, có quyền quản lý người dùng và xem toàn bộ công việc.

---

#### 2. **Công nghệ sử dụng**

* **Spring Boot** (REST API)
* **Spring Security** (xác thực và phân quyền)
* **Spring Data JPA** (tương tác cơ sở dữ liệu)
* **MySQL** (lưu trữ dữ liệu)
* **BCrypt** (mã hóa mật khẩu)

---

#### 3. **Chức năng của ứng dụng**

##### **Đối với USER**

* **Đăng nhập** bằng username và password.
* **Xem danh sách công việc** của chính mình tại `/todos`.
* **Thêm Todo** mới bằng cách gửi POST request đến `/todos`.
* **Tìm kiếm Todo theo tiêu đề** qua endpoint `/todos/search?title=...`.
* **Xem chi tiết Todo theo ID** với `/todos/{id}`.
* **Cập nhật thông tin Todo** bằng PUT `/todos/{id}`.
* **Xóa Todo** của chính mình với DELETE `/todos/{id}`.

##### **Đối với ADMIN**

* **Xem toàn bộ danh sách người dùng** tại `/admin/users`.
* **Xóa người dùng** theo ID bằng DELETE `/admin/delete/users/{id}`.
* **Cập nhật quyền (role) của người dùng** qua PUT `/admin/user/{id}/role?newRole=ROLE_ADMIN/ROLE_USER`.
* **Xem toàn bộ Todo** của mọi user (trong `/todos`).

---

#### 4. **Quản lý bảo mật (Spring Security)**

* Người dùng đăng nhập thông qua **form login** của Spring Security.
* Role được ánh xạ trực tiếp từ enum `Role` (`ROLE_USER`, `ROLE_ADMIN`).
* Chỉ ADMIN mới được truy cập các endpoint `/admin/**`.
* Chỉ USER đã đăng nhập mới được thao tác với `/todos/**`.

---

#### 5. **Luồng hoạt động**

1. Người dùng đăng nhập → Spring Security xác thực thông tin.
2. Sau khi đăng nhập thành công:

   * USER được chuyển hướng đến `/todos` để quản lý công việc cá nhân.
   * ADMIN có thể truy cập thêm `/admin/**` để quản trị hệ thống.
3. Các request liên quan Todo đều dựa vào `currentUser` để lọc dữ liệu đúng người dùng.

---

#### 6. **Seeder dữ liệu mẫu**

Ứng dụng tự động seed:

* 1 Admin (`admin/admin123`)
* 1 User (`user/oc123`)
* 3 Todo mẫu cho user `"user"`
