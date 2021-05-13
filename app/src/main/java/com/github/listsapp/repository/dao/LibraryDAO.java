package com.github.listsapp.repository.dao;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.listsapp.R;
import com.github.listsapp.model.LibraryModel;
import com.github.listsapp.repository.Repository;
import com.github.listsapp.util.Book;
import com.github.listsapp.util.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LibraryDAO {

    private static LibraryDAO libraryDAO;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private MutableLiveData<List<Book>> library;
    private final String data = "library";

    private LibraryDAO()
    {
        library = new MutableLiveData<>(new ArrayList<>());
        database = FirebaseDatabase.getInstance("https://homelibrary-c0594-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("users");
    }

    public static LibraryDAO getInstance(){
        if(libraryDAO == null)
            libraryDAO = new LibraryDAO();

        return libraryDAO;
    }

    public LiveData<List<Book>> getLibrary(String username)
    {
        List<Book> books = new ArrayList<>();
        if(username != null)
        {
            DatabaseReference refDB = reference.child(username).child(data);

            refDB.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot s: snapshot.getChildren()) {
                        Book value = s.getValue(Book.class);
                        books.add(value);
                        System.out.println("fra firebase  " + books.size());
                        System.out.println(books.get(0).toString());

                    }
                    library.setValue(books);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        return library;
    }

    public LiveData<List<Book>> getLibraryTest()
    {
        return library;
    }


    public void addBook(Book book, String displayName)
    {
        reference.child(displayName.toLowerCase()).child(data).child(book.getTitle()).setValue(book);
    }


    public void firebaseHelloWorld()
    {

        List<Book> books = new ArrayList<>();
        DatabaseReference refDB = reference.child("maria").child(data);

        refDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s: snapshot.getChildren()) {
                    Book value = s.getValue(Book.class);
                    books.add(value);
                    System.out.println(value.getTitle() + " nnnnnnnnnnnnnnnnnnnnnnnnn");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Toast.makeText(getContext(), "cancelled", Toast.LENGTH_SHORT).show();
            }
        });
        library.setValue(books);
        //return library;
    }


    public void firebasetest()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://homelibrary-c0594-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference();

        myRef.child("users").child("maria").child("userdetails").setValue(new User("maria", "password"));
        Book book = new Book();
        book.setAuthor("christopher");
        book.setTitle("eragon 1");

        myRef.child("users").child("maria").child("books").child("book1").setValue(book);
        Book book2 = new Book();
        book2.setAuthor("christopher");
        book2.setTitle("eragon 2");

        myRef.child("users").child("maria").child("books").child("book2").setValue(book2);

    }
}
