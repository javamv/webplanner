package com.autowebinar.core.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by VMoskalenko on 05.01.2017.
 */
@Document(collection = "users")
public class User {

    @Id
    private String id;

    String username;
    String email;
    String image;
    String googleId;
    String signature;
    String luxMail;

    public User(String username, String email, String image, String googleId) {
        this.username = username;
        this.email = email;
        this.image = image;
        this.googleId = googleId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getLuxMail() {
        return luxMail;
    }

    public void setLuxMail(String luxMail) {
        this.luxMail = luxMail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }
}
