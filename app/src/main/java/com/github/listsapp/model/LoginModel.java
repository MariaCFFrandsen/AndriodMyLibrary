package com.github.listsapp.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.listsapp.util.User;

import java.util.ArrayList;

public class LoginModel {

    private final MutableLiveData<ArrayList<User>> users;

    public LoginModel()
    {
        users = new MutableLiveData<>();
        ArrayList<User> array = new ArrayList<>();
        array.add(new User("maria", "123"));
        array.add(new User("user", "123"));

        users.setValue(array);
    }

    public LiveData<Boolean> createUser(User user)
    {
        ArrayList<User> tmp = users.getValue();
        tmp.add(user);
        users.setValue(tmp);
        User tmp_user;
        for (int i = 0; i < tmp.size(); i++) {
            tmp_user = tmp.get(i);
            if(tmp_user.getUsername().equals(user.getUsername()))
                return new MutableLiveData<>(false);
        }
        return new MutableLiveData<>(true);
    }

    public LiveData<Boolean> login(User user)
    {
        ArrayList<User> tmp = users.getValue();
        User tmp_user;
        for (int i = 0; i < tmp.size(); i++) {
            tmp_user = tmp.get(i);
            if(tmp_user.getUsername().equals(user.getUsername()) && tmp_user.getPassword().equals(user.getPassword()))
                return new MutableLiveData<>(true);
        }
        return new MutableLiveData<>(false);
    }

}
