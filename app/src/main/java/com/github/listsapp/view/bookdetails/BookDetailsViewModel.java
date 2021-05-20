package com.github.listsapp.view.bookdetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.listsapp.util.callback.CallBack;
import com.github.listsapp.repository.Repository;
import com.github.listsapp.util.Book;

public class BookDetailsViewModel extends ViewModel {

    private Repository repository = Repository.getInstance();
    public static MutableLiveData<Book> chosenBook;

    public BookDetailsViewModel()
    {

    }


    public void editBook(Book book, CallBack callBack)
    {
        repository.editBook(book, callBack);
    }


    public void deleteBook(Book book, CallBack callBack) {
        repository.deleteBook(book, callBack);
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
