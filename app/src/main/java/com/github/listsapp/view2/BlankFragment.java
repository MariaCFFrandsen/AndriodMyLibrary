package com.github.listsapp.view2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.listsapp.R;
import com.github.listsapp.model.LastestBooksAdapter;
import com.github.listsapp.util.Book;

import java.util.ArrayList;

public class BlankFragment extends Fragment {


    RecyclerView lastestBooks;
    LastestBooksAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_blank, container, false);

        lastestBooks = view.findViewById(R.id.home_recyclerview);
        lastestBooks.hasFixedSize();
        ArrayList<Book> books = new ArrayList<>();
        adapter = new LastestBooksAdapter(books);
        lastestBooks.setLayoutManager(new LinearLayoutManager(getContext()));
        lastestBooks.setAdapter(adapter);

        return view;
    }
}