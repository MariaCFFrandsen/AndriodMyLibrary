package com.github.listsapp.viewmodel;

import android.view.Display;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.listsapp.model.LibraryModel;
import com.github.listsapp.util.Book;

public class SelectedBookViewModel extends ViewModel {

    public static MutableLiveData<Book> chosenBook;
    public static MutableLiveData<Integer> chosenBookId = new MutableLiveData<>();
    private static  Book chosen = null;
    private static LibraryModel libraryModel;

    public void init()
    {
        libraryModel = LibraryModel.getInstance();
        chosenBook = new MutableLiveData<>();
        chosenBook.setValue(new Book(0,"", 1, "", false, 1, ""));
    }

    public Book getChosenBook() {
        return chosenBook.getValue();
    }

    public static void setChosenBook(int id) {
        libraryModel = LibraryModel.getInstance();
        Book book = libraryModel.getBookById(id);
        MutableLiveData here = new MutableLiveData<>();
        here.setValue(book);
        SelectedBookViewModel.chosenBook = here ;
        chosen = book;
        System.out.println("hello" + chosen.getTitle());

    }

    public static Book getChosen() {
        return chosen;
    }
}
