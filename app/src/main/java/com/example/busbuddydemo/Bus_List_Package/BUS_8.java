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

public class BUS_8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_for_bus8);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Data_For_Bus[] mydata = new Data_For_Bus[]{
                new Data_For_Bus("1. SHANTI NAGAR" , "06:55:00 AM" , "08:55:00 AM"),
                new Data_For_Bus("2. MHOW GAON" , "06:56:00 AM" , "08:56:00 AM"),
                new Data_For_Bus("3. DHAR NAKA" , "06:57:00 AM" , "08:57:00 AM"),
                new Data_For_Bus("4. PETROL PUMP" , "06:58:00 AM" , "08:58:00 AM"),
                new Data_For_Bus("5. KODRIYA GAON" , "07:00:00 AM" , "09:00:00 AM"),
                new Data_For_Bus("6. PRASHANTI HOSP." , "07:02:00 AM" , "09:02:00 AM"),
                new Data_For_Bus("7. DREAM LAND" , "07:03:00 AM" , "09:03:00 AM"),
                new Data_For_Bus("8. MHOWHARI GATE" , "07:04:00 AM" , "09:04:00 AM"),
                new Data_For_Bus("9. MHOW BAKERY" , "07:06:00 AM" , "09:06:00 AM"),
                new Data_For_Bus("10. KISHAN GANJ" , "07:08:00 AM" , "09:08:00 AM"),
                new Data_For_Bus("11. VETERNARY COLG." , "07:09:00 AM" , "09:09:00 AM"),
                new Data_For_Bus("12. RAU BYPASS SQ" , "07:10:00 AM" , "09:10:00 AM"),
                new Data_For_Bus("13. MAMA KA DHABA" , "07:11:00 AM" , "09:11:00 AM"),
                new Data_For_Bus("14. RAU BUS STAND" , "07:12:00 AM" , "09:12:00 AM"),
                new Data_For_Bus("15. RAU MUKTIDHAM" , "07:13:00 AM" , "09:13:00 AM"),
                new Data_For_Bus("16. RAU RADIO ST." , "07:14:00 AM" , "09:14:00 AM"),
                new Data_For_Bus("17. SHIV CITY" , "07:16:00 AM" , "09:16:00 AM"),
                new Data_For_Bus("18. SILICON MAIN" , "07:18:00 AM" , "09:18:00 AM"),
                new Data_For_Bus("19. SILICON CLUB" , "07:19:00 AM" , "09:19:00 AM"),
                new Data_For_Bus("20. SILICON CITY" , "07:25:00 AM" , "09:25:00 AM"),
                new Data_For_Bus("21. CDGI" , "07:45:00 AM" , "09:45:00 AM"),
        };

        Adapter_for_Bus adapter = new Adapter_for_Bus(mydata, BUS_8.this);
        recyclerView.setAdapter(adapter);
    }

    public void onBackPressed() {
        Intent intent = new Intent(BUS_8.this, Bus_List.class);
        startActivity(intent);
        finish();
    }
}