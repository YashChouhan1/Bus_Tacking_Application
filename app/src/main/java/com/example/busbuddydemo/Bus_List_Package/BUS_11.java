package com.example.busbuddydemo.Bus_List_Package;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.busbuddydemo.Adapter_for_Bus;
import com.example.busbuddydemo.Bus_List;
import com.example.busbuddydemo.Data_For_Bus;
import com.example.busbuddydemo.R;

public class BUS_11 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_for_bus11);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Data_For_Bus[] mydata = new Data_For_Bus[]{
                new Data_For_Bus("1. MANGLIA", "06:30:00 AM", "08:30:00 AM"),
                new Data_For_Bus("2. PANCHVATI", "06:40:00 AM", "08:40:00 AM"),
                new Data_For_Bus("3. DEWAS NAKA", "06:42:00 AM", "08:42:00 AM"),
                new Data_For_Bus("4. VIJAY NAGAR ", "06:48:00 AM", "08:48:00 AM"),
                new Data_For_Bus("5. SWAGAT GARDEN", "06:50:00 AM", "08:50:00 AM"),
                new Data_For_Bus("6. DAINEK BHASKAR", "06:51:00 AM", "08:51:00 AM"),
                new Data_For_Bus("7. SAKET SQ", "06:52:00 AM", "08:52:00 AM"),
                new Data_For_Bus("8. PATRAKAR SQ.", "06:53:00 AM", "08:53:00 AM"),
                new Data_For_Bus("9. SANVID NAGAR ", "06:54:00 AM", "08:54:00 AM"),
                new Data_For_Bus("10. SAKET SQ", "06:55:00 AM", "08:55:00 AM"),
                new Data_For_Bus("11. NAKODA SWEETS", "07:00:00 AM", "09:00:00 AM"),
                new Data_For_Bus("12. ADARSH SCHOOL", "07:03:00 AM", "09:03:00 AM"),
                new Data_For_Bus("13. POLICE LINE", "07:23:00 AM", "09:23:00 AM"),
                new Data_For_Bus("14. COLMBIA CONVENT", "07:25:00 AM", "09:25:00 AM"),
                new Data_For_Bus("15. CDGI", "07:45:00 AM", "09:45:00 AM"),
        };

        Adapter_for_Bus adapter = new Adapter_for_Bus(mydata, BUS_11.this);
        recyclerView.setAdapter(adapter);
    }

    public void onBackPressed() {
        Intent intent = new Intent(BUS_11.this, Bus_List.class);
        startActivity(intent);
        finish();
    }
}