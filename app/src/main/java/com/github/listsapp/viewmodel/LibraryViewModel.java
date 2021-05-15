package com.github.listsapp.viewmodel;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.listsapp.model.LibraryModel;
import com.github.listsapp.util.Book;
import com.google.firebase.storage.StorageTask;

import java.util.List;

public class LibraryViewModel extends ViewModel {

    LibraryModel libraryModel;
    MutableLiveData<Book> chosenBook;

    public LibraryViewModel()
    {
        libraryModel = LibraryModel.getInstance();
    }

    public void searchForBook(String query, String spinnerFilter)
    {
        libraryModel.searchForBook(query, spinnerFilter);
    }

    public LiveData<List<Book>> getSearchedBooks()
    {
        return libraryModel.getSearchedBooks();
    }


    public MutableLiveData<Book> getChosenBook() {
        return chosenBook;
    }

    public void setChosenBook(MutableLiveData<Book> chosenBook) {
        this.chosenBook = chosenBook;
    }

    public LiveData<List<Book>> getLibrary(String user)
    {
        return libraryModel.getLibrary(user);
    }

    public void addBook(Book book, String displayName)
    {
        libraryModel.addBook(book, displayName);
    }

    public void uploadFile(String title, String imagename, Uri imageUri)
    {
        libraryModel.uploadFile(title, imagename, imageUri);
    }

    public StorageTask getStorageTask()
    {
        return libraryModel.getStorageTask();
    }

}
