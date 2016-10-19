package com.bignerdranch.android.tripper;

import java.util.UUID;

/**
 * Created by Paul J Jensen on 19/10/2016.
 */

public class Settings {

    private UUID mId;
    private String mSID;
    private String mName;
    private String mEmail;
    private String mGender;
    private String mComment;

    public String getSID() {
        return mSID;
    }

    public void setSID(String SID) {
        mSID = SID;
    }


    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }


    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }


    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }


    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }


    public Settings() {
        this(UUID.randomUUID());
    }

    public Settings(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }




}
