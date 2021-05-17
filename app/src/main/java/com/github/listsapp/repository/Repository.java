package com.github.listsapp.repository;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.LiveData;

import com.github.listsapp.repository.dao.CurrentlyReadingDAO;
import com.github.listsapp.repository.dao.LibraryDAO;
import com.github.listsapp.repository.dao.LoginDAO;
import com.github.listsapp.repository.remotedata.NetworkImpl;
import com.github.listsapp.util.Book;
import com.github.listsapp.util.User;
import com.github.listsapp.util.api.GBookList;
import com.google.firebase.storage.StorageTask;

import java.util.List;

public class Repository {


    private static Repository instance;
    private LibraryDAO libraryDAO;
    private CurrentlyReadingDAO currentlyReadingDAO;
    private NetworkImpl network;
    private LoginDAO loginDAO;

    private Repository(){
        libraryDAO = LibraryDAO.getInstance();
        currentlyReadingDAO = CurrentlyReadingDAO.getInstance();
        network = NetworkImpl.getInstance();
        loginDAO = LoginDAO.getInstance();
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


    public void uploadFile(String title, String username, String imagename, Uri imageUri) {
        libraryDAO.uploadFile(title, username, imagename, imageUri);
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

    public void search(String keyword) {
        network.searchForBooks(keyword);
    }

    public LiveData<GBookList> getSearchedBooks()
    {
        return network.getSearchedBooks();
    }

    public LiveData<List<Book>> getCurrentlyReadingBooks(String username)
    {
        return currentlyReadingDAO.getCurrentlyReadingBooks(username);
    }

    public void signOut() {
        loginDAO.signOut();
    }

    public void setApplication(Application app) {
        loginDAO.setApp(app);
    }
}
