package com.bignerdranch.android.tripper;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.File;
import java.util.UUID;

import static android.content.ContentValues.TAG;

/**
 * Created by PJ Jensen on 18/10/2016.
 */

public class TripFragment extends Fragment implements AdapterView.OnItemSelectedListener, GoogleApiClient.ConnectionCallbacks {

    private static final String ARG_TRIP_ID = "trip_id";
    private static final int REQUEST_PHOTO = 0;
    private static final int REQUEST_ERROR = 1;

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
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;
    private File mPhotoFile;
    private GoogleApiClient mClient;
    private Button mLocationButton;



    public static TripFragment newInstance(UUID tripId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TRIP_ID, tripId);

        TripFragment fragment = new TripFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onPause() {
        super.onPause();

        TripLab.get(getActivity()).updateTrip(mTrip);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID tripId = (UUID) getActivity().getIntent().getSerializableExtra(MainActivity.EXTRA_TRIP_ID);
        mTrip = TripLab.get(getActivity()).getTrip(tripId);

        mPhotoFile = TripLab.get(getActivity()).getPhotoFile(mTrip);

        mClient = new GoogleApiClient.Builder(getActivity()).addApi(LocationServices.API)
                .build();

        mClient = new GoogleApiClient.Builder(getActivity())

                .addApi(LocationServices.API)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected (Bundle bundle){
                getActivity().invalidateOptionsMenu();
            }
            @Override
            public void onConnectionSuspended (int i){
            }
        })
                .build();

    }

    @Override
    public void onConnectionSuspended (int i){
    }

    @Override
    public void onConnected(final Bundle connectionHint) {
        LocationRequest request = LocationRequest.create();
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        request.setNumUpdates(1);
        request.setInterval(0);


        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission
        .ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            return;
        }



        LocationServices.FusedLocationApi.requestLocationUpdates(mClient,
                request, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        Log.d(TAG, "got a fix: " + location);
//                        mTrip.setLongitude(Longitude);
                        double dLatitude = location.getLatitude();
                        double dLongitude = location.getLongitude();
                        String longitude = Double.toString(dLongitude);
                        String latitude = Double.toString(dLatitude);
                        mTrip.setLongitude(longitude);
                        mTrip.setLatitude(latitude);
                        mTrip.setLocation(location);


                    }
                });

    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().invalidateOptionsMenu();
        mClient.connect();

    }
    @Override
    public void onStop() {
        super.onStop();
        mClient.disconnect();

    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trip, container, false);
 //       View f = inflater.inflate(R.layout.view_camera_and_title, container, false);

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

            }

            @Override
            public void afterTextChanged(Editable s) {
                mTrip.setTitle(s.toString());
                Log.d("aaaaa", "on text change" + mTrip.getTitle());
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


        PackageManager packageManager = getActivity().getPackageManager();

        mPhotoButton = (ImageButton) v.findViewById(R.id.trip_camera);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        boolean canTakePhoto = mPhotoFile != null &&
                captureImage.resolveActivity(packageManager) != null;
        mPhotoButton.setEnabled(canTakePhoto);

        if (canTakePhoto) {
            Uri uri = Uri.fromFile(mPhotoFile);
            captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }

        mPhotoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivityForResult(captureImage, REQUEST_PHOTO);

            }
        });

        mPhotoView = (ImageView) v.findViewById(R.id.trip_photo);
        updatePhotoView();


        mLocationButton = (Button) v.findViewById(R.id.trip_location_button);
        mLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a Uri from an intent string. Use the result to create an Intent.
//                Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988");
//                Uri gmmIntentUri = Uri.parse("geo: 46.414382,10.013988");
                Uri gmmIntentUri = Uri.parse("geo: " + mTrip.getLatitude() + ", " + mTrip.getLongitude());
// Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
// Make the Intent explicit by setting the Google Maps package
                mapIntent.setPackage("com.google.android.apps.maps");

// Attempt to start an activity that can handle the Intent
                startActivity(mapIntent);
            }
        });


        return v;
    }

    private void updatePhotoView() {
        if (mPhotoFile == null || mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        } else if (requestCode == REQUEST_PHOTO) {
            updatePhotoView();
        }
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
