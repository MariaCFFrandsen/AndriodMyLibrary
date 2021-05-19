package com.github.listsapp.repository;

import android.net.Uri;

import androidx.lifecycle.LiveData;

import com.github.listsapp.repository.dao.CurrentlyReadingDAO;
import com.github.listsapp.repository.dao.LibraryDAO;
import com.github.listsapp.repository.remotedata.NetworkImpl;
import com.github.listsapp.util.Book;
import com.github.listsapp.util.api.GBookList;
import com.github.listsapp.util.callbackinterfaces.CallBack;
import com.github.listsapp.util.callbackinterfaces.CallBackForAddGBook;
import com.github.listsapp.util.callbackinterfaces.CallBack_AddBook;
import com.google.firebase.storage.StorageTask;

import java.util.List;

public class Repository {


    private static Repository instance;
    private LibraryDAO libraryDAO;
    private CurrentlyReadingDAO currentlyReadingDAO;
    private NetworkImpl network;

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

    public void addBook(Book book, String displayName, CallBack_AddBook addBook)
    {
        libraryDAO.addBook(book, displayName, addBook);
    }

    public void addBook(Book book, String displayName, CallBackForAddGBook addGBook)
    {
        libraryDAO.addBook(book, displayName, addGBook);
    }


    public void uploadFile(String title, String username, String imagename, Uri imageUri) {
        libraryDAO.uploadFile(title, username, imagename, imageUri);
    }

    public StorageTask getStorageTask()
    {
        return libraryDAO.getStorageTask();
    }

    public void editBook(Book book, String username, CallBack callBack) {
        libraryDAO.editBook(book, username, callBack);
    }

    public void deleteBook(Book book, String username, CallBack callBack) {
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

}
