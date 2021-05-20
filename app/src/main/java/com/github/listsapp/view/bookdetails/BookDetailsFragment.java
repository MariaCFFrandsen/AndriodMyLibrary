package com.github.listsapp.view.bookdetails;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.listsapp.R;
import com.github.listsapp.util.Book;
import com.github.listsapp.util.callback.CallBack;
import com.github.listsapp.view.home.CurrentlyReadingFragment;


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
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Home Library");

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
                if(book.getImageUrl() != null)
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
            viewModel.editBook(book, new CallBack() {
                @Override
                public void makeToast(String message) {
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                }
            });
            goToHome();
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
            viewModel.deleteBook(book, new CallBack() {
                @Override
                public void makeToast(String message) {
                    Toast.makeText(getContext(), message + book.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
            goToHome();
        });

        return view;


    }

    private void setBook(Book book) {
        this.book = book;
    }

    public void goToHome()
    {
        System.out.println("hello");
        CurrentlyReadingFragment book = new CurrentlyReadingFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, book);
        transaction.addToBackStack("bookdetails");
        transaction.commit();
    }

}