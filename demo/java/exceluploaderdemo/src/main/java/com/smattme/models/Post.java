package com.smattme.models;

import javafx.geometry.Pos;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Seun Matt on 24-Mar-18
 */
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @Column(unique = true)
    private String title;
    private String post;

    @ManyToOne(targetEntity = User.class)
    private User author;

    private Date createdAt;
    private Date updatedAt;

    public Post() {}

    public Post(String title, String post, User author) {
        this.title = title;
        this.post = post;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @PrePersist
    public void createTimestamps() {
        if(createdAt == null)
            createdAt = new Date();
        if(updatedAt == null)
            updatedAt = new Date();
    }

    @PreUpdate
    public void updateTimestamps () {
        if(updatedAt == null)
            updatedAt = new Date();
    }

    @Override
    public String toString() {
        return "Post Details \n" +
                " Title: "  + title +
                " Post: " + post +
                " Created On: " + createdAt +
                " Last Updated On: " + updatedAt;
    }
}
