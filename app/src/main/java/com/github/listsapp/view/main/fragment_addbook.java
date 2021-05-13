package com.github.listsapp.view.main;

import android.media.Rating;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Switch;

import com.github.listsapp.R;
import com.github.listsapp.model.LibraryModel;
import com.github.listsapp.util.Book;
import com.github.listsapp.viewmodel.LibraryViewModel;
import com.github.listsapp.viewmodel.LoginViewModel;

import java.sql.Time;
import java.sql.Timestamp;

public class fragment_addbook extends Fragment {

    RatingBar ratingBar;
    private AppCompatButton button_save;
    private EditText editText_title;
    private EditText editText_author;
    private EditText editText_pagecount;
    private Switch switch_owned;
    private EditText editText_readstatus;
    private EditText editText_price;
    private LibraryViewModel libraryViewModel;

    public fragment_addbook() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        libraryViewModel = new ViewModelProvider(this).get(LibraryViewModel.class);
        View view = inflater.inflate(R.layout.fragment_addbook, container, false);
        editText_title = view.findViewById(R.id.addbook_edit_title);
        editText_author = view.findViewById(R.id.addbook_edit_author);
        editText_price = view.findViewById(R.id.addbook_edit_price);
        editText_readstatus = view.findViewById(R.id.addbook_edit_read);
        switch_owned = view.findViewById(R.id.addbook_switch_owned);
        editText_pagecount = view.findViewById(R.id.addbook_edit_pageCount);
        ratingBar = view.findViewById(R.id.ratingbaraddbook);
        button_save = view.findViewById(R.id.button_save);

        button_save.setOnClickListener(v -> {
            saveAddedBook();
        });

        return view;
    }

    private void saveAddedBook() {

        Book book = new Book();
        book.setTitle(editText_title.getText().toString());
        book.setAuthor(editText_author.getText().toString());
        book.setReadStatus(editText_readstatus.getText().toString());
        book.setOwned(switch_owned.isChecked());
        book.setPrice(Integer.parseInt(editText_price.getText().toString()));
        book.setPagecount(Integer.parseInt(editText_pagecount.getText().toString()));
        book.setRating(ratingBar.getRating());
        String username = LibraryModel.getUsername();

        libraryViewModel.addBook(book, username);


    }
}