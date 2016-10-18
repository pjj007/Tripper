package com.bignerdranch.android.tripper.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.tripper.Trip;

import java.util.Date;
import java.util.UUID;

/**
 * Created by PJ Jensen on 18/10/2016.
 */

public class TripCursorWrapper extends CursorWrapper {

    public TripCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Trip getTrip() {
        String uuidString = getString(getColumnIndex(TripDbSchema.TripTable.Cols.UUID));
        String title = getString(getColumnIndex(TripDbSchema.TripTable.Cols.TITLE));
        long date = getLong(getColumnIndex(TripDbSchema.TripTable.Cols.DATE));
//        int isSolved = getInt(getColumnIndex(TripDbSchema.TripTable.Cols.SOLVED));
        String tripType = getString(getColumnIndex(TripDbSchema.TripTable.Cols.TRIP_TYPE));
        String destination = getString(getColumnIndex(TripDbSchema.TripTable.Cols.DESTINATION));
        int duration = getInt(getColumnIndex(TripDbSchema.TripTable.Cols.DURATION));
        String comment = getString(getColumnIndex(TripDbSchema.TripTable.Cols.COMMENT));

        Trip trip = new Trip(UUID.fromString(uuidString));
        trip.setTitle(title);
        trip.setDate(new Date(date));
//        trip.setSolved((isSolved != 0));
        trip.setTripType(tripType);
        trip.setDestination(destination);
        trip.setDuration(duration);
        trip.setComment(comment);

        return trip;

    }
}
