package com.bignerdranch.android.tripper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.bignerdranch.android.tripper.database.TripBaseHelper;
import com.bignerdranch.android.tripper.database.TripCursorWrapper;
import com.bignerdranch.android.tripper.database.TripDbSchema;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by PJ Jensen on 18/10/2016.
 */

public class TripLab {

    private static TripLab sTripLab;
    //private List<Trip> mTrips;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static TripLab get(Context context) {
        if (sTripLab == null) {
            sTripLab = new TripLab(context);
        }
        return sTripLab;
    }
    private TripLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new TripBaseHelper(mContext).getWritableDatabase();

    }

    public List<Trip> getTrips() {

        List<Trip> trips = new ArrayList<>();

        TripCursorWrapper cursor = queryTrips(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                trips.add(cursor.getTrip());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return trips;
    }
    public Trip getTrip(UUID id) {


        TripCursorWrapper cursor = queryTrips(
                TripDbSchema.TripTable.Cols.UUID + " = ?",
                new String[] {
                        id.toString()
                }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getTrip();
        } finally {
            cursor.close();
        }

//        return null;
    }

    public File getPhotoFile(Trip trip) {
        File externalFilesDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        if (externalFilesDir == null) {
            return null;
        }

        return new File(externalFilesDir, trip.getPhotoFilename());
    }

    public void addTrip (Trip t) {

        ContentValues values = getContentValues(t);

        mDatabase.insert(TripDbSchema.TripTable.NAME, null, values);
        updateTrip(t);
    }

    public void updateTrip(Trip trip) {
        String uuidString = trip.getId().toString();
        ContentValues values = getContentValues(trip);

        mDatabase.update(TripDbSchema.TripTable.NAME, values,
                TripDbSchema.TripTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }

    public void deleteTrip (Trip trip) {
        String uuidString = trip.getId().toString();
        mDatabase.delete(
                TripDbSchema.TripTable.NAME,
                TripDbSchema.TripTable.Cols.UUID + " = ?",
                new String[] {uuidString}
        );
    }

    private static ContentValues getContentValues(Trip trip) {
        ContentValues values = new ContentValues();
        values.put(TripDbSchema.TripTable.Cols.UUID, trip.getId().toString());
        values.put(TripDbSchema.TripTable.Cols.TITLE, trip.getTitle());
        values.put(TripDbSchema.TripTable.Cols.DATE, trip.getDate().getTime());
        values.put(TripDbSchema.TripTable.Cols.TRIP_TYPE, trip.getTripType());
        values.put(TripDbSchema.TripTable.Cols.DESTINATION, trip.getDestination());
        values.put(TripDbSchema.TripTable.Cols.DURATION, trip.getDuration());
        values.put(TripDbSchema.TripTable.Cols.COMMENT, trip.getComment());

        return values;
    }
    private TripCursorWrapper queryTrips(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                TripDbSchema.TripTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, //having
                null // orderBy
        );

//        return cursor;
        return new TripCursorWrapper(cursor);
    }

}
