package com.github.listsapp.view.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.github.listsapp.R;
import com.github.listsapp.model.LastestBooksAdapter;
import com.github.listsapp.util.Book;
import com.github.listsapp.util.User;
import com.github.listsapp.view.login.fragment_createuser;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

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
            firebaseHelloWorld();
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

    public void firebaseHelloWorld()
    {

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://homelibrary-c0594-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference().child("users");


        //System.out.println(ServerValue.TIMESTAMP.toString() + "           timestamp");
        DatabaseReference maria = myRef.child("maria");

        maria.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                User value = snapshot.getValue(User.class);
                System.out.println(value.getUsername() + " " + value.getPassword());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "cancelled", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void firebasetest()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://homelibrary-c0594-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference().child("users").child("hello");

        DatabaseReference maria = myRef.child("maria");

        maria.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                User value = snapshot.getValue(User.class);
                System.out.println(value.getUsername() + " " + value.getPassword());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "cancelled", Toast.LENGTH_SHORT).show();
            }
        });
    }
}