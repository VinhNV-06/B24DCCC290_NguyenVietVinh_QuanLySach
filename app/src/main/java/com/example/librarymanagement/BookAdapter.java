package com.example.librarymanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.ArrayList;

public class BookAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Book> bookList;

    public BookAdapter(Context context, ArrayList<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @Override
    public int getCount() { return bookList.size(); }

    @Override
    public Object getItem(int position) { return bookList.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        }

        Book book = bookList.get(position);

        TextView tvTitle = convertView.findViewById(R.id.tvBookTitle);
        TextView tvAuthor = convertView.findViewById(R.id.tvBookAuthor);
        TextView tvCategory = convertView.findViewById(R.id.tvBookCategory);
        TextView tvStatus = convertView.findViewById(R.id.tvBookStatus);
        RatingBar rbRating = convertView.findViewById(R.id.rbBookRating);

        tvTitle.setText(book.getTitle());
        tvAuthor.setText("Tác giả: " + book.getAuthor());
        tvCategory.setText(book.getCategory());
        tvStatus.setText(book.getStatus());
        rbRating.setRating(book.getRating());

        return convertView;
    }
}