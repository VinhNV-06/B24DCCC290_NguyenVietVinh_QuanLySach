package com.example.librarymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView tvTotalBooks, tvReadBooks, tvUnreadBooks;
    private ListView lvBooks;
    private Button btnAddBook;
    private ArrayList<Book> bookList;
    private BookAdapter adapter;

    private static final int REQUEST_CODE_ADD = 100;
    private static final int REQUEST_CODE_DETAIL = 200;
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTotalBooks = findViewById(R.id.tvTotalBooks);
        tvReadBooks = findViewById(R.id.tvReadBooks);
        tvUnreadBooks = findViewById(R.id.tvUnreadBooks);
        lvBooks = findViewById(R.id.lvBooks);
        btnAddBook = findViewById(R.id.btnAddBook);

        // Khởi tạo dữ liệu mẫu ban đầu[cite: 1, 5]
        bookList = new ArrayList<>();
        bookList.add(new Book("Lập trình Android cơ bản", "Nguyễn Văn A", "Công nghệ", "Đang đọc", 4, "Sách dành cho người mới bắt đầu"));
        bookList.add(new Book("Đắc nhân tâm", "Dale Carnegie", "Kỹ năng sống", "Đã đọc", 5, "Hay và ý nghĩa"));
        bookList.add(new Book("Dế Mèn phiêu lưu ký", "Tô Hoài", "Văn học", "Chưa đọc", 0, "Truyện thiếu nhi kinh điển"));

        adapter = new BookAdapter(this, bookList);
        lvBooks.setAdapter(adapter);
        updateStatistics();

        btnAddBook.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD);
        });

        lvBooks.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            Book book = bookList.get(position);
            Intent intent = new Intent(MainActivity.this, BookDetailActivity.class);
            intent.putExtra("book", book);
            startActivityForResult(intent, REQUEST_CODE_DETAIL);
        });
    }

    private void updateStatistics() {
        int total = bookList.size();
        int read = 0;
        int unread = 0;
        for (Book b : bookList) {
            if (b.getStatus().equals("Đã đọc")) {
                read++;
            } else {
                unread++; // Gồm Chưa đọc và Đang đọc[cite: 1, 5]
            }
        }
        tvTotalBooks.setText(String.valueOf(total));
        tvReadBooks.setText(String.valueOf(read));
        tvUnreadBooks.setText(String.valueOf(unread));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == REQUEST_CODE_ADD) {
                Book newBook = (Book) data.getSerializableExtra("newBook");
                if (newBook != null) {
                    bookList.add(newBook);
                    adapter.notifyDataSetChanged();
                    updateStatistics();
                }
            } else if (requestCode == REQUEST_CODE_DETAIL) {
                String action = data.getStringExtra("action");
                if ("delete".equals(action)) {
                    if (selectedPosition != -1 && selectedPosition < bookList.size()) {
                        bookList.remove(selectedPosition);
                    }
                } else if ("update".equals(action)) {
                    Book updatedBook = (Book) data.getSerializableExtra("updatedBook");
                    if (selectedPosition != -1 && updatedBook != null) {
                        bookList.set(selectedPosition, updatedBook);
                    }
                }
                adapter.notifyDataSetChanged();
                updateStatistics();
            }
        }
    }
}