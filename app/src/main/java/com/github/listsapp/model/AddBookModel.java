package com.github.listsapp.model;

import androidx.lifecycle.Observer;

import com.github.listsapp.repository.Repository;
import com.github.listsapp.util.Book;

import java.util.ArrayList;
import java.util.List;

public class AddBookModel {


    private static AddBookModel addBookModel;

    private AddBookModel()
    {



    }

    public static AddBookModel getInstance(){
        if(addBookModel == null)
            addBookModel = new AddBookModel();

        return addBookModel;
    }

    public void uploadFile()
    {

    }
}
