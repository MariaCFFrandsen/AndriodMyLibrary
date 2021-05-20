package com.github.listsapp.repository.remotedata;

import com.github.listsapp.util.api.GBookList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BooksAPI {

    //get request with query ?q=q

    @GET("/books/v1/volumes")
    Call<GBookList> getBook(@Query("q") String q);
}
