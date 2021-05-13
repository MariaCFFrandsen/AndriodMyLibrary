package com.github.listsapp.util;

import java.util.List;

public class Library {

    private List<Book> books;

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Library()
    {

    }
    public Library(List<Book> books)
    {
        this.books = books;
    }
}
