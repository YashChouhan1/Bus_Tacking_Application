package com.example.busbuddydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.busbuddydemo.Bus_List_Package.BUS_1;
import com.example.busbuddydemo.Bus_List_Package.BUS_2;

public class Bus_List2 extends AppCompatActivity {

    Spinner spinner;
    Button button;
    String[] array = {"Choose Bus :-","Bus no.1" , "Bus no.2" ,"Bus no.3","Bus no.4","Bus no.5","Bus no.6","Bus no.7","Bus no.8"
            ,"Bus no.9","Bus no.10","Bus no.11","Bus no.12","Bus no.13","Bus no.14","Bus no.15"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner);
        spinner = findViewById(R.id.spinner1);
        button = findViewById(R.id.signoutButton);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Bus_List2.this, android.R.layout.simple_dropdown_item_1line, array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Choose Bus :-")){

                }
                else {
                    String values = parent.getItemAtPosition(position).toString();
                    Toast.makeText(Bus_List2.this, "Bus Selected -> " + (position), Toast.LENGTH_LONG).show();

                    if(parent.getItemAtPosition(position).equals("Bus no.1")) {
                        Intent intent = new Intent(Bus_List2.this, RetriveMapLocation.class);
                        startActivity(intent);
                        finish();
                    }

                    if(parent.getItemAtPosition(position).equals("Bus no.2")) {
                        Intent intent = new Intent(Bus_List2.this, MapsActivityNew.class);
                        startActivity(intent);
                        finish();
                    }

                    if(parent.getItemAtPosition(position).equals("Bus no.3")) {
                        Intent intent = new Intent(Bus_List2.this, test.class);
                        startActivity(intent);
                        finish();
                    }

//                    if(parent.getItemAtPosition(position).equals(array[position])) {
//                        Intent intent = new Intent(Bus_List2.this, BusActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Bus_List2.this, BusActivity.class);
        startActivity(intent);
        finish();
    }

}