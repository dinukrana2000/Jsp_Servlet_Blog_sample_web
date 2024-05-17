package com.example.demo.model;
public class Post {
    private String title;
    private String description;
    private String author;
    private java.sql.Timestamp datePosted;
    private String imageUrl;

    private int id;


    public int getId(){
        return id;
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public java.sql.Timestamp getDatePosted() {
        return datePosted;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDatePosted(java.sql.Timestamp datePosted) {
        this.datePosted = datePosted;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setId(int id) {
        this.id = id;
    }
}
