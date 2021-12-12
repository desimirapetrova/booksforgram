package com.example.booksforgram.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "blogs")
public class Blog extends BaseEntity{
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    @Lob
    private String  description;
    @ManyToOne
    private Picture picture;

    @ManyToOne
    private User author;

    public Blog() {
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
