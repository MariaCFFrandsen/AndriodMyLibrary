package com.github.listsapp.view.main;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.listsapp.R;
import com.github.listsapp.util.Book;
import com.github.listsapp.viewmodel.SelectedBookViewModel;

import java.util.Objects;


public class fragment_bookdetails extends Fragment {

    private SelectedBookViewModel selectedBookViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        selectedBookViewModel = new ViewModelProvider(this).get(SelectedBookViewModel.class);
        selectedBookViewModel.init();
        SelectedBookViewModel.chosenBook.observe(getViewLifecycleOwner(), Observer-> {
            Book book = SelectedBookViewModel.getChosen();
            System.out.println(book.getTitle());
        });



        return inflater.inflate(R.layout.fragment_bookdetails, container, false);
    }
}