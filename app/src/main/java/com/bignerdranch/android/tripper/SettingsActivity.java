package com.bignerdranch.android.tripper;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;


/**
 * Created by Paul J Jensen on 19/10/2016.
 */

public class SettingsActivity extends SingleFragmentActivity {

    public static final String EXTRA_SETTINGS_ID = "com.example.trip_id";

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, SettingsActivity.class);

        return intent;
    }


    @Override
    protected Fragment createFragment() {

        return SettingsFragment.newInstance();


    }

}
