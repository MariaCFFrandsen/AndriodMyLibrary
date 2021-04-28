package com.github.listsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.listsapp.model.LibraryModel;
import com.github.listsapp.util.Book;

import java.util.List;

public class LibraryViewModel extends ViewModel {

    LibraryModel libraryModel = new LibraryModel();

    public void searchForBook(String query)
    {
        libraryModel.searchForBook(query);
    }

    public LiveData<List<Book>> getSearchedBooks()
    {
        return libraryModel.getSearchedBooks();
    }

}
