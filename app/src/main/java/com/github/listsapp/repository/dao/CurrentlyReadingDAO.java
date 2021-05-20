package com.github.listsapp.repository.dao;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.github.listsapp.util.Book;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CurrentlyReadingDAO {

    private static CurrentlyReadingDAO currentlyReadingDAO;
    private FirebaseDatabase database;
    private static DatabaseReference databaseReference;

    private static final String data = "library";
    private List<Book> currently_books = new ArrayList<>();
    private static MutableLiveData<List<Book>> currentlyReading = new MutableLiveData<>();

    private CurrentlyReadingDAO()
    {
        //initializing variables
        currentlyReading.setValue(currently_books);
        database = FirebaseDatabase.getInstance("https://homelibrary-c0594-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = database.getReference().child("users");
    }

    public static CurrentlyReadingDAO getInstance(){
        if(currentlyReadingDAO == null)
            currentlyReadingDAO = new CurrentlyReadingDAO();

        return currentlyReadingDAO;
    }


    public LiveData<List<Book>> getCurrentlyReadingBooks(String username)
    {


        if(username != null)
        {
            //querying firebase database for what books the user is currently reading
            //checking for when the variable readStatus is set to currently

            Query query = databaseReference.child(username).child(data).orderByChild("readStatus").equalTo("currently");

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    currently_books.clear();
                    for (DataSnapshot s: snapshot.getChildren()) {
                        Book value = s.getValue(Book.class);
                        currently_books.add(value);

                    }
                    currentlyReading.setValue(currently_books);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.i("Firebase", "failure on retrieving currently books");
                }

            });

        }
        return currentlyReading;
    }
}
