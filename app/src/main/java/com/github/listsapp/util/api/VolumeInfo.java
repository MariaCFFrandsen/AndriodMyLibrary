package com.github.listsapp.util.api;

import java.util.List;

public class VolumeInfo {
    private String title;
    private String subtitle;
    private String publisher;
    private String description;
    private ImageLinks imageLinks;
    private double averageRating;
    private int pageCount;
    private List<String> authors;
    private List<String> categories;


    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getListToString(List<String> list)
    {
        StringBuilder sbString = new StringBuilder("");

        for(String item : list){

            sbString.append(item).append(", ");
        }

        String strList = sbString.toString();

        if( strList.length() > 0 )
            strList = strList.substring(0, strList.length() - 1);

        return strList;
    }
}