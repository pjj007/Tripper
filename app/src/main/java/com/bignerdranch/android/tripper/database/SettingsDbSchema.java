package com.bignerdranch.android.tripper.database;

/**
 * Created by Paul J Jensen on 19/10/2016.
 */

public class SettingsDbSchema {

    public static final class SettingsTable {
        public static final String NAME = "Settings";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String SID = "student_id";
            public static final String EMAIL = "email";
            public static final String GENDER = "gender";
            public static final String COMMENT = "comment";

        }
    }

}
