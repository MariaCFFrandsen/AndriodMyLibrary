package com.github.listsapp.util;

import android.widget.ImageView;

public class Book {

    private String title;
    private int bookCover;
    private String readStatus;
    private boolean owned;
    private int rating;


    public Book(String name, int bookCover, String readStatus, boolean owned, int rating) {
        title = name;
        this.bookCover = bookCover;
        this.readStatus = readStatus;
        this.owned = owned;
        this.rating = rating;
    }

    public String getName() {
        return title;
    }

    public int getIconId() {
        return bookCover;
    }


    public int getRating() {
        return rating;
    }

    public String getmName() {
        return title;
    }

    public String getReadStatus() {
        return readStatus;
    }

    public boolean isOwned() {
        return owned;
    }
}
