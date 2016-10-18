package com.bignerdranch.android.tripper;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class MainActivity extends SingleFragmentActivity {

    public static final String EXTRA_TRIP_ID = "com.example.trip_id";

    public static Intent newIntent(Context packageContext, UUID tripId) {
        Intent intent = new Intent(packageContext, MainActivity.class);
        intent.putExtra(EXTRA_TRIP_ID, tripId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {

        UUID tripId = (UUID) getIntent().getSerializableExtra(EXTRA_TRIP_ID);
        return TripFragment.newInstance(tripId);

    }
}
