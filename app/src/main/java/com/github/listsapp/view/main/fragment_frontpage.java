package com.github.listsapp.view.main;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.listsapp.R;
import com.github.listsapp.view.main.adapters.LastestBooksAdapter;
import com.github.listsapp.util.Book;
import com.github.listsapp.viewmodel.LoginViewModel;

import java.util.ArrayList;

public class fragment_frontpage extends Fragment implements LastestBooksAdapter.OnListItemClickListener {


    RecyclerView lastestBooks;
    LastestBooksAdapter adapter;
    TextView textViewUsername;
    ImageView currentReadingBookCover;
    EditText editTextOnPage;
    EditText editTextTotalPageCount;
    AppCompatButton buttonUpdate;
    AppCompatButton buttonMore;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        lastestBooks = view.findViewById(R.id.home_recyclerview);
        lastestBooks.hasFixedSize();
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1,"Nu er det den her", R.drawable.bogplaceholder, "Read", true, 4, "Paolini"));
        books.add(new Book(2, "Eragon", R.drawable.bogplaceholder, "Read", true, 4, "Paolini"));
        books.add(new Book(3, "Eragon", R.drawable.bogplaceholder, "Read", true, 4, "Paolini"));


        adapter = new LastestBooksAdapter(books, this);
        lastestBooks.setLayoutManager(new LinearLayoutManager(getContext()));
        lastestBooks.setAdapter(adapter);

        //get text, edit

        textViewUsername = view.findViewById(R.id.textViewUsername);
        currentReadingBookCover = view.findViewById(R.id.imageView2);
        editTextOnPage = view.findViewById(R.id.editTextOnPage);
        editTextTotalPageCount = view.findViewById(R.id.editTextPageCount);
        buttonUpdate = view.findViewById(R.id.button_updatebookpagecount);
        buttonMore = view.findViewById(R.id.button_morecurrentbooks);
        buttonMore.setOnClickListener(v -> {

        });

        return view;
    }

    public void onListItemClick(int clickedItemIndex) {

        fragment_bookdetails book = new fragment_bookdetails();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, book);
        transaction.addToBackStack("book");
        transaction.commit();
    }



}