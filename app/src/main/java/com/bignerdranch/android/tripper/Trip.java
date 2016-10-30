package com.bignerdranch.android.tripper;

import android.location.Location;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by PJ Jensen on 18/10/2016.
 */

public class Trip {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private String mTripType;
    private String mDestination;
    private String mDuration;
    private String mComment;
    private String mLatitude;
    private String mLongitude;
    private Location mLocation;

    private ArrayList<String> mTripTypeList = new ArrayList<>();

//    public TripType() {
//        mTripTypeList.add("Work");
//        mTripTypeList.add("Personal");
//        mTripTypeList.add("Commute");
//    }

    public int getTripTypeList() {
        return mTripTypeList.indexOf(mTripType);
    }


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

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTripType() {
        return mTripType;
    }

    public void setTripType(int TripType) {
        mTripType = mTripTypeList.get(TripType);
    }

    public void setTripType(String tripType) {
        mTripType = tripType;
    }

    public String getDestination() {
        return mDestination;
    }

    public void setDestination(String Destination) {
        mDestination = Destination;
    }

    public String getDuration() {
        return mDuration;
    }

    public void setDuration(String Duration) {
        mDuration = Duration;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String Comment) {
        mComment = Comment;
    }

    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(Location location) {
        mLocation = location;
    }

    public String getLongitude() {
        return mLongitude;
    }

    public void setLongitude(String Longitude) {
        mLongitude = Longitude;
    }

    public String getLatitude() {
        return mLatitude;
    }

    public void setLatitude(String Latitude) {
        mLatitude = Latitude;
    }

}
