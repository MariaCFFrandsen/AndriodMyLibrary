package com.github.listsapp.util;

import android.widget.ImageView;

public class Book {

    private String title;
    private int bookCover;
    private String readStatus;
    private boolean owned;
    private int rating;
    private String author;


    public Book(String title, int bookCover, String readStatus, boolean owned, int rating, String author) {
        this.title = title;
        this.bookCover = bookCover;
        this.readStatus = readStatus;
        this.owned = owned;
        this.rating = rating;
        this.author = author;
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

    public String getAuthor() {
        return author;
    }
}
