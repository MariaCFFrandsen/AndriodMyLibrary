package com.github.listsapp.view.library;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.listsapp.util.Book;
import com.github.listsapp.util.callbackinterfaces.CallBackForSignOut;
import com.github.listsapp.util.callbackinterfaces.CallBack_AddBook;
import com.google.firebase.storage.StorageTask;

import java.util.List;

public class LibraryViewModel extends ViewModel {

    LibrarySearchAdapter librarySearchAdapter;
    MutableLiveData<Book> chosenBook;

    public LibraryViewModel()
    {
        librarySearchAdapter = LibrarySearchAdapter.getInstance();
    }

    public void searchForBook(String query, String spinnerFilter)
    {
        librarySearchAdapter.searchForBook(query, spinnerFilter);
    }

    public LiveData<List<Book>> getSearchedBooks()
    {
        return librarySearchAdapter.getSearchedBooks();
    }


    public MutableLiveData<Book> getChosenBook() {
        return chosenBook;
    }

    public void setChosenBook(MutableLiveData<Book> chosenBook) {
        this.chosenBook = chosenBook;
    }

    public LiveData<List<Book>> getLibrary(String user)
    {
        return librarySearchAdapter.getLibrary(user);
    }

    public void addBook(Book book, String displayName, CallBack_AddBook addBook)
    {
        librarySearchAdapter.addBook(book, displayName, addBook);
    }

    public void uploadFile(String title, String imagename, Uri imageUri)
    {
        librarySearchAdapter.uploadFile(title, imagename, imageUri);
    }

    public StorageTask getStorageTask()
    {
        return librarySearchAdapter.getStorageTask();
    }

}
