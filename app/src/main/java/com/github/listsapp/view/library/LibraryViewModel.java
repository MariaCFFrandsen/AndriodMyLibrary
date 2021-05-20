package com.github.listsapp.view.library;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.listsapp.util.Book;
import com.github.listsapp.util.callbackinterfaces.CallBack;
import com.google.firebase.storage.StorageTask;

import java.util.List;

public class LibraryViewModel extends ViewModel {

    LibrarySearchAdapter librarySearchAdapter;


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

    public StorageTask getStorageTask()
    {
        return librarySearchAdapter.getStorageTask();
    }

}
