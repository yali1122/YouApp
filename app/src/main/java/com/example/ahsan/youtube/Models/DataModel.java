package com.example.ahsan.youtube.Models;

/**
 * Created by Ahsan on 1/16/2017.
 */

public class DataModel {


    String url;
    String title;
    String author;
    String views;
    String duration;
    String image;

    public DataModel(String url,String title, String author , String views , String duration,String image ) {

        this.url = url;
        this.title = title;
        this.author = author;
        this.views = views;
        this.duration = duration;
        this.image=image;
    }



    public String getUrl() {
        return url;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getViews() {
        return views;
    }
    public String getDuration() {
        return duration;
    }
    public String getImage() {return image;}

}