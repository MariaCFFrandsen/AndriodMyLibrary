package com.github.listsapp.repository.network;

import com.github.listsapp.util.Book;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BooksAPI {

    @GET("api/v2/pokemon/{name}")
    Call<Book> getBook(@Path("name") String name);
}
