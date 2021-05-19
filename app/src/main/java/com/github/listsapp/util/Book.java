package com.github.listsapp.util;

import android.net.Uri;
import android.widget.ImageView;

import com.github.listsapp.util.api.GBook;

import java.util.Date;

public class Book {

    private String username;
    private int id;
    private String title;
    private int bookCover;
    private String readStatus;
    private boolean owned;
    private float rating;
    private String author;
    private double price;
    private Date timestamp;
    private int pagecount;
    private String name;
    private String imageUrl;
    private int onPage;
    private String lastUpdated;


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

    public static Book CreateBookFromGBook(GBook gBook) {

        Book book = new Book();
        book.setTitle(gBook.getVolumeInfo().getTitle());
        book.setAuthor(gBook.getVolumeInfo().getListToString(gBook.getVolumeInfo().getAuthors()));
        book.setPagecount(gBook.getVolumeInfo().getPageCount());
        book.setImageUrl(gBook.getVolumeInfo().getImageLinks().getThumbnail());
        return book;

    }


    public void setOnPage(int onPage) {
        this.onPage = onPage;
    }

    public int getOnPage() {
        return onPage;
    }

    public int getPagecount() {
        return pagecount;
    }

    public void setPagecount(int pagecount) {
        this.pagecount = pagecount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
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

    public int getBookCover() {
        return bookCover;
    }

    public int getId() {
        return id;
    }

    public float getRating() {
        return rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBookCover(int bookCover) {
        this.bookCover = bookCover;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "Book{" +
                "username='" + username + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", bookCover=" + bookCover +
                ", readStatus='" + readStatus + '\'' +
                ", owned=" + owned +
                ", rating=" + rating +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", timestamp=" + timestamp +
                ", pagecount=" + pagecount +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
