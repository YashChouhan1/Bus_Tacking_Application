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

public class BUS_6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_bus6);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Data_For_Bus[] mydata = new Data_For_Bus[]{
                new Data_For_Bus("1. ITI SQ." , "06:55:00 AM" , "08:55:00 AM"),
                new Data_For_Bus("2. CLARK COLONY" , "06:56:00 AM" , "08:56:00 AM"),
                new Data_For_Bus("3. ELEC. COMPLEX" , "06:57:00 AM" , "08:57:00 AM"),
                new Data_For_Bus("4. WHITE TEMLE" , "06:58:00 AM" , "08:58:00 AM"),
                new Data_For_Bus("5. PARDESI PURA" , "07:00:00 AM" , "09:00:00 AM"),
                new Data_For_Bus("6. TEEN PULIYA" , "07:02:00 AM" , "09:02:00 AM"),
                new Data_For_Bus("7. NANDA NAGAR" , "07:03:00 AM" , "09:03:00 AM"),
                new Data_For_Bus("8. PATNIPURA" , "07:04:00 AM" , "09:04:00 AM"),
                new Data_For_Bus("9. MALWA MIL" , "07:06:00 AM" , "09:06:00 AM"),
                new Data_For_Bus("10. MARUTI SHW." , "07:08:00 AM" , "09:08:00 AM"),
                new Data_For_Bus("11. RANI SATI GATE" , "07:09:00 AM" , "09:09:00 AM"),
                new Data_For_Bus("12. LENTURN SQ" , "07:10:00 AM" , "09:10:00 AM"),
                new Data_For_Bus("13. JANJIRWALA SQ." , "07:11:00 AM" , "09:11:00 AM"),
                new Data_For_Bus("14. DHOBI GHAT" , "07:12:00 AM" , "09:12:00 AM"),
                new Data_For_Bus("15. GRT. KAILASH HOSP." , "07:13:00 AM" , "09:13:00 AM"),
                new Data_For_Bus("16. NAVNEET TOWER" , "07:14:00 AM" , "09:14:00 AM"),
                new Data_For_Bus("17. ANAND BAZAR SQ." , "07:16:00 AM" , "09:16:00 AM"),
                new Data_For_Bus("18. CHANDRA NAGAR" , "07:18:00 AM" , "09:18:00 AM"),
                new Data_For_Bus("19. PUSHP NANAR" , "07:19:00 AM" , "09:19:00 AM"),
                new Data_For_Bus("20. PIPLIYAHANA TALAB" , "07:20:00 AM" , "09:20:00 AM"),
                new Data_For_Bus("21. ANAND VAN" , "07:21:00 AM" , "09:21:00 AM"),
                new Data_For_Bus("22. APS" , "07:25:00 AM" , "09:25:00 AM"),
                new Data_For_Bus("23. CDGI" , "07:45:00 AM" , "09:45:00 AM"),
        };

        Adapter_for_Bus adapter = new Adapter_for_Bus(mydata, BUS_6.this);
        recyclerView.setAdapter(adapter);
    }

    public void onBackPressed() {
        Intent intent = new Intent(BUS_6.this, Bus_List.class);
        startActivity(intent);
        finish();
    }
}