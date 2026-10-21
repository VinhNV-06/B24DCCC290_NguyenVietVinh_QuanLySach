package com.example.librarymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class AddBookActivity extends AppCompatActivity {
    private EditText edtTitle, edtAuthor, edtNote;
    private Spinner spinnerCategory;
    private RadioGroup rgStatus;
    private RatingBar ratingBar;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        edtTitle = findViewById(R.id.edtTitle);
        edtAuthor = findViewById(R.id.edtAuthor);
        edtNote = findViewById(R.id.edtNote);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        rgStatus = findViewById(R.id.rgStatus);
        ratingBar = findViewById(R.id.ratingBar);
        btnSave = findViewById(R.id.btnSave);

        String[] categories = {"Văn học", "Khoa học", "Công nghệ", "Kỹ năng sống", "Khác"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        spinnerCategory.setAdapter(adapter);

        btnSave.setOnClickListener(v -> {
            String title = edtTitle.getText().toString().trim();
            String author = edtAuthor.getText().toString().trim();
            String category = spinnerCategory.getSelectedItem().toString();
            String note = edtNote.getText().toString().trim();
            float rating = ratingBar.getRating();

            if (title.isEmpty()) {
                edtTitle.setError("Tên sách không được để trống");
                return;
            }
            if (author.isEmpty()) {
                edtAuthor.setError("Tác giả không được để trống");
                return;
            }

            String status = "Chưa đọc";
            int selectedId = rgStatus.getCheckedRadioButtonId();
            if (selectedId == R.id.rbReading) {
                status = "Đang đọc";
            } else if (selectedId == R.id.rbRead) {
                status = "Đã đọc";
            }

            Book book = new Book(title, author, category, status, rating, note);
            Intent resultIntent = new Intent();
            resultIntent.putExtra("newBook", book);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}