package com.example.busbuddydemo;

import static com.example.busbuddydemo.LoginPage.REQUEST_CODE_FINE_LOCATION;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.busbuddydemo.databinding.ActivityMapBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class Map_Activity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private ActivityMapBinding binding;

    private static final String TAG = "MapActivity";
    int LOCATION_REQUEST_CODE = 10001;

    double latitude = 0;
    double longitude = 0;

    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        mMap = googleMap;

        beforeMapReady(mMap);

        Log.e(TAG, "onSuccess: " + latitude);

        if(latitude > 0 && longitude > 0) {
            Log.e(TAG, "result1234432 : " + longitude);
        }
    }

    private void beforeMapReady(GoogleMap mMap) {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();

            locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        //We have a location
                        Log.d(TAG, "onSuccess: " + location);
                        Log.d(TAG, "onSuccess: " + location.getLatitude());
                        Log.d(TAG, "onSuccess: " + location.getLongitude());
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        Log.e(TAG, "result : " + longitude);

                        if (latitude > 0 && longitude > 0) {
                            LatLng obj = new LatLng(latitude, longitude);
                            mMap.addMarker(new MarkerOptions().position(obj).title("Your Position"));
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(obj, 16));
                        }

                    } else {
                        Log.d(TAG, "onSuccess: Location was null...");
                    }
                }
            });

            locationTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "onFailure: " + e.getLocalizedMessage());
                }
            });
        }else {
            ActivityCompat.requestPermissions(Map_Activity.this, new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_FINE_LOCATION);
        }
    }


    public void onBackPressed() {
        Intent intent = new Intent(Map_Activity.this, BusActivity.class);
        startActivity(intent);
        finish();
    }
}