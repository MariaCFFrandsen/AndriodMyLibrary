package com.github.listsapp.view.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.listsapp.R;
import com.github.listsapp.model.LastestBooksAdapter;
import com.github.listsapp.util.Book;
import com.github.listsapp.view.login.fragment_createuser;

import java.util.ArrayList;

public class fragment_frontpage extends Fragment implements LastestBooksAdapter.OnListItemClickListener {


    RecyclerView lastestBooks;
    LastestBooksAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        lastestBooks = view.findViewById(R.id.home_recyclerview);
        lastestBooks.hasFixedSize();
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Eragon", R.drawable.bogplaceholder, "Read", true, 4));
        books.add(new Book("Eragon", R.drawable.bogplaceholder, "Read", true, 4));

        adapter = new LastestBooksAdapter(books, this);
        lastestBooks.setLayoutManager(new LinearLayoutManager(getContext()));
        lastestBooks.setAdapter(adapter);

        return view;
    }

    public void onListItemClick(int clickedItemIndex) {
        int number = clickedItemIndex + 1;
        //Toast.makeText(this, "Pokemon Number: " + pokemonNumber, Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), "open page with book title " + number, Toast.LENGTH_SHORT).show();

        //fragment or new activity?
//        Intent intent = new Intent();
//        startActivity(intent);
        fragment_bookdetails book = new fragment_bookdetails();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.drawer_layout, book);
        transaction.addToBackStack("book");
        transaction.commit();
    }
}