package com.example.busbuddydemo;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.busbuddydemo.databinding.RetriveMapLocationBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RetriveMapLocation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private RetriveMapLocationBinding binding;
    String busId = Bus_List2.busId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = RetriveMapLocationBinding.inflate(getLayoutInflater());
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

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Bus Location").child(busId);

        ValueEventListener listener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild("latitude") && snapshot.hasChild("longitude")) {
                    String latitude = snapshot.child("latitude").getValue(String.class);
                    String longitude = snapshot.child("longitude").getValue(String.class);
                    String name = snapshot.child("Bus No").getValue(String.class);

                    assert latitude != null;
                    double Lat = Double.parseDouble(latitude);
                    assert longitude != null;
                    double Long = Double.parseDouble(longitude);

                    LatLng location = new LatLng(Lat, Long);
                    mMap.clear();
                    mMap.addMarker(new MarkerOptions().position(location).title(name));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16f));

                }else{
                    Toast.makeText(RetriveMapLocation.this, "Location for this bus is not set yet!!.", Toast.LENGTH_SHORT).show();
                    Toast.makeText(RetriveMapLocation.this, "Location for this bus is not set yet!!.", Toast.LENGTH_SHORT).show();
                    Toast.makeText(RetriveMapLocation.this, "Location for this bus is not set yet!!.", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "EMPTY LATITUDE AND LONGITUDE");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void onBackPressed() {
        Intent intent = new Intent(RetriveMapLocation.this, Bus_List2.class);
        startActivity(intent);
        finish();
    }
}