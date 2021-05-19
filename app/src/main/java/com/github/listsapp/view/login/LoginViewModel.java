package com.github.listsapp.view.login;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.listsapp.view.library.LibrarySearchAdapter;
import com.github.listsapp.repository.dao.UserDAO;
import com.github.listsapp.repository.Repository;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends AndroidViewModel {

    private static UserDAO currentUser = new UserDAO();
    private LibrarySearchAdapter librarySearchAdapter;
    private Repository repository = Repository.getInstance();

    public LoginViewModel(Application app)
    {
        super(app);
        librarySearchAdapter = LibrarySearchAdapter.getInstance();
        currentUser.setApplication(app);

    }

    public static LiveData<FirebaseUser> getCurrentUser() {

        return currentUser;
    }

    public String getUsername()
    {
        return librarySearchAdapter.getUsername();
    }


    public void setDisplayName(String displayName) {
        librarySearchAdapter.setUsername(displayName);
    }

}
