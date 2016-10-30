package com.bignerdranch.android.tripper;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import java.util.UUID;

public class MainActivity extends SingleFragmentActivity {

    public static final String EXTRA_TRIP_ID = "com.example.trip_id";
    private static final int REQUEST_ERROR = 0;

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

    @Override
    protected void onResume() {
        super.onResume();
        int errorCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if(errorCode != ConnectionResult.SUCCESS) {
            Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(errorCode, this, REQUEST_ERROR,
                    new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            finish();
                        }
                    });
            errorDialog.show();;
        }
    }
}
