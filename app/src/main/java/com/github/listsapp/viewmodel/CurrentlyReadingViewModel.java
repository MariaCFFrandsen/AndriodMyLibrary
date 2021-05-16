package com.github.listsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.listsapp.model.LibraryModel;
import com.github.listsapp.repository.Repository;
import com.github.listsapp.util.Book;
import com.github.listsapp.util.api.GBook;
import com.github.listsapp.util.api.GBookList;

import java.util.List;

public class CurrentlyReadingViewModel extends ViewModel {


    private Repository repository = Repository.getInstance();


    public LiveData<List<Book>> getCurrentlyReadingList(String username)
    {
        return repository.getCurrentlyReadingBooks(username);
    }

    private static MutableLiveData<Book> bookMutableLiveData;


    public static void setChosenGBook(Book book) {
        if(bookMutableLiveData == null)
            bookMutableLiveData = new MutableLiveData<>(new Book());
        bookMutableLiveData.setValue(book);
    }

    public LiveData<Book> getBookMutableLiveData() {
        if(bookMutableLiveData == null)
            bookMutableLiveData = new MutableLiveData<>(new Book());
        return bookMutableLiveData;
    }

    public void editBook(Book item) {
        repository.editBook(item, LibraryModel.getUsername());
    }
}
