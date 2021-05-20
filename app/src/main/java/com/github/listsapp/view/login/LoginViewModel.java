package com.github.listsapp.view.login;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.listsapp.view.library.LibrarySearchAdapter;
import com.github.listsapp.repository.Repository;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends AndroidViewModel {


    private LibrarySearchAdapter librarySearchAdapter;
    private static Repository repository = Repository.getInstance();

    public LoginViewModel(Application app)
    {
        super(app);
        librarySearchAdapter = LibrarySearchAdapter.getInstance();
        setApplication(app);
    }

    public static LiveData<FirebaseUser> getCurrentUser() {

        return repository.getCurrentUser();
    }

    public String getUsername()
    {
        return Repository.getUsername();
    }


    public void setDisplayName(String displayName) {
        Repository.setUsername(displayName);
    }

    public void setApplication(Application app)
    {
        repository.setApplication(app);
    }

}
