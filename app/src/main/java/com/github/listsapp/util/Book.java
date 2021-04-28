package com.github.listsapp.util;

import android.widget.ImageView;

public class Book {

    private String title;
    private int bookCover;
    private String readStatus;
    private boolean owned;
    private int rating;


    public Book(String title, int bookCover, String readStatus, boolean owned, int rating) {
        this.title = title;
        this.bookCover = bookCover;
        this.readStatus = readStatus;
        this.owned = owned;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public int getIconId() {
        return bookCover;
    }


    public int getRating() {
        return rating;
    }


    public String getReadStatus() {
        return readStatus;
    }

    public boolean isOwned() {
        return owned;
    }
}
