package com.github.listsapp.view.library;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.github.listsapp.repository.Repository;
import com.github.listsapp.util.Book;
import com.github.listsapp.util.callback.CallBack;
import com.google.firebase.storage.StorageTask;

import java.util.ArrayList;
import java.util.List;

public class LibrarySearchAdapter {
    //adapter for updating recyclerview with the books matching search criteria or word
    private static List<Book> booklist;
    private final MutableLiveData<List<Book>> searchedBook = new MutableLiveData<>();
    private Repository repository;

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

        //the user can filter both on criteria (unread, read, wanted, owned)
        //if the users have selected a criteria, the search word is matched to the title
        //if the user filter on title, author then the search word is matched to title/author

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
                    if (p.getTitle().toLowerCase().contains(query.toLowerCase()) && p.getReadStatus() != null && p.getReadStatus().equals("read")) {
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
                    //the book is added from google.books.com then readstatus can be null
                    if (p.getTitle().toLowerCase().contains(query.toLowerCase()) && p.getReadStatus() != null && (p.getReadStatus().equals("unread"))) {
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
                    System.out.println(p.toString());
                    if (p.getTitle().toLowerCase().contains(query.toLowerCase()) && p.isOwned())
                    {
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

    public LiveData<List<Book>> getSearchedBooks() { return searchedBook; }


    public StorageTask getStorageTask()
    {
        return repository.getStorageTask();
    }

}
