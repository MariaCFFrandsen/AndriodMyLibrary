package com.github.listsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.listsapp.model.LibraryModel;
import com.github.listsapp.model.UserLiveData;
import com.github.listsapp.util.User;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends ViewModel {

    private static UserLiveData currentUser = new UserLiveData();
    private LibraryModel libraryModel;
    public LoginViewModel()
    {
        libraryModel = LibraryModel.getInstance();
    }

    public LiveData<FirebaseUser> getCurrentUser() {

        return currentUser;
    }

    public String getUsername()
    {
        return libraryModel.getUsername();
    }


    public void setDisplayName(String displayName) {
        libraryModel.setUsername(displayName);
    }
}
