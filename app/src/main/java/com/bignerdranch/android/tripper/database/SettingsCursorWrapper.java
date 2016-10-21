package com.bignerdranch.android.tripper.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.tripper.Settings;

import java.util.UUID;

/**
 * Created by Paul J Jensen on 19/10/2016.
 */

public class SettingsCursorWrapper extends CursorWrapper {

    public SettingsCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Settings getSettings() {
        String uuidString = getString(getColumnIndex(SettingsDbSchema.SettingsTable.Cols.UUID));
        String name = getString(getColumnIndex(SettingsDbSchema.SettingsTable.Cols.NAME));
        String SID = getString(getColumnIndex(SettingsDbSchema.SettingsTable.Cols.SID));
        String email = getString(getColumnIndex(SettingsDbSchema.SettingsTable.Cols.EMAIL));
        String gender = getString(getColumnIndex(SettingsDbSchema.SettingsTable.Cols.GENDER));
        String comment = getString(getColumnIndex(SettingsDbSchema.SettingsTable.Cols.COMMENT));

        Settings setting = new Settings(UUID.fromString(uuidString));
        setting.setName(name);
        setting.setSID(SID);
        setting.setEmail(email);
        setting.setGender(gender);
        setting.setComment(comment);

        return setting;

    }

}
