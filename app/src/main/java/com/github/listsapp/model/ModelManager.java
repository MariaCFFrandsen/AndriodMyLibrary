package com.github.listsapp.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.listsapp.util.Book;
import com.github.listsapp.util.User;
import com.github.listsapp.model.LoginModel;
import java.util.ArrayList;

public class ModelManager {
    private LoginModel loginModel;
    private LibraryModel libraryModel;

    public ModelManager()
    {
        loginModel = new LoginModel();
        libraryModel = new LibraryModel();
    }


    public LiveData<Boolean> createUser(User user)
    {
      return loginModel.createUser(user);
    }

    public LiveData<Boolean> login(User user)
    {
        return loginModel.login(user);
    }

    public Book getBookById(int id)
    {
        return libraryModel.getBookById(id);
    }
}
