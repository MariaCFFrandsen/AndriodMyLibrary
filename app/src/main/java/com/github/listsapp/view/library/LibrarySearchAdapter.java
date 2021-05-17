package com.github.listsapp.view.library;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.github.listsapp.repository.Repository;
import com.github.listsapp.util.Book;
import com.google.firebase.storage.StorageTask;

import java.util.ArrayList;
import java.util.List;

public class LibrarySearchAdapter {
    private static List<Book> booklist;
    private final MutableLiveData<List<Book>> searchedBook = new MutableLiveData<>();
    private Repository repository;
    private static String  username;

    private static LibrarySearchAdapter librarySearchAdapter;

    private LibrarySearchAdapter()
    {

        booklist = new ArrayList<>();
        repository = Repository.getInstance();

    }

    public void getBooks(String username) {

        repository.getLibrary(username).observeForever(new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                booklist = books;
            }
        });

        searchedBook.setValue(booklist);

    }

    public static LibrarySearchAdapter getInstance(){
        if(librarySearchAdapter == null)
            librarySearchAdapter = new LibrarySearchAdapter();

        return librarySearchAdapter;
    }

    public void searchForBook(String query, String spinnerFilter){

        switch (spinnerFilter)
        {
            case "Title":
            {
                List<Book> result = new ArrayList<>();
                for (Book p : booklist) {
                    if (p.getTitle().toLowerCase().contains(query.toLowerCase())) {
                        result.add(p);
                    }
                }
                searchedBook.setValue(result);
                break;
            }
            case "Author":
            {
                List<Book> result = new ArrayList<>();
                for (Book p : booklist) {
                    if (p.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                        result.add(p);
                    }
                }
                searchedBook.setValue(result);
                break;
            }
            case "Read":
            {
                List<Book> result = new ArrayList<>();
                for (Book p : booklist) {
                    if (p.getTitle().toLowerCase().contains(query.toLowerCase()) && p.getReadStatus().equals("Read")) {
                        result.add(p);
                    }
                }
                searchedBook.setValue(result);
                break;
            }

            case "Unread":
            {
                List<Book> result = new ArrayList<>();
                for (Book p : booklist) {
                    if (p.getTitle().toLowerCase().contains(query.toLowerCase()) && (p.getReadStatus().equals("Unread") || p.getReadStatus().equals("Current"))) {
                        result.add(p);
                    }
                }
                searchedBook.setValue(result);
                break;
            }

            case "Owned":
            {
                List<Book> result = new ArrayList<>();
                for (Book p : booklist) {
                    if (p.getTitle().toLowerCase().contains(query.toLowerCase()) && p.isOwned()) {
                        result.add(p);
                    }
                }
                searchedBook.setValue(result);
                break;
            }

            case "Not owned":
            {
                List<Book> result = new ArrayList<>();
                for (Book p : booklist) {
                    if (p.getTitle().toLowerCase().contains(query.toLowerCase()) && !p.isOwned()) {
                        result.add(p);
                    }
                }
                searchedBook.setValue(result);
                break;
            }

            case "All" :
            {
                List<Book> result = new ArrayList<>(booklist);
                searchedBook.setValue(result);
                break;
            }

        }

    }

    public LiveData<List<Book>> getSearchedBooks() {

        return searchedBook;
    }

    public Book getBookByTitle(String id)
    {
        Book book = null;
        for (Book p : booklist) {
            System.out.println(p.getImageUrl());
            if (p.getTitle().equals(id)) {
                book = p;
                break;
            }
        }
       return book;
    }

    public LiveData<List<Book>> getLastestBooks() {
        return null;
    }

    public LiveData<List<Book>> getLibrary(String user)
    {
        return repository.getLibrary(user);
    }

    public void addBook(Book book, String displayName)
    {
        repository.addBook(book, displayName);
    }

    public static String getUsername() {
        return username;
    }

    public void setUsername(String username)
    {
        LibrarySearchAdapter.username = username;
    }

    public void uploadFile(String title, String imagename, Uri imageUri) {
        repository.uploadFile(title, username, imagename, imageUri);
    }

    public StorageTask getStorageTask()
    {
        return repository.getStorageTask();
    }

}
