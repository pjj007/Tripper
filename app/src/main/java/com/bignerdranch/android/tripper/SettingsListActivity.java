package com.bignerdranch.android.tripper;

import android.support.v4.app.Fragment;

/**
 * Created by Paul J Jensen on 19/10/2016.
 */

public class SettingsListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new SettingsListFragment();
    }

}
