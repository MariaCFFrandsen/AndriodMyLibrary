package com.github.listsapp.repository.remotedata;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.listsapp.util.api.GBookList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class NetworkImpl{


    private final MutableLiveData<GBookList> searchedBooks;
    private static NetworkImpl network;

    private NetworkImpl() {
        GBookList bookList = new GBookList();
        bookList.setItems(new ArrayList<>());
        searchedBooks = new MutableLiveData<>(bookList);
    }

    public static NetworkImpl getInstance(){
        if(network == null)
            network = new NetworkImpl();

        return network;
    }

    public LiveData<GBookList> getSearchedBooks() {
        return searchedBooks;
    }

    public void searchForBooks(String query) {
        BooksAPI booksAPI = ServiceGenerator.getBooksAPI();
        Call<GBookList> call = booksAPI.getBook(query);
        call.enqueue(new Callback<GBookList>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<GBookList> call, Response<GBookList> response) {
                if (response.isSuccessful()) {
                    searchedBooks.setValue(response.body());

                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<GBookList> call, Throwable t) {
                Log.i("Retrofit ", "Retrieving from google.books.com failed");
            }
        });
    }


}
