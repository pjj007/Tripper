package com.bignerdranch.android.tripper.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Paul J Jensen on 19/10/2016.
 */

public class SettingsBaseHelper extends SQLiteOpenHelper{

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "settingsBase.db";

    public SettingsBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + SettingsDbSchema.SettingsTable.NAME + "(" + "" +
                        "_id integer primary key autoincrement, " +
                        SettingsDbSchema.SettingsTable.Cols.UUID + ", " +
                        SettingsDbSchema.SettingsTable.Cols.NAME + ", " +
                        SettingsDbSchema.SettingsTable.Cols.SID + ", " +
                        SettingsDbSchema.SettingsTable.Cols.EMAIL + ", " +
                        SettingsDbSchema.SettingsTable.Cols.GENDER + ", " +
                        SettingsDbSchema.SettingsTable.Cols.COMMENT + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
