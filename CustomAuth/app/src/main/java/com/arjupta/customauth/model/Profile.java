package com.arjupta.customauth.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by michaellimantara on 20/3/16.
 */
public class Profile {

    private long id;
    private String avatar;
    private String name;

    @SerializedName("phone")
    private String contactNo;

    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
