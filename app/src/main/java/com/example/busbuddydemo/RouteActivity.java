package com.example.busbuddydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;



public class RouteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
    }

    public void onBackPressed() {
        Intent intent = new Intent(RouteActivity.this, Bus_List.class);
        startActivity(intent);
        finish();
}
}