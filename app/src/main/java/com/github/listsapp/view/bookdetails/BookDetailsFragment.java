package com.github.listsapp.view.bookdetails;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.listsapp.R;
import com.github.listsapp.util.Book;
import com.github.listsapp.util.api.GBook;


public class BookDetailsFragment extends Fragment {

    //private SelectedBookViewModel selectedBookViewModel;
    //private Book book;
    private BookDetailsViewModel viewModel;
    private TextView title;
    private EditText author;
    private EditText owned;
    private EditText price;
    private EditText read;
    private EditText pagecount;
    private RatingBar bar;
    private Book book;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bookdetails, container, false);
        title = view.findViewById(R.id.booksdetails_title);
        author = view.findViewById(R.id.booksdetails_author);
        owned = view.findViewById(R.id.booksdetails_owned);
        price = view.findViewById(R.id.booksdetails_price);
        read = view.findViewById(R.id.booksdetails_read);
        pagecount = view.findViewById(R.id.booksdetais_pagecount);
        bar = view.findViewById(R.id.bookdetails_rating);
        ImageView imageView = view.findViewById(R.id.bookdetails_imageView);
        AppCompatButton edit = view.findViewById(R.id.button_editBook);
        AppCompatButton delete = view.findViewById(R.id.button_deleteBook);
        viewModel = new ViewModelProvider(this).get(BookDetailsViewModel.class);

        //selectedBookViewModel = new ViewModelProvider(this).get(SelectedBookViewModel.class);
        //selectedBookViewModel.init();
        BookDetailsViewModel.getChosenBook().observe(getViewLifecycleOwner(), new Observer<Book>() {
            @Override
            public void onChanged(Book book) {
                setBook(book);
                title.setText(book.getTitle());
                author.setText(book.getAuthor());
                if (book.isOwned()) {
                    owned.setText("Owned");
                } else {
                    owned.setText("Unowned");
                }
                price.setText(String.valueOf(book.getPrice()));
                read.setText(book.getReadStatus());
                price.setText(String.valueOf(book.getPrice()));
                pagecount.setText(String.valueOf(book.getPagecount()));
                bar.setRating(book.getRating());
                System.out.println(book.getImageUrl());
                Glide.with(getContext()).load(book.getImageUrl()).into(imageView);

            }
        });


        edit.setOnClickListener(v -> {
            Book book = new Book();

            book.setTitle(title.getText().toString());
            book.setAuthor(author.getText().toString());
            book.setReadStatus(read.getText().toString());
            book.setPagecount(Integer.parseInt(pagecount.getText().toString()));
            book.setRating(bar.getRating());
            book.setPrice(Double.parseDouble(price.getText().toString()));
            book.setName(this.book.getName());
            book.setImageUrl(this.book.getImageUrl());

            viewModel.editBook(book);
        });

        delete.setOnClickListener(v -> {
            Book book = new Book();

            book.setTitle(title.getText().toString());
            book.setAuthor(author.getText().toString());
            book.setReadStatus(read.getText().toString());
            book.setPagecount(Integer.parseInt(pagecount.getText().toString()));
            book.setRating(bar.getRating());
            book.setPrice(Double.parseDouble(price.getText().toString()));
            book.setName(this.book.getName());
            book.setImageUrl(this.book.getImageUrl());
            viewModel.deleteBook(book);
        });

        return view;


    }

    private void setBook(Book book) {
        this.book = book;
    }

}