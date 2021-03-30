package com.github.listsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.listsapp.repository.Repository;
import com.github.listsapp.util.User;

public class LoginViewModel extends ViewModel {

    private Repository repository;

    public LoginViewModel()
    {
        repository = new Repository();
    }

    public LiveData<Boolean> createUser(User user)
    {
       return repository.createUser(user);
    }

    public  LiveData<Boolean> login(User user)
    {
       return repository.login(user);
    }
}
