package com.bignerdranch.android.tripper.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PJ Jensen on 18/10/2016.
 */

public class TripBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "tripBase.db";

    public TripBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TripDbSchema.TripTable.NAME + "(" + "" +
                "_id integer primary key autoincrement, " +
                TripDbSchema.TripTable.Cols.UUID + ", " +
                TripDbSchema.TripTable.Cols.TITLE + ", " +
                TripDbSchema.TripTable.Cols.DATE + ", " +
                TripDbSchema.TripTable.Cols.TRIP_TYPE + ", " +
                TripDbSchema.TripTable.Cols.DESTINATION + ", " +
                TripDbSchema.TripTable.Cols.DURATION + ", " +
                TripDbSchema.TripTable.Cols.COMMENT + ", " +
                TripDbSchema.TripTable.Cols.LATITUDE + ", " +
                TripDbSchema.TripTable.Cols.LONGITUDE + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
