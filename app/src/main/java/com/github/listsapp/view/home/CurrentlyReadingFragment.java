package com.github.listsapp.view.home;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.listsapp.R;
import com.github.listsapp.repository.Repository;
import com.github.listsapp.util.callback.CallBack;
import com.github.listsapp.util.Book;

public class CurrentlyReadingFragment extends Fragment implements CurrentlyReadingAdapter.OnListItemClickListener {


    RecyclerView lastestBooks;
    CurrentlyReadingAdapter adapter;
    TextView textViewUsername;
    ImageView currentReadingBookCover;
    EditText editTextOnPage;
    TextView editTextTotalPageCount;
    AppCompatButton buttonUpdate;
    AppCompatButton buttonDelete;
    private CurrentlyReadingViewModel viewModel;
    private static Book item;
    private int index = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_currentlyreading, container, false);
        editTextOnPage = view.findViewById(R.id.frontpage_editOnPage);
        editTextTotalPageCount = view.findViewById(R.id.frontpage_viewTotalpagecount);

        textViewUsername = view.findViewById(R.id.textViewUsername);
        currentReadingBookCover = view.findViewById(R.id.imageView2);

        buttonUpdate = view.findViewById(R.id.button_updatebookpagecount);
        buttonDelete = view.findViewById(R.id.button_morecurrentbooks);
        lastestBooks = view.findViewById(R.id.home_recyclerview);
        lastestBooks.hasFixedSize();

        //initialize viewmodel
        viewModel = new ViewModelProvider(this).get(CurrentlyReadingViewModel.class);

        //adapter for recyclerview
        adapter = new CurrentlyReadingAdapter(getContext(), this);
        lastestBooks.setLayoutManager(new LinearLayoutManager(getContext()));
        lastestBooks.setAdapter(adapter);

        //retrieving user's library
        viewModel.getCurrentlyReadingList(Repository.getUsername()).observe(getViewLifecycleOwner(), adapter::updateCurrentlyReadingBookList);

        //observe on the livedata containing user's library
        viewModel.getBookMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Book>() {
            @Override
            public void onChanged(Book book) {
                if (book != null) {
                    CurrentlyReadingFragment.setBook(book);
                    textViewUsername.setText(book.getTitle());
                    Glide.with(getContext()).load(book.getImageUrl()).fitCenter().placeholder(R.drawable.coverplaceholder).into(currentReadingBookCover);
                    editTextOnPage.setText(String.valueOf(book.getOnPage()));
                    editTextTotalPageCount.setText(String.valueOf(book.getPagecount()));
                }

            }

        });

        //set onClickListeners for buttons
        buttonDelete.setOnClickListener(v -> {
            //abandon book
            item.setReadStatus("unread");
            viewModel.editBook(item, new CallBack() {
                @Override
                public void makeToast(String message) {
                    Toast.makeText(getContext(), item.getTitle() + " has been set to unread", Toast.LENGTH_SHORT).show();
                }
            });

            clearFields();

            adapter.getBooks().remove(index);
            adapter.notifyDataSetChanged();
        });


        buttonUpdate.setOnClickListener(v -> {
            //updated
            if (item != null) {
                int onPage = Integer.parseInt(editTextOnPage.getText().toString());
                if (onPage == item.getPagecount()) {
                    //if they finish the book
                    item.setOnPage(0);
                    item.setReadStatus("read");
                    clearFields();
                    adapter.getBooks().remove(index);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Congratulations on finishing " + item.getTitle(), Toast.LENGTH_SHORT).show();

                } else
                    item.setOnPage(onPage);
                viewModel.editBook(item, new CallBack() {
                    @Override
                    public void makeToast(String message) {
                        Toast.makeText(getContext(), item.getTitle() + " has been updated", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

        return view;
    }

    public void onListItemClick(int clickedItemIndex) {
        index = clickedItemIndex;
    }

    public static void setBook(Book book) {
        item = book;
    }


    public void clearFields()
    {
        editTextOnPage.setText("");
        editTextTotalPageCount.setText("");

        textViewUsername.setText("");
        currentReadingBookCover.setImageResource(R.drawable.coverplaceholder);
    }
}