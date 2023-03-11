package com.example.busbuddydemo.Bus_List_Package;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.busbuddydemo.Adapter_for_Bus;
import com.example.busbuddydemo.BusActivity;
import com.example.busbuddydemo.Bus_List;
import com.example.busbuddydemo.Data_For_Bus;
import com.example.busbuddydemo.R;
import com.example.busbuddydemo.RouteActivity;

public class BUS_5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_bus5);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Data_For_Bus[] mydata = new Data_For_Bus[]{
                new Data_For_Bus("1. CHHOTA BANGADA" , "06:35:00 AM" , "08:35:00 AM"),
                new Data_For_Bus("2. LAXMIPURI SQ." , "06:45:00 AM" , "08:45:00 AM"),
                new Data_For_Bus("3. MODERN SCHOOL" , "06:47:00 AM" , "08:47:00 AM"),
                new Data_For_Bus("4. SCHEME NO. 51" , "06:48:00 AM" , "08:48:00 AM"),
                new Data_For_Bus("5. NEELAKASH SCH." , "06:49:00 AM" , "08:49:00 AM"),
                new Data_For_Bus("6. KHADE GANPATI" , "06:52:00 AM" , "06:52:00 AM"),
                new Data_For_Bus("7. MAHESH GUARD" , "06:53:00 AM" , "08:53:00 AM"),
                new Data_For_Bus("8. CDGI" , "07:45:00 AM" , "09:45:00 AM"),
        };

        Adapter_for_Bus adapter = new Adapter_for_Bus(mydata, BUS_5.this);
        recyclerView.setAdapter(adapter);
    }

    public void onBackPressed() {
        Intent intent = new Intent(BUS_5.this, Bus_List.class);
        startActivity(intent);
        finish();
    }
}