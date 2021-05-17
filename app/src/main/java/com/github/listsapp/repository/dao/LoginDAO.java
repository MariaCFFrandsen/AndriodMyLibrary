package com.github.listsapp.repository.dao;

import android.app.Application;

import com.firebase.ui.auth.AuthUI;

public class LoginDAO {

    private Application app;

    private static LoginDAO loginDAO;

    public static LoginDAO getInstance(){
        if(loginDAO == null)
            loginDAO = new LoginDAO();

        return loginDAO;
    }

    public static LoginDAO getInstance(Application app){
        if(loginDAO == null)
            loginDAO = new LoginDAO(app);

        return loginDAO;
    }

    private LoginDAO()
    {

    }

    public void setApp(Application app) {
        this.app = app;
    }

    private LoginDAO(Application app)
    {
        this.app = app;
    }

    public void signOut() {

        AuthUI.getInstance()
                .signOut(app.getApplicationContext());

    }
}
