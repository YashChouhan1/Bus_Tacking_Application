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
    String[] array = {"Choose Bus :-","Bus No 1" , "Bus No 2" ,"Bus No 3","Bus No 4","Bus No 5","Bus No 6","Bus No 7","Bus No 8"
                        ,"Bus No 9","Bus No 10","Bus No 11","Bus No 12","Bus No 13","Bus No 14","Bus No 15"};
    public static String busId;
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
                    Toast.makeText(Bus_List2.this, "Please select the bus which you want to track.", Toast.LENGTH_SHORT).show();
                }
                else{
                    busId = spinner.getSelectedItem().toString();
                    Intent intent = new Intent(Bus_List2.this, RetriveMapLocation.class);
                    intent.putExtra("calling-activity", ActivityConstants.ACTIVITY_FROM_LOGIN);
                    startActivity(intent);
                    finish();
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