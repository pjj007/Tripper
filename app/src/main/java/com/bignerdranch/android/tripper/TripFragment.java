package com.bignerdranch.android.tripper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by PJ Jensen on 18/10/2016.
 */

public class TripFragment extends Fragment {

    private static final String ARG_TRIP_ID = "trip_id";

    private Trip mTrip;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    public static TripFragment newInstance(UUID tripId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TRIP_ID, tripId);

        TripFragment fragment = new TripFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        UUID tripId = (UUID) getActivity().getIntent().getSerializableExtra(MainActivity.EXTRA_TRIP_ID);
        mTrip = TripLab.get(getActivity()).getTrip(tripId);
    }

    @Override
    public void onPause() {
        super.onPause();

        TripLab.get(getActivity()).updateTrip(mTrip);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trip, container, false);

        mTitleField = (EditText)v.findViewById(R.id.trip_title);
        mTitleField.setText(mTrip.getTitle());

        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mTrip.setTitle(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateButton = (Button)v.findViewById(R.id.trip_date);
        mDateButton.setText(mTrip.getDate().toString());
        mDateButton.setEnabled(false);mSolvedCheckBox = (CheckBox)v.findViewById(R.id.trip_solved);
        mSolvedCheckBox.setChecked(mTrip.isSolved());

        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                mTrip.setSolved(isChecked);
            }
        });

        return v;
    }

}
