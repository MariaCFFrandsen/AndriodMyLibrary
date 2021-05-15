package com.github.listsapp.repository;

import android.net.Uri;

import androidx.lifecycle.LiveData;

import com.github.listsapp.model.*;
import com.github.listsapp.repository.dao.LibraryDAO;
import com.github.listsapp.util.Book;
import com.github.listsapp.util.Library;
import com.github.listsapp.util.User;
import com.google.firebase.storage.StorageTask;

import java.util.List;

public class Repository {


    private static Repository instance;
    private LibraryDAO libraryDAO;
    private LibraryLiveData libraryLiveData;

    private Repository(){
        libraryDAO = LibraryDAO.getInstance();
    }

    public static Repository getInstance(){
        if(instance == null)
            instance = new Repository();

        return instance;
    }


    public LiveData<Boolean> createUser(User user)
    {
        //return modelManager.createUser(user);
        return null;
    }

    public LiveData<Boolean> login(User user)
    {
        //return modelManager.login(user);
        return null;
    }

    public LiveData<List<Book>> getLibrary(String user)
    {
        return libraryDAO.getLibrary(user);
    }

    public void addBook(Book book, String displayName)
    {

        libraryDAO.addBook(book, displayName);
    }

    public LiveData<Library> getLiveDataBooks()
    {
        return libraryLiveData;
    }

    public void init(String username)
    {
       // libraryLiveData = new LibraryLiveData(username);
    }

    public void uploadFile(String username, String imagename, Uri imageUri) {
        libraryDAO.uploadFile(username, imagename, imageUri);
    }

    public StorageTask getStorageTask()
    {
        return libraryDAO.getStorageTask();
    }

    public void editBook(Book book, String username) {
        libraryDAO.editBook(book, username);
    }

    public void deleteBook(Book book, String username) {
        libraryDAO.removeBook(book, username);
    }
}
