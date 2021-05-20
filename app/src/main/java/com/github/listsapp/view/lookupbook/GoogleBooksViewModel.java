package com.github.listsapp.view.lookupbook;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.listsapp.repository.Repository;
import com.github.listsapp.util.api.GBookList;

public class GoogleBooksViewModel extends ViewModel {
    private Repository repository = Repository.getInstance();

    public void search(String keyword) {

        repository.search(keyword);

    }

    public LiveData<GBookList> getSearchedBooks()
    {
        return repository.getSearchedBooks();
    }



}
