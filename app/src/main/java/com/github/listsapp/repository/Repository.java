package com.github.listsapp.repository;

import androidx.lifecycle.LiveData;

import com.github.listsapp.model.*;
import com.github.listsapp.util.User;

public class Repository {


    private static Repository instance;

    private Repository(){
        //opret forbindelse til modellerne
        modelManager = new ModelManager();
    }

    public static Repository getInstance(){
        if(instance == null)
            instance = new Repository();

        return instance;
    }

    private ModelManager modelManager;

    public LiveData<Boolean> createUser(User user)
    {
        return modelManager.createUser(user);
    }

    public LiveData<Boolean> login(User user)
    {
        return modelManager.login(user);
    }

}
