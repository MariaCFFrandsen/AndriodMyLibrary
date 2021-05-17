package com.github.listsapp.view.login;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.listsapp.view.library.LibrarySearchAdapter;
import com.github.listsapp.repository.dao.UserLiveData;
import com.github.listsapp.repository.Repository;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends AndroidViewModel {

    private static UserLiveData currentUser = new UserLiveData();
    private LibrarySearchAdapter librarySearchAdapter;
    private Repository repository = Repository.getInstance();

    public LoginViewModel(Application app)
    {
        super(app);
        System.out.println("kkkkkkkkkkkkkkkkkkkkkk");
        System.out.println(app == null);
        librarySearchAdapter = LibrarySearchAdapter.getInstance();
        setApplication(app);
    }

    public LiveData<FirebaseUser> getCurrentUser() {

        return currentUser;
    }

    public String getUsername()
    {
        return librarySearchAdapter.getUsername();
    }


    public void setDisplayName(String displayName) {
        librarySearchAdapter.setUsername(displayName);
    }

    public void signOut() {
        repository.signOut();
    }

    public void setApplication(Application app) {
        repository.setApplication(app);
    }
}
