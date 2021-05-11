package com.github.listsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firebase.ui.auth.AuthUI;
import com.github.listsapp.repository.Repository;
import com.github.listsapp.repository.firebase.UserLiveData;
import com.github.listsapp.util.User;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends ViewModel {

    private final UserLiveData currentUser;

    private Repository repository;

    public LoginViewModel()
    {
        repository = Repository.getInstance();
        currentUser = new UserLiveData();
    }

    public LiveData<Boolean> createUser(User user)
    {
       return repository.createUser(user);
    }

    public  LiveData<Boolean> login(User user)
    {
       return repository.login(user);
    }

    public LiveData<FirebaseUser> getCurrentUser() {
     return currentUser;
    }


}
