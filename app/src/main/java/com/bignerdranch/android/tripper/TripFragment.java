package com.bignerdranch.android.tripper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.UUID;

/**
 * Created by PJ Jensen on 18/10/2016.
 */

public class TripFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String ARG_TRIP_ID = "trip_id";

    private Trip mTrip;
    private EditText mTitleField;
    private Button mDateButton;
    //    private CheckBox mSolvedCheckBox;
    private Spinner mTripType;
    private EditText mDestination;
    private EditText mDuration;
    private EditText mComment;
    private Button mDeleteButton;
    private Button mSaveButton;
    private Button mCancelButton;

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

        mTitleField = (EditText) v.findViewById(R.id.trip_title);
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

        mDateButton = (Button) v.findViewById(R.id.trip_date);
        mDateButton.setText(mTrip.getDate().toString());
        mDateButton.setEnabled(false);


        mDeleteButton = (Button) v.findViewById(R.id.trip_delete);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),
                        R.string.delete_toast, Toast.LENGTH_SHORT).show();
                TripLab.get(getActivity()).deleteTrip(mTrip);
                getActivity().finish();
            }
        });


        mSaveButton = (Button) v.findViewById(R.id.trip_save);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),
                        R.string.save_toast, Toast.LENGTH_SHORT).show();
                TripLab.get(getActivity()).updateTrip(mTrip);
                getActivity().finish();
            }
        });

        mCancelButton = (Button) v.findViewById(R.id.trip_cancel) ;
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TripLab.get(getActivity()).deleteTrip(mTrip);
                getActivity().finish();
            }
        });


//        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.trip_solved);
//        mSolvedCheckBox.setChecked(mTrip.isSolved());
//
//        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                mTrip.setSolved(isChecked);
//            }
//        });

        mComment = (EditText) v.findViewById(R.id.trip_comment);
        mComment.setText(mTrip.getComment());
        mComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mTrip.setComment(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDestination = (EditText) v.findViewById(R.id.trip_destination);
        mDestination.setText(mTrip.getDestination());
        mDestination.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mTrip.setDestination(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mDuration = (EditText) v.findViewById(R.id.trip_duration);
        mDuration.setText(mTrip.getDuration());
        mDuration.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mTrip.setDuration(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mTripType = (Spinner) v.findViewById(R.id.trip_type_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.trip_type_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
// Apply the adapter to the spinner
        mTripType.setAdapter(adapter);


        return v;
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        Spinner spinner = (Spinner) view.findViewById(R.id.trip_type_spinner);
        spinner.setOnItemSelectedListener(this);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}
