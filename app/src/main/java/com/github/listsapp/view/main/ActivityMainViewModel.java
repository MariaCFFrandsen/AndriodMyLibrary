package com.github.listsapp.view.main;

import androidx.lifecycle.ViewModel;

import com.github.listsapp.repository.Repository;
import com.github.listsapp.util.callbackinterfaces.CallBack;

public class ActivityMainViewModel extends ViewModel {

    private Repository repository = Repository.getInstance();

    public String getUsername()
    {
        return Repository.getUsername();
    }

    public void signOut(CallBack callBack)
    {
        repository.signOut(callBack);
    }


}
