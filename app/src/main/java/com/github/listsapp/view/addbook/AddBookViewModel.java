package com.github.listsapp.view.addbook;

import android.net.Uri;

import androidx.lifecycle.ViewModel;


import com.github.listsapp.repository.Repository;
import com.github.listsapp.util.Book;
import com.github.listsapp.util.callback.CallBack;
import com.github.listsapp.util.callback.CallBackCheckTitle;

public class AddBookViewModel extends ViewModel {
    private Repository repository = Repository.getInstance();

    public void addBook(Book book, CallBack addBook)
    {
        repository.addBook(book, addBook);
    }

    public void uploadFile(String title, String imagename, Uri imageUri)
    {
        repository.uploadFile(title, imagename, imageUri);
    }

    public void checkTitle(CallBackCheckTitle callBack, String title, String username) {
        repository.checkTitle(callBack, title, username);
    }
}
