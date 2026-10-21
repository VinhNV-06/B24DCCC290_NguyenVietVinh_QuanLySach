package com.example.librarymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BookDetailActivity extends AppCompatActivity {
    private TextView tvTitleDetail, tvAuthorCategoryDetail, tvStatusDetail, tvNoteDetail;
    private RatingBar ratingBarDetail;
    private Button btnMarkRead, btnBack, btnDelete;
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        tvTitleDetail = findViewById(R.id.tvTitleDetail);
        tvAuthorCategoryDetail = findViewById(R.id.tvAuthorCategoryDetail);
        tvStatusDetail = findViewById(R.id.tvStatusDetail);
        tvNoteDetail = findViewById(R.id.tvNoteDetail);
        ratingBarDetail = findViewById(R.id.ratingBarDetail);
        btnMarkRead = findViewById(R.id.btnMarkRead);
        btnBack = findViewById(R.id.btnBack);
        btnDelete = findViewById(R.id.btnDelete);

        book = (Book) getIntent().getSerializableExtra("book");
        if (book != null) {
            tvTitleDetail.setText(book.getTitle());
            tvAuthorCategoryDetail.setText(book.getAuthor() + " · " + book.getCategory());
            tvStatusDetail.setText(book.getStatus());
            tvNoteDetail.setText(book.getNote().isEmpty() ? "Không có ghi chú" : book.getNote());
            ratingBarDetail.setRating(book.getRating());

            if (book.getStatus().equals("Đã đọc")) {
                btnMarkRead.setEnabled(false);
                btnMarkRead.setAlpha(0.5f);
            }
        }

        btnMarkRead.setOnClickListener(v -> {
            book.setStatus("Đã đọc");
            tvStatusDetail.setText("Đã đọc");
            btnMarkRead.setEnabled(false);
            btnMarkRead.setAlpha(0.5f);

            Intent intent = new Intent();
            intent.putExtra("action", "update");
            intent.putExtra("updatedBook", book);
            setResult(RESULT_OK, intent);
        });

        ratingBarDetail.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            if (fromUser) {
                book.setRating(rating);
                Intent intent = new Intent();
                intent.putExtra("action", "update");
                intent.putExtra("updatedBook", book);
                setResult(RESULT_OK, intent);
            }
        });

        btnBack.setOnClickListener(v -> finish());

        btnDelete.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("action", "delete");
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}