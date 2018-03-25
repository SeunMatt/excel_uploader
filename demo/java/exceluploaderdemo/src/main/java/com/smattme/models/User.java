package com.smattme.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Seun Matt on 24-Mar-18
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String phone;

    private Date createdAt;
    private Date updatedAt;


    public User() {

    }

    public User(String firstName, String password, String email) {
        this.name = firstName;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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
        return "User Details: \n" +
                " First Name: " + name +
                " Last Name: " + password +
                " Email: " + email;
    }
}
