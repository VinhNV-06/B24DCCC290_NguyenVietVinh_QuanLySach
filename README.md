
# Ứng dụng Quản lý Thư viện Sách Cá Nhân (LibraryManagement)

Ứng dụng di động Android được phát triển bằng **Java** và **XML**, giúp người dùng quản lý tủ sách cá nhân và theo dõi tiến độ đọc sách.

---

## 🚀 Các tính năng chính

1. **Đăng nhập (LoginActivity):**
    - Đăng nhập bằng tài khoản cố định (`admin` / `123456`).
    - Kiểm tra ràng buộc không được bỏ trống thông tin, báo lỗi trực quan khi sai sót.

2. **Danh sách sách & Thống kê (MainActivity):**
    - Hiển thị khối thống kê trực quan: Tổng số sách, Số sách đã đọc, Số sách chưa đọc xong.
    - Danh sách cuốn sách sử dụng `ListView` kết hợp `BookAdapter` tùy chỉnh (hiển thị tên, tác giả, thể loại, trạng thái, đánh giá số sao).
    - Chuyển sang màn hình thêm sách hoặc xem chi tiết.

3. **Thêm sách mới (AddBookActivity):**
    - Nhập thông tin sách (Tên sách, Tác giả, Thể loại qua `Spinner`, Tình trạng qua `RadioGroup`, Đánh giá qua `RatingBar`, Ghi chú).
    - Kiểm tra lỗi dữ liệu trống bằng `setError`.
    - Trả dữ liệu ngược về màn hình chính qua `Intent` và cập nhật lại thống kê.

4. **Chi tiết sách (BookDetailActivity):**
    - Xem toàn bộ thông tin chi tiết của sách.
    - Cho phép đánh dấu "Đã đọc", thay đổi số sao đánh giá hoặc xóa sách khỏi danh sách.

---

## 🛠️ Công nghệ sử dụng
- **Ngôn ngữ:** Java
- **Giao diện:** XML Layouts (ConstraintLayout, LinearLayout)
- **Điều hướng & Dữ liệu:** Intent, Serializable, ArrayList, BaseAdapter

---

## 👨‍💻 Tác giả
- **Họ và tên:** Nguyễn Viết Vinh
- **Mã sinh viên:** B24DCCC290

## 📂 Cấu trúc dự án
```text
LibraryManagement/
├── app/
│   ├── manifests/
│   │   └── AndroidManifest.xml
│   ├── kotlin+java/com/example/librarymanagement/
│   │   ├── Book.java
│   │   ├── BookAdapter.java
│   │   ├── LoginActivity.java
│   │   ├── MainActivity.java
│   │   ├── AddBookActivity.java
│   │   └── BookDetailActivity.java
│   └── res/
│       ├── layout/ (activity_login, activity_main, activity_add_book, activity_book_detail, item_book)
│       └── values/