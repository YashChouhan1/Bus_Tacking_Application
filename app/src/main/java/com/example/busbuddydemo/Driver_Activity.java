package com.example.busbuddydemo;

import static android.content.ContentValues.TAG;
import static android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.HashMap;

public class Driver_Activity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {

    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    private LocationManager mLocationManager;
    private LocationRequest mLocationRequest;
    private com.google.android.gms.location.LocationListener listener;
    private final long UPDATE_INTERVAL = 5000;
    private final long FASTEST_INTERVAL = 5000;
    private static final int REQUEST_LOCATION_SETTINGS = 1;
    private final long MIN_DISTANCE = 5;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private LatLng latLng;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference().child("Bus Location");
    private boolean isPermission;
    Button startSharing, stopSharing;
    ImageView imageView;
    //String busId = getIntent().getStringExtra("key");

//    Bundle bundle = getIntent().getExtras();
//    String data = bundle.getString("key");

    static String busId;
    int callingActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        startSharing = findViewById(R.id.stopSharing);   //little mismatch but ok....
        stopSharing = findViewById(R.id.startSharing);
        imageView = findViewById(R.id.image);

        callingActivity = getIntent().getIntExtra("calling-activity", 0);
        switch (callingActivity) {                               // this is used to know the previous activity
            case ActivityConstants.ACTIVITY_FROM_SIGNUP:      // because there are two bus list first in driver login
                busId = Driver_Signup.busId;                 // and second in driver signup.
                break;
            case ActivityConstants.ACTIVITY_FROM_LOGIN:
                busId = Bus_List_3.busId;
                break;
        }
        Log.e(TAG, busId);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(Driver_Activity.this);
                dialog.setTitle("Warning");
                dialog.setMessage("** Please do not cut this page until location sharing is started. Location sharing will start when toast messages start appearing. If <u>not responding dialog box," +
                        "</u> appears then click on <b>wait</b>. Because it take some time to start and stop location sharing");

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle positive button click
                    }
                });
                dialog.show();
            }
        });

        if (requestSinglePermission()) {

            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();

            mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

            checkLocation();
        }
        if (checkLocation()) {
            startSharing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Driver_Activity.this, "Location sharing started", Toast.LENGTH_SHORT).show();
                    onStart();
                }
            });

            stopSharing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Driver_Activity.this, "Location sharing stopped", Toast.LENGTH_SHORT).show();
                    if (mGoogleApiClient.isConnected()) {
                        mGoogleApiClient.disconnect();
                    }
                }
            });
        }
    }

    private boolean checkLocation() {

        if(!isLocationEnabled()){
            showAlert();
        }
        return isLocationEnabled();
    }

    private void showAlert() {

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Your locations settings is set to 'Off'." +
                        "\nPlease enable location to use bus buddy application")
                .setPositiveButton("Location Setting", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    private boolean isLocationEnabled() {

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private boolean requestSinglePermission() {

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        isPermission = true;
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        if (permissionDeniedResponse.isPermanentlyDenied()) {
                            isPermission  = false;
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                }).check();

        return isPermission;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED){
            return;
        }
        startLocationUpdates();
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if(mLocation == null){
            startLocationUpdates();
        }
        else{
            Toast.makeText(this, "Location not detected", Toast.LENGTH_SHORT).show();
        }
    }

    private void startLocationUpdates() {

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){
            return;
        }

        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, UPDATE_INTERVAL, (float) MIN_DISTANCE, (android.location.LocationListener) locationListener);

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest, this);
    }
    @Override
    public void onLocationChanged(@NonNull Location location) {
        String message = "Updated Locaton : " + Double.toString(location.getLatitude()) + "," +
                Double.toString(location.getLongitude());
        Log.e(TAG, String.valueOf(location.getLatitude()));
        Log.e(TAG, String.valueOf(location.getLongitude()));

        HashMap<String , String> hashMap = new HashMap<>();

        hashMap.put("latitude" , String.valueOf(location.getLatitude()));
        hashMap.put("longitude" , String.valueOf(location.getLongitude()));
        hashMap.put("Bus No", busId);

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        databaseReference.child(busId).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Driver_Activity.this , "Location Saved" , Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Driver_Activity.this , "Location Not Saved" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    protected void onStart() {
        super.onStart();

        if(mGoogleApiClient != null){
            mGoogleApiClient.connect();
        }
    }
    @Override
    public void onBackPressed() {
        if (callingActivity == ActivityConstants.ACTIVITY_FROM_LOGIN) {
            Intent intent = new Intent(Driver_Activity.this, LoginPage.class);
            startActivity(intent);
            finish();
        }
        if (callingActivity == ActivityConstants.ACTIVITY_FROM_SIGNUP) {
            Intent intent = new Intent(Driver_Activity.this, Driver_Signup.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    public void onConnectionSuspended(int i) {

    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

    }
}