package com.example.busbuddydemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class BusActivity extends AppCompatActivity {

    Button location, signoutButton;
    Button routes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        location = findViewById(R.id.mapSection);
        routes = findViewById(R.id.routeSection);
        signoutButton = findViewById(R.id.signoutButton);


        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusActivity.this, Map_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        routes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusActivity.this, Bus_List.class);
                startActivity(intent);
                finish();
            }
        });

        signoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }

    private void signOut() {
        GoogleSignInClient mGoogleSignInClient = LoginPage.mGoogleSignInClient;
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(BusActivity.this, LoginPage.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(BusActivity.this, "Successfully signed out.." , Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BusActivity.this, LoginPage.class);
        startActivity(intent);
        finish();
    }
}