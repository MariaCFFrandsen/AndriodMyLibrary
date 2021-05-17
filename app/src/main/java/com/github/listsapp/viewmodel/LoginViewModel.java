package com.github.listsapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.listsapp.model.LibraryModel;
import com.github.listsapp.model.UserLiveData;
import com.github.listsapp.repository.Repository;
import com.github.listsapp.repository.dao.LoginDAO;
import com.github.listsapp.util.User;
import com.google.firebase.auth.FirebaseUser;

import java.util.ResourceBundle;

public class LoginViewModel extends AndroidViewModel {

    private static UserLiveData currentUser = new UserLiveData();
    private LibraryModel libraryModel;
    private Repository repository = Repository.getInstance();

    public LoginViewModel(Application app)
    {
        super(app);
        System.out.println("kkkkkkkkkkkkkkkkkkkkkk");
        System.out.println(app == null);
        libraryModel = LibraryModel.getInstance();
        setApplication(app);
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

    public void signOut() {
        repository.signOut();
    }

    public void setApplication(Application app) {
        repository.setApplication(app);
    }
}
