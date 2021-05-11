package com.github.listsapp.util;

import android.widget.ImageView;

import java.sql.Timestamp;

public class Book {

    private String username;
    private int id;
    private String title;
    private int bookCover;
    private String readStatus;
    private boolean owned;
    private int rating;
    private String author;
    private double price;
    private Timestamp timestamp;

    public Book()
    {

    }

    public Book(int id, String title, int bookCover, String readStatus, boolean owned, int rating, String author) {
        this.id = id;
        this.title = title;
        this.bookCover = bookCover;
        this.readStatus = readStatus;
        this.owned = owned;
        this.rating = rating;
        this.author = author;
    }


    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public int getIconId() {
        return bookCover;
    }

    public int getId() {
        return id;
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

    public int getBookCover() {
        return bookCover;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
