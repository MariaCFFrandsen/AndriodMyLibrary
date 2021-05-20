package com.github.listsapp.repository;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.LiveData;

import com.github.listsapp.repository.dao.CurrentlyReadingDAO;
import com.github.listsapp.repository.dao.LibraryDAO;
import com.github.listsapp.repository.dao.UserDAO;
import com.github.listsapp.repository.remotedata.NetworkImpl;
import com.github.listsapp.util.Book;
import com.github.listsapp.util.api.GBookList;
import com.github.listsapp.util.callback.CallBack;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageTask;

import java.util.List;

public class Repository {


    private static Repository instance;
    private LibraryDAO libraryDAO;
    private CurrentlyReadingDAO currentlyReadingDAO;
    private NetworkImpl network;
    public static String username;
    private static UserDAO currentUser = new UserDAO();


    private Repository(){
        libraryDAO = LibraryDAO.getInstance();
        currentlyReadingDAO = CurrentlyReadingDAO.getInstance();
        network = NetworkImpl.getInstance();
    }

    public static Repository getInstance(){
        if(instance == null)
            instance = new Repository();

        return instance;
    }

    public LiveData<List<Book>> getLibrary(String user)
    {
        return libraryDAO.getLibrary(user);
    }

    public void addBook(Book book, CallBack addBook)
    {
        libraryDAO.addBook(book, username, addBook);
    }

    public void uploadFile(String title, String imagename, Uri imageUri) {
        libraryDAO.uploadFile(title, username, imagename, imageUri);
    }

    public StorageTask getStorageTask()
    {
        return libraryDAO.getStorageTask();
    }

    public void editBook(Book book, CallBack callBack) {
        libraryDAO.editBook(book, username, callBack);
    }

    public void deleteBook(Book book, CallBack callBack) {
        libraryDAO.removeBook(book, username, callBack);
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

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Repository.username = username;
    }

    public void signOut(CallBack callBack) {
        UserDAO.signOut(callBack);
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return currentUser;
    }

    public void setApplication(Application app) {
        currentUser.setApplication(app);
    }
}
