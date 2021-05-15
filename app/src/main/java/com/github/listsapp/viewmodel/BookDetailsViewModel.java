package com.github.listsapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.github.listsapp.model.LibraryModel;
import com.github.listsapp.repository.Repository;
import com.github.listsapp.util.Book;

public class BookDetailsViewModel extends ViewModel {

    private Repository repository = Repository.getInstance();
    private Book book;

    public void editBook(Book book)
    {
        repository.editBook(book, LibraryModel.getUsername());
    }


    public void deleteBook(Book book) {
        repository.deleteBook(book, LibraryModel.getUsername());
    }

    public Book find(int id)
    {
        return LibraryModel.getInstance().getBookById(id);
    }
}
