package com.bignerdranch.android.tripper;

import java.util.Date;
import java.util.UUID;

/**
 * Created by PJ Jensen on 18/10/2016.
 */

public class Trip {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Trip() {
        this(UUID.randomUUID());
    }

    public Trip(UUID id) {
        mId = id;
        mDate = new Date();
    }

    public Date getDate() {
        return mDate;
    }
    public void setDate(Date date) {
        mDate = date;
    }
    public boolean isSolved() {
        return mSolved;
    }
    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public UUID getId() {
        return mId;
    }
    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String title) {
        mTitle = title;
    }
}
