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

public class BUS_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_for_bus1);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Data_For_Bus[] mydata = new Data_For_Bus[]{
                new Data_For_Bus("1. PALIYA" , "06:25:00 AM" , "08:25:00 AM"),
                new Data_For_Bus("2. HATOD" , "06:32:00 AM" , "08:32:00 AM"),
                new Data_For_Bus("3. GANDHI NAGAR" , "06:45:00 AM" , "08:45:00 AM"),
                new Data_For_Bus("4. AERODRUM THANA" , "06:50:00 AM" , "08:50:00 AM"),
                new Data_For_Bus("5. VIDHYA DHAM" , "06:51:00 AM" , "08:51:00 AM"),
                new Data_For_Bus("6. KALANI NAGAR" , "06:52:00 AM" , "06:52:00 AM"),
                new Data_For_Bus("7. SHIKSHAK NAGAR" , "06:56:00 AM" , "08:56:00 AM"),
                new Data_For_Bus("8. BHATIYA HOSPITAL" , "06:58:00 AM" , "08:58:00 AM"),
                new Data_For_Bus("9. RAMCHANDRA NGR." , "07:00:00 AM" , "09:00:00 AM"),
                new Data_For_Bus("10. CDGI" , "07:45:00 AM" , "09:45:00 AM"),
        };

        Adapter_for_Bus adapter = new Adapter_for_Bus(mydata, BUS_1.this);
        recyclerView.setAdapter(adapter);
    }

    public void onBackPressed() {
        Intent intent = new Intent(BUS_1.this, Bus_List.class);
        startActivity(intent);
        finish();
    }
}