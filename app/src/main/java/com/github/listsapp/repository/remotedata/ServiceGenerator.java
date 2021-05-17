package com.github.listsapp.repository.remotedata;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final String url = "https://www.googleapis.com";

    private static BooksAPI booksAPI;

    public static BooksAPI getBooksAPI() {
        if (booksAPI == null) {
            booksAPI = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(BooksAPI.class);
        }
        return booksAPI;
    }
}
