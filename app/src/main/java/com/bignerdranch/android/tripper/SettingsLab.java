package com.bignerdranch.android.tripper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.tripper.database.SettingsBaseHelper;
import com.bignerdranch.android.tripper.database.SettingsCursorWrapper;
import com.bignerdranch.android.tripper.database.SettingsDbSchema;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul J Jensen on 19/10/2016.
 */

public class SettingsLab  {

    private static SettingsLab sSettingsLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static SettingsLab get(Context context) {
        if (sSettingsLab == null) {
            sSettingsLab = new SettingsLab(context);
        }
        return sSettingsLab;
    }

    private SettingsLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new SettingsBaseHelper(mContext).getWritableDatabase();
    }

    public List<Settings> getSetting() {

        List<Settings> settings = new ArrayList<>();

        SettingsCursorWrapper cursor = querySettings(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                settings.add(cursor.getSettings());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return settings;
    }


    public Settings getSettings() {


        SettingsCursorWrapper cursor = querySettings(null, null);

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getSettings();
        } finally {
            cursor.close();
        }

//        return null;
    }

    public void addSettings (Settings s) {

        ContentValues values = getContentValues(s);

        mDatabase.insert(SettingsDbSchema.SettingsTable.NAME, null, values);
        updateSettings(s);
    }

    public void updateSettings(Settings settings) {
        String uuidString = settings.getId().toString();
        ContentValues values = getContentValues(settings);

        mDatabase.update(SettingsDbSchema.SettingsTable.NAME, values,
                SettingsDbSchema.SettingsTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }

    public void deleteSettings (Settings settings) {
        String uuidString = settings.getId().toString();
        mDatabase.delete(
                SettingsDbSchema.SettingsTable.NAME,
                SettingsDbSchema.SettingsTable.Cols.UUID + " = ?",
                new String[] {uuidString}
        );
    }


    private static ContentValues getContentValues(Settings setting) {
        ContentValues values = new ContentValues();
        values.put(SettingsDbSchema.SettingsTable.Cols.UUID, setting.getId().toString());
        values.put(SettingsDbSchema.SettingsTable.Cols.NAME, setting.getName());
        values.put(SettingsDbSchema.SettingsTable.Cols.SID, setting.getSID());
        values.put(SettingsDbSchema.SettingsTable.Cols.EMAIL, setting.getEmail());
        values.put(SettingsDbSchema.SettingsTable.Cols.GENDER, setting.getGender());
        values.put(SettingsDbSchema.SettingsTable.Cols.COMMENT, setting.getComment());

        return values;
    }

    private SettingsCursorWrapper querySettings(String whereClause, String[] whereArgs){

        Cursor cursor = mDatabase.query(
                SettingsDbSchema.SettingsTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, //having
                null // orderBy
        );

//        return cursor;
        return new SettingsCursorWrapper(cursor);
    }

}
