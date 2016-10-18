package com.bignerdranch.android.tripper;

import android.support.v4.app.Fragment;

/**
 * Created by PJ Jensen on 18/10/2016.
 */

public class TripListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new TripListFragment();
    }
}
