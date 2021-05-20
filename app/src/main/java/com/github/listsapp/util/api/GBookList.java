package com.github.listsapp.util.api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

//POJO to map the json format for get request to google.books.com
//includes a list of GBook which includes a VolumeInfo which includes ImageLinks
//only the information used is stored

public class GBookList extends GBook implements Serializable {

    @SerializedName("items")
    private List<GBook> items;

    @SerializedName("totalItems")
    private int count;
    public List<GBook> getItems() {
        return items;
    }

    public void setItems(List<GBook> items) {
        this.items = items;
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    @Override
    public String getTitle() {
        return super.getTitle();
    }

    public int getCount() {
        return count;
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    @Override
    public void setVolumeInfo(VolumeInfo volumeInfo) {
        super.setVolumeInfo(volumeInfo);
    }

    public void setCount(int count) {
        this.count = count;
    }
}