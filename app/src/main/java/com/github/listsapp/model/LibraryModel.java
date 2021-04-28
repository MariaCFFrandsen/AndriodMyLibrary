package com.github.listsapp.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.listsapp.R;
import com.github.listsapp.util.Book;

import java.util.ArrayList;
import java.util.List;

public class LibraryModel {
    private final List<Book> booklist;
    private final MutableLiveData<List<Book>> searchedBook = new MutableLiveData<>();

    public LibraryModel(){
        booklist = new ArrayList<>();
        booklist.add(new Book("Eragon", R.drawable.bogplaceholder, "Read", true, 4));
        booklist.add(new Book("Arven", R.drawable.bogplaceholder, "Read", true, 4));
        booklist.add(new Book("Eragon", R.drawable.bogplaceholder, "Unread", false, 4));
        booklist.add(new Book("Arven", R.drawable.bogplaceholder, "Read", true, 4));
        booklist.add(new Book("Den Ældste", R.drawable.bogplaceholder, "Read", true, 4));
        booklist.add(new Book("Arden", R.drawable.bogplaceholder, "Read", true, 4));
        booklist.add(new Book("Eradon", R.drawable.bogplaceholder, "Read", true, 4));

        searchedBook.setValue(booklist);
    }

    public void searchForBook(String query){
        List<Book> result = new ArrayList<>();
        for (Book p : booklist) {
            if (p.getTitle().toLowerCase().contains(query.toLowerCase())) {
                result.add(p);
            }
        }
        searchedBook.setValue(result);
    }

    public LiveData<List<Book>> getSearchedBooks() {
        return searchedBook;
    }


}
