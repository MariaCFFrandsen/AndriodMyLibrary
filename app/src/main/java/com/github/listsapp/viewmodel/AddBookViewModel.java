package com.github.listsapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.github.listsapp.model.AddBookModel;
import com.github.listsapp.repository.Repository;

public class AddBookViewModel extends ViewModel {


    private AddBookModel model = AddBookModel.getInstance();


    public void uploadFile()
    {
        model.uploadFile();
    }

}
