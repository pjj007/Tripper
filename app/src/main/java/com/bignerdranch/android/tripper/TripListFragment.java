package com.bignerdranch.android.tripper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by PJ Jensen on 18/10/2016.
 */

public class TripListFragment extends Fragment {

    private RecyclerView mTripRecyclerView;
    private TripAdapter mAdapter;
    private Button mNewButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trip_list, container, false);
        mTripRecyclerView = (RecyclerView) view
                .findViewById(R.id.trip_recycler_view);
        mTripRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        mNewButton = (Button) view.findViewById(R.id.new_trip_button);
        mNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Trip trip = new Trip();
                TripLab.get(getActivity()).addTrip(trip);
                Intent intent = MainActivity.newIntent(getActivity(), trip.getId());
                startActivity(intent);

            }
        });

        updateUI();

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        TripLab tripLab = TripLab.get(getActivity());
        List<Trip> trips = tripLab.getTrips();

        if (mAdapter == null) {
            mAdapter = new TripAdapter(trips);
            mTripRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setTrips(trips);
            mAdapter.notifyDataSetChanged();
        }
//        updateSubtitle();
    }

//    private void updateSubtitle() {
//    }

    private class TripHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Trip mTrip;
        private TextView mTitleTextView;
        private TextView mDateTextView;
//        private CheckBox mSolvedCheckBox;

        public TripHolder(View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_trip_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_trip_date_text_view);
//            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_trip_solved_check_box);
        }

        public void bindTrip(Trip trip) {
            mTrip = trip;
            mTitleTextView.setText(mTrip.getTitle());
            mDateTextView.setText(mTrip.getDate().toString());
//            mSolvedCheckBox.setChecked(mTrip.isSolved());
        }

        public void onClick(View v) {

            Intent intent = MainActivity.newIntent(getActivity(), mTrip.getId());
            startActivity(intent);

        }
    }

    private class TripAdapter extends RecyclerView.Adapter<TripHolder> {

        private List<Trip> mTrips;

        public TripAdapter(List<Trip> trips) {
            mTrips = trips;
        }

        @Override
        public TripHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_trip, parent, false);
            return new TripHolder(view);
        }
        @Override
        public void onBindViewHolder(TripHolder holder, int position) {
            Trip trip = mTrips.get(position);
            holder.bindTrip(trip);
        }
        @Override
        public int getItemCount() {
            return mTrips.size();
        }

        public void setTrips(List<Trip> trips) {
            mTrips = trips;
        }
    }
}
