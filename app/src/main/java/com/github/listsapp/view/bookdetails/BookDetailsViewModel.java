package com.github.listsapp.view.bookdetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.listsapp.util.api.GBook;
import com.github.listsapp.view.library.LibrarySearchAdapter;
import com.github.listsapp.repository.Repository;
import com.github.listsapp.util.Book;

public class BookDetailsViewModel extends ViewModel {

    private Repository repository = Repository.getInstance();
    public static MutableLiveData<Book> chosenBook;

    public BookDetailsViewModel()
    {

    }


    public void editBook(Book book)
    {
        repository.editBook(book, LibrarySearchAdapter.getUsername());
    }


    public void deleteBook(Book book) {
        repository.deleteBook(book, LibrarySearchAdapter.getUsername());
    }

    public static void setChosenBook(Book book) {
        if(chosenBook == null)
            chosenBook = new MutableLiveData<>(new Book());

        chosenBook.setValue(book);
    }

    public static LiveData<Book> getChosenBook() {
        return chosenBook;
    }
}
