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
        booklist.add(new Book(1,"Nu er det den her", R.drawable.bogplaceholder, "Read", true, 4, "Christopher Paolini"));
        booklist.add(new Book(8,"Eragon", R.drawable.bogplaceholder, "Read", true, 4, "Christopher Paolini"));
        booklist.add(new Book(9, "Arven", R.drawable.bogplaceholder, "Read", true, 4, "Christopher Paolini"));
        booklist.add(new Book(10,"Jeg valgte den her", R.drawable.bogplaceholder, "Unread", false, 4, "Christopher Paolini"));
        booklist.add(new Book(11,"Arven", R.drawable.bogplaceholder, "Read", true, 4, "Christopher Paolini"));
        booklist.add(new Book(12,"Den Ældste", R.drawable.bogplaceholder, "Read", true, 4, "Christopher Paolini"));
        booklist.add(new Book(13,"Arden", R.drawable.bogplaceholder, "Read", true, 4, "Christopher Paolini") );
        booklist.add(new Book(14,"Eradon", R.drawable.bogplaceholder, "Read", true, 4, "Christopher Paolini"));

        searchedBook.setValue(booklist);
    }


    public void searchForBook(String query, String spinnerFilter){

        switch (spinnerFilter)
        {
            case "Title":
            {
                List<Book> result = new ArrayList<>();
                for (Book p : booklist) {
                    if (p.getTitle().toLowerCase().contains(query.toLowerCase())) {
                        result.add(p);
                    }
                }
                searchedBook.setValue(result);
                break;
            }
            case "Author":
            {
                List<Book> result = new ArrayList<>();
                for (Book p : booklist) {
                    if (p.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                        result.add(p);
                    }
                }
                searchedBook.setValue(result);
                break;
            }
            case "Read":
            {
                List<Book> result = new ArrayList<>();
                for (Book p : booklist) {
                    if (p.getTitle().toLowerCase().contains(query.toLowerCase()) && p.getReadStatus().equals("Read")) {
                        result.add(p);
                    }
                }
                searchedBook.setValue(result);
                break;
            }

            case "Unread":
            {
                List<Book> result = new ArrayList<>();
                for (Book p : booklist) {
                    if (p.getTitle().toLowerCase().contains(query.toLowerCase()) && (p.getReadStatus().equals("Unread") || p.getReadStatus().equals("Current"))) {
                        result.add(p);
                    }
                }
                searchedBook.setValue(result);
                break;
            }

            case "Owned":
            {
                List<Book> result = new ArrayList<>();
                for (Book p : booklist) {
                    if (p.getTitle().toLowerCase().contains(query.toLowerCase()) && p.isOwned()) {
                        result.add(p);
                    }
                }
                searchedBook.setValue(result);
                break;
            }

            case "Not owned":
            {
                List<Book> result = new ArrayList<>();
                for (Book p : booklist) {
                    if (p.getTitle().toLowerCase().contains(query.toLowerCase()) && !p.isOwned()) {
                        result.add(p);
                    }
                }
                searchedBook.setValue(result);
                break;
            }

            case "All" :
            {
                List<Book> result = new ArrayList<>(booklist);
                searchedBook.setValue(result);
                break;
            }

        }

    }

    public LiveData<List<Book>> getSearchedBooks() {
        return searchedBook;
    }

    public Book getBookById(int id)
    {
        Book book = null;
        for (Book p : booklist) {
            if (p.getId() == id) {
                book = p;
                break;
            }
        }
       return book;
    }


}
