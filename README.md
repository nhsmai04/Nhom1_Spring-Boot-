# Spring Boot Project - Book Management System

Đây là một dự án Spring Boot để quản lý hệ thống sách, với các tính năng CRUD cho người dùng, sách, tác giả, và nhà xuất bản. Dự án sử dụng các mô hình quan hệ, xác thực người dùng và các API trả về dữ liệu dưới dạng JSON.

## Các mô hình (Models)

1. **Book**:
   - Quan hệ nhiều-nhiều với **Author** thông qua bảng **BookAuthor**.
   - Quan hệ một-nhiều với **Publisher**.
2. **Author**:
   - Quan hệ nhiều-nhiều với **Book** thông qua bảng **BookAuthor**.
3. **BookAuthor**: Bảng trung gian giữa **Book** và **Author**.
4. **MyUser**: Mô hình người dùng.
5. **Publisher**: Mô hình nhà xuất bản.

## Các DTOs

1. **UserDto**:
   - Dùng để chứa các trường cần thiết của người dùng và sử dụng validation.

## Các Repository

1. **BookRepository**: Thực hiện các chức năng CRUD cho mô hình **Book**.
2. **UserRepository**: Thực hiện các chức năng CRUD và tìm kiếm người dùng.
3. **PublisherRepository**: Thực hiện các chức năng CRUD cho mô hình **Publisher**.

## Các Service

### Interface

- **UserService**: Interface chứa các phương thức logic cho người dùng.

### Implementations

- **UserServiceImpl**: Cài đặt các logic cho người dùng.
- **PublisherImpl**: Cài đặt các logic cho nhà xuất bản.

## Các Controller

- **api**: Thực hiện điều hướng và trả về API (JSON).
- **web**: Thực hiện điều hướng và trả về HTML (MVC).

## Các Configuration

1. **AppConfig**: Cấu hình **ModelMapper**.
2. **SecurityConfig**: Cấu hình bảo mật.

## Các Exception

1. **GlobalException**: Xử lý lỗi toàn cục.
2. **NotFoundException**: Exception tùy chỉnh để xử lý trường hợp không tìm thấy.
3. **ValidationException**: Xử lý lỗi validation.
4. **DuplicatedException**: Exception tùy chỉnh để xử lý lỗi trùng lặp.

## Các Response

1. **ResponseObject**: Trả về kiểu JSON chứa các nội dung:

   - `message`: Thông báo.
   - `status`: Trạng thái.
   - `data`: Dữ liệu trả về.

2. **ValidationObject**: Trả về kiểu JSON chứa các nội dung:
   - `status`: Trạng thái.
   - `error`: Thông báo lỗi.
   - `fieldErrors`: Map chứa các lỗi từng trường.

## Các Tệp Template (resources/templates)

- **index.html**: Trang chủ.
- **new.html**: Trang thêm người dùng mới.
- **publishers.html**: Trang hiển thị các nhà xuất bản.
- **edit.html**: Trang cập nhật thông tin người dùng.

## Các Tệp Cấu Hình

- **application.properties**: Cấu hình database, JPA, web.

## Chức Năng Chính

1. **CRUD Người Dùng**: Thực hiện các chức năng CRUD đối với người dùng, trả về cả API và web.
2. **Xác Thực Người Dùng và Quyền**: Cấu hình xác thực người dùng và xác thực quyền truy cập.

## Cài Đặt và Chạy Dự Án

1. **Cài đặt Dependencies**:

   - Đảm bảo rằng bạn đã cài đặt JDK 11+ và Maven.
   - Clone dự án về máy:
     ```bash
     git clone <repository-url>
     ```
   - Di chuyển đến thư mục dự án:
     ```bash
     cd <project-directory>
     ```
   - Cài đặt dependencies:
     ```bash
     mvn clean install
     ```

2. **Cấu hình Application**:

   - Cấu hình **application.properties** để kết nối với database.

3. **Chạy Dự Án**:
   ```bash
   mvn spring-boot:run
   ```
