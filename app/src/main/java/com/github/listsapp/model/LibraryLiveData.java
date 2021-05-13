package com.github.listsapp.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.listsapp.util.Book;
import com.github.listsapp.util.Library;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LibraryLiveData extends LiveData<Library> {
/*
    private Library library = new Library();

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private final String data = "library";

    public LibraryLiveData(String username)
    {
        database = FirebaseDatabase.getInstance("https://homelibrary-c0594-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("users").child(username).child(data);
    }


    private final ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            for (DataSnapshot s: snapshot.getChildren()) {
                Book value = s.getValue(Book.class);
                library.getBooks().add(value);
                System.out.println("fra firebase  hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh" + library.getBooks().size());
                System.out.println(library.getBooks().get(0).toString());

            }

            setValue(library);

        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };

    @Override
    protected void onActive() {
        super.onActive();
        reference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        reference.removeEventListener(listener);
    }


 */
}
