package com.bignerdranch.android.tripper.database;

/**
 * Created by PJ Jensen on 18/10/2016.
 */

public class TripDbSchema {

    public static final class TripTable {
        public static final String NAME = "Trips";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";

        }
    }
}
