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

public class BUS_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_bus3);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Data_For_Bus[] mydata = new Data_For_Bus[]{
                new Data_For_Bus("1. SATYASAI SQ.", "06:45:00 AM", "08:45:00 AM"),
                new Data_For_Bus("2. SHALIMAR TWS.", "06:46:00 AM", "08:46:00 AM"),
                new Data_For_Bus("3. MAHINDRA SWR.", "06:47:00 AM", "08:47:00 AM"),
                new Data_For_Bus("4. SCHEME NO. 114", "06:48:00 AM", "08:48:00 AM"),
                new Data_For_Bus("5. SCHEME NO. 78", "06:50:00 AM", "08:50:00 AM"),
                new Data_For_Bus("6. SANGEET ADY78", "06:51:00 AM", "08:51:00 AM"),
                new Data_For_Bus("7. NARMDA COLONY SQ.", "06:52:00 AM", "08:52:00 AM"),
                new Data_For_Bus("8. PRESTIGE COLLEGE", "06:53:00 AM", "08:53:00 AM"),
                new Data_For_Bus("9. LIFE CARE HOSP.", "06:54:00 AM", "08:54:00 AM"),
                new Data_For_Bus("10. NYAY NAGAR SQ.", "06:55:00 AM", "08:55:00 AM"),
                new Data_For_Bus("11. MR-10 CHOURAHA", "07:00:00 AM", "09:00:00 AM"),
                new Data_For_Bus("12. HEERA NAGAR SQ.", "07:03:00 AM", "09:03:00 AM"),
                new Data_For_Bus("13. BAPAT CHOURAHA", "07:05:00 AM", "09:05:00 AM"),
                new Data_For_Bus("14. FOURTUNE LDMK", "07:06:00 AM", "09:06:00 AM"),
                new Data_For_Bus("15. MEGHDOOT GARDEN", "07:07:00 AM", "09:07:00 AM"),
                new Data_For_Bus("16. KRISHNA DAIRY", "07:08:00 AM", "09:08:00 AM"),
                new Data_For_Bus("17. VIJAY NAGAR", "07:10:00 AM", "09:10:00 AM"),
                new Data_For_Bus("18. CDGI", "07:45:00 AM", "09:45:00 AM"),
        };

        Adapter_for_Bus adapter = new Adapter_for_Bus(mydata, BUS_3.this);
        recyclerView.setAdapter(adapter);
    }

    public void onBackPressed() {
        Intent intent = new Intent(BUS_3.this, Bus_List.class);
        startActivity(intent);
        finish();
    }
}