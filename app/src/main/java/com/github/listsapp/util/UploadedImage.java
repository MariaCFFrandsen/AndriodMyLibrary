package com.github.listsapp.util;

public class UploadedImage {

    private String name;
    private String imageUrl;

    public UploadedImage()
    {

    }

    public UploadedImage(String name, String imageUrl)
    {
        if(name.trim().equals(""))
        {
            name = "No name";
        }
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
