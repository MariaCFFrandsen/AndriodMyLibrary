package com.github.listsapp.view.gbook_details;

import android.content.ContentResolver;
import android.net.Uri;
import android.webkit.MimeTypeMap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.listsapp.repository.Repository;
import com.github.listsapp.util.Book;
import com.github.listsapp.util.api.GBook;
import com.github.listsapp.util.callbackinterfaces.CallBackForAddGBook;
import com.github.listsapp.view.library.LibraryBookAdapter;
import com.github.listsapp.view.library.LibrarySearchAdapter;

public class GBookDetailsViewModel extends ViewModel {

    private static MutableLiveData<GBook> gBookMutableLiveData;
    private Repository repository = Repository.getInstance();
    public static void setChosenGBook(GBook gBook) {
        if(gBookMutableLiveData == null)
            gBookMutableLiveData = new MutableLiveData<>(new GBook());
        gBookMutableLiveData.setValue(gBook);
    }

    public static LiveData<GBook> getgBookMutableLiveData() {
        return gBookMutableLiveData;
    }

    public void addGBookToLibrary(Book book, String imageUrl, String imagename, CallBackForAddGBook callBackForAddGBook) {
        repository.addBook(book, LibrarySearchAdapter.getUsername(), callBackForAddGBook);
    }

}
