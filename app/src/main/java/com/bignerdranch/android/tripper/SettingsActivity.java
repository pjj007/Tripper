package com.bignerdranch.android.tripper;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;


/**
 * Created by Paul J Jensen on 19/10/2016.
 */

public class SettingsActivity extends SingleFragmentActivity {

    public static final String EXTRA_SETTINGS_ID = "com.example.trip_id";

    public static Intent newIntent(Context packageContext, UUID settingsId) {
        Intent intent = new Intent(packageContext, SettingsActivity.class);
        intent.putExtra(EXTRA_SETTINGS_ID, settingsId);
        return intent;
    }


    @Override
    protected Fragment createFragment() {

        UUID settingsId = (UUID) getIntent().getSerializableExtra(EXTRA_SETTINGS_ID);
        return SettingsFragment.newInstance(settingsId);


    }

}
