package com.example.busbuddydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Driver_Activity extends AppCompatActivity {

    Button startSharing , stopSharing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        startSharing = findViewById(R.id.stopSharing);   //little mismatch but ok....
        stopSharing = findViewById(R.id.startSharing);

        startSharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Driver_Activity.this, "Location sharing started", Toast.LENGTH_SHORT).show();
            }
        });

        stopSharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Driver_Activity.this, "Location sharing stopped", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onBackPressed() {
        Intent intent = new Intent(Driver_Activity.this, LoginPage.class);
        startActivity(intent);
        finish();
    }
}