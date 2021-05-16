package com.github.listsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.listsapp.model.LibraryModel;
import com.github.listsapp.util.Book;
import com.github.listsapp.util.api.GBook;

public class GBookDetailsViewModel extends ViewModel {

    private static MutableLiveData<GBook> gBookMutableLiveData;



    public static void setChosenGBook(GBook gBook) {
        if(gBookMutableLiveData == null)
            gBookMutableLiveData = new MutableLiveData<>(new GBook());
        gBookMutableLiveData.setValue(gBook);
    }

    public static LiveData<GBook> getgBookMutableLiveData() {
        return gBookMutableLiveData;
    }
}
