package com.github.listsapp.view.main;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.listsapp.R;
import com.github.listsapp.model.LibraryModel;
import com.github.listsapp.repository.dao.CurrentlyReadingDAO;
import com.github.listsapp.util.api.GBook;
import com.github.listsapp.view.main.adapters.LastestBooksAdapter;
import com.github.listsapp.util.Book;
import com.github.listsapp.viewmodel.CurrentlyReadingViewModel;
import com.github.listsapp.viewmodel.LoginViewModel;

import java.util.ArrayList;
import java.util.List;

public class fragment_frontpage extends Fragment implements LastestBooksAdapter.OnListItemClickListener {


    RecyclerView lastestBooks;
    LastestBooksAdapter adapter;
    TextView textViewUsername;
    ImageView currentReadingBookCover;
    EditText editTextOnPage;
    TextView editTextTotalPageCount;
    AppCompatButton buttonUpdate;
    AppCompatButton buttonMore;
    //private LiveData<List<Book>> currentlyReadingBooks;
    private CurrentlyReadingViewModel viewModel;
    private static Book item;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        editTextOnPage = view.findViewById(R.id.frontpage_editOnPage);
        editTextTotalPageCount = view.findViewById(R.id.frontpage_viewTotalpagecount);

        textViewUsername = view.findViewById(R.id.textViewUsername);
        currentReadingBookCover = view.findViewById(R.id.imageView2);

        buttonUpdate = view.findViewById(R.id.button_updatebookpagecount);
        buttonMore = view.findViewById(R.id.button_morecurrentbooks);

        viewModel = new ViewModelProvider(this).get(CurrentlyReadingViewModel.class);
        lastestBooks = view.findViewById(R.id.home_recyclerview);
        lastestBooks.hasFixedSize();

        adapter = new LastestBooksAdapter(getContext(), this);
        lastestBooks.setLayoutManager(new LinearLayoutManager(getContext()));
        lastestBooks.setAdapter(adapter);
        viewModel.getCurrentlyReadingList(LibraryModel.getUsername()).observe(getViewLifecycleOwner(), adapter::updateCurrentlyReadingBookList);

        viewModel.getBookMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Book>() {
            @Override
            public void onChanged(Book book) {
                if (book != null) {
                    fragment_frontpage.setBook(book);
                    textViewUsername.setText(book.getTitle());
                    Glide.with(getContext()).load(book.getImageUrl()).fitCenter().placeholder(R.drawable.coverplaceholder).into(currentReadingBookCover);
                    editTextOnPage.setText(String.valueOf(book.getOnPage()));
                    editTextTotalPageCount.setText(String.valueOf(book.getPagecount()));
                }

            }

        });

        buttonUpdate.setOnClickListener(v -> {
            //abandon book
            item.setReadStatus("unread");
            viewModel.editBook(item);

        });


        buttonUpdate.setOnClickListener(v -> {
            if (item != null) {
                int onPage = Integer.parseInt(editTextOnPage.getText().toString());
                if (onPage == item.getPagecount()) {
                    item.setOnPage(0);
                    item.setReadStatus("read");
                } else
                    item.setOnPage(onPage);
                viewModel.editBook(item);

            }

        });

        return view;
    }

    public void onListItemClick(int clickedItemIndex) {

    }

    public static void setBook(Book book) {
        item = book;
    }


}