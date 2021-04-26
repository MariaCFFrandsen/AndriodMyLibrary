package com.github.listsapp.repository;

import androidx.lifecycle.LiveData;

import com.github.listsapp.model.*;
import com.github.listsapp.util.User;

public class Repository {
    private ModelManager modelManager;

    public Repository()
    {
        modelManager = new ModelManager();
    }

    public LiveData<Boolean> createUser(User user)
    {
        return modelManager.createUser(user);
    }

    public LiveData<Boolean> login(User user)
    {
        return modelManager.login(user);
    }

}
