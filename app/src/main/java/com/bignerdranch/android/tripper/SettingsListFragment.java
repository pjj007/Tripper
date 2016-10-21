package com.bignerdranch.android.tripper;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Paul J Jensen on 19/10/2016.
 */

public class SettingsListFragment extends Fragment {

    private RecyclerView mSettingsRecyclerView;
    private SettingsAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings_list, container, false);
        mSettingsRecyclerView = (RecyclerView) view
                .findViewById(R.id.settings_recycler_view);
        mSettingsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }


    private void updateUI() {
        SettingsLab settingsLab = SettingsLab.get(getActivity());
        List<Settings> settings = settingsLab.getSetting();

        if (mAdapter == null) {
            mAdapter = new SettingsAdapter(settings);
            mSettingsRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setSettings(settings);
            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }



    private class SettingsAdapter extends RecyclerView.Adapter<SettingsHolder> {

        private List<Settings> mSettings;

        public SettingsAdapter(List<Settings> settings) {
            mSettings = settings;
        }

        @Override
        public SettingsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_settings, parent, false);
            return new SettingsHolder(view);
        }

        @Override
        public void onBindViewHolder(SettingsHolder holder, int position) {
            Settings settings = mSettings.get(position);
            holder.bindSettings(settings);
        }

        @Override
        public int getItemCount() {
            return mSettings.size();
        }

        public void setSettings(List<Settings> settings) {
            mSettings = settings;
        }
    }


    private class SettingsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Settings mSettings;

        private TextView mNameTextView;
        private TextView mSidTextView;
        private TextView mEmailTextView;
        private TextView mGenderTextView;
        private TextView mCommentTextView;


        public SettingsHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mNameTextView = (TextView) itemView.findViewById(R.id.list_item_settings_name_text_view);
            mSidTextView = (TextView) itemView.findViewById(R.id.list_item_settings_sid_text_view);
            mEmailTextView = (TextView) itemView.findViewById(R.id.list_item_settings_email_text_view);
            mGenderTextView = (TextView) itemView.findViewById(R.id.list_item_settings_gender_text_view);
            mCommentTextView = (TextView) itemView.findViewById(R.id.list_item_settings_comment_text_view);
        }

        public void bindSettings(Settings settings) {
            mSettings = settings;
            mNameTextView.setText(mSettings.getName());
            mSidTextView.setText(mSettings.getSID());
            mEmailTextView.setText(mSettings.getEmail());
            mGenderTextView.setText(mSettings.getGender());
            mCommentTextView.setText(mSettings.getComment());
        }

        public void onClick(View v) {

            Intent intent = MainActivity.newIntent(getActivity(), mSettings.getId());
            startActivity(intent);

        }
    }
}


