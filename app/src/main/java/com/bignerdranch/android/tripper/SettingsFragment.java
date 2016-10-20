package com.bignerdranch.android.tripper;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by Paul J Jensen on 19/10/2016.
 */

public class SettingsFragment extends Fragment {

    private static final String ARG_SETTINGS_ID = "settings_id";

    private Settings mSettings;
    private EditText mName;
    private EditText mId;
    private EditText mEmail;
    private EditText mGender;
    private EditText mComment;

    public static SettingsFragment newInstance(UUID settingsId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SETTINGS_ID, settingsId);

        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onPause() {
        super.onPause();

        SettingsLab.get(getActivity()).updateSettings(mSettings);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSettings = SettingsLab.get(getActivity()).getSettings();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        mName = (EditText) v.findViewById(R.id.settings_name);
        mName.setText(mSettings.getName());
        mName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mSettings.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mId = (EditText) v.findViewById(R.id.settings_id);
        mId.setText(mSettings.getName());
        mId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mSettings.setSID(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEmail = (EditText) v.findViewById(R.id.settings_email);
        mEmail.setText(mSettings.getEmail());
        mEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mSettings.setEmail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mGender = (EditText) v.findViewById(R.id.settings_gender);
        mGender.setText(mSettings.getGender());
        mGender.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mSettings.setGender(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mComment = (EditText) v.findViewById(R.id.settings_comment);
        mComment.setText(mSettings.getComment());
        mComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mSettings.setComment(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        return v;

    }


}
