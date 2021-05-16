package com.github.listsapp.repository.dao;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.listsapp.util.Book;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.util.ArrayList;
import java.util.List;

public class CurrentlyReadingDAO {

    private static CurrentlyReadingDAO currentlyReadingDAO;
    private FirebaseDatabase database;
    private static DatabaseReference databaseReference;

    private static final String data = "library";
    private StorageReference storageReference;
    private StorageTask storageTask;
    private static MutableLiveData<List<Book>> currentlyReading;



    private CurrentlyReadingDAO()
    {
        currentlyReading = new MutableLiveData<>(new ArrayList<>());

        database = FirebaseDatabase.getInstance("https://homelibrary-c0594-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = database.getReference().child("users");
        storageReference = FirebaseStorage.getInstance("gs://homelibrary-c0594.appspot.com").getReference("images");
    }

    public static CurrentlyReadingDAO getInstance(){
        if(currentlyReadingDAO == null)
            currentlyReadingDAO = new CurrentlyReadingDAO();

        return currentlyReadingDAO;
    }


    public static LiveData<List<Book>> getCurrentlyReadingBooks(String username)
    {
        List<Book> books = new ArrayList<>();
        if(username != null)
        {
            Query query = databaseReference.child(username).child(data).orderByChild("readStatus").equalTo("currently");

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot s: snapshot.getChildren()) {
                        Book value = s.getValue(Book.class);
                        books.add(value);
                        System.out.println(value.toString());

                    }
                    currentlyReading.setValue(books);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }


            });


            query.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    currentlyReading.getValue().add(snapshot.getValue(Book.class));
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Book book = snapshot.getValue(Book.class);
                    List<Book> value = currentlyReading.getValue();


                    for (Book b: value)
                    {
                        if(b.getTitle().equals(book.getTitle()))
                        {
                            currentlyReading.getValue().remove(b);
                            currentlyReading.getValue().add(book);
                        }
                    }



                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                    Book book = snapshot.getValue(Book.class);
                    for (Book b: currentlyReading.getValue())
                    {
                        if(b.getTitle().equals(book.getTitle()))
                        {
                            currentlyReading.getValue().remove(b);
                        }

                    }
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });




        }
        return currentlyReading;
    }
}
