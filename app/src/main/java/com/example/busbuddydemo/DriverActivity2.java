package com.example.busbuddydemo;

import static android.content.ContentValues.TAG;
import static android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class DriverActivity2 extends AppCompatActivity {

    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    static String busId;
    int callingActivity;
    Button start, stop;
    Animation scaleUp, scaleDown;
    ImageView imageView;
    TextView textView;
    private boolean isPermission;
    private LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        start = findViewById(R.id.stopSharing);
        stop = findViewById(R.id.startSharing);
        imageView = findViewById(R.id.image);
        textView = findViewById(R.id.busNo);

        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);

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

        textView.setText(busId);

        checkLocation();
        start.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == event.ACTION_DOWN) {
                    start.startAnimation(scaleUp);
                } else if (event.getAction() == event.ACTION_UP) {
                    start.startAnimation(scaleDown);
                }
                return false;
            }
        });

        stop.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == event.ACTION_DOWN) {
                    stop.startAnimation(scaleUp);
                } else if (event.getAction() == event.ACTION_UP) {
                    stop.startAnimation(scaleDown);
                }
                return false;
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ContextCompat.checkSelfPermission(
                            getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(
                                DriverActivity2.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                REQUEST_CODE_LOCATION_PERMISSION
                        );
                    } else {
                        checkLocation();
                        startLocationService();
                    }
                }
            });

            stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stopLocationService();
                }
            });


            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(DriverActivity2.this);
                    dialog.setTitle("Info.");
                    dialog.setMessage("PLEASE KEEP YOUR INTERNET AND GPS ON. Here you can start and stop sharing your location for which you have to always keep your gps location setting turned ON." +
                            "We recommend you to start location sharing when you going to start driving bus and also keep in mind to stop location sharing " +
                            "when you are done with your driving services. Thank You :) ");

                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Handle positive button click
                        }
                    });
                    dialog.show();
                }
            });
        }

        private boolean checkLocation() {

            if (!isLocationEnabled()) {
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
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) &&
                    locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startLocationService();
            } else{
                Toast.makeText(this, "Permission Denied!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isLocationServiceRunning(){
        ActivityManager activityManager =
                (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if(activityManager != null){
            for(ActivityManager.RunningServiceInfo service :
                 activityManager.getRunningServices(Integer.MAX_VALUE)){
                if(LocationServices2.class.getName().equals(service.service.getClassName())){
                    if(service.foreground){
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
     }

     private void startLocationService(){
        if(!isLocationServiceRunning()){
            Intent intent = new Intent(getApplicationContext(), LocationServices2.class);
            intent.setAction(Constants.ACTION_START_LOCATION_SERVICE);
            startService(intent);
            Toast.makeText(this, "Location Services Started", Toast.LENGTH_SHORT).show();
        }
     }

     private void stopLocationService(){
         if(!isLocationServiceRunning()){
             Toast.makeText(this, "Please start location service first!!", Toast.LENGTH_SHORT).show();

         }
         if(isLocationServiceRunning()){
             Intent intent = new Intent(getApplicationContext(), LocationServices2.class);
             intent.setAction(Constants.ACTION_STOP_LOCATION_SERVICE);
             startService(intent);
             Toast.makeText(this, "Location Services Stopped", Toast.LENGTH_SHORT).show();
         }
     }

    public void onBackPressed() {
        if (callingActivity == ActivityConstants.ACTIVITY_FROM_LOGIN) {
            Intent intent = new Intent(DriverActivity2.this, Bus_List_3.class);
            startActivity(intent);
            finish();
        }
        if (callingActivity == ActivityConstants.ACTIVITY_FROM_SIGNUP) {
            Intent intent = new Intent(DriverActivity2.this, LoginPage.class);
            startActivity(intent);
            finish();
        }
    }
}