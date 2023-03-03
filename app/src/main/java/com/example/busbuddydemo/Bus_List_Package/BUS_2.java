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

public class BUS_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_for_bus2);

        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Data_For_Bus[] mydata = new Data_For_Bus[]{
                new Data_For_Bus("1. BANGANGA THANA" , "06:40:00 AM" , "08:40:00 AM"),
                new Data_For_Bus("2. UJJAIN NAKA" , "06:43:00 AM" , "08:43:00 AM"),
                new Data_For_Bus("3. MARIMATA SQ." , "06:45:00 AM" , "08:45:00 AM"),
                new Data_For_Bus("4. POLOGROUND" , "06:46:00 AM" , "08:46:00 AM"),
                new Data_For_Bus("5. NARAYAN BAG" , "06:49:00 AM" , "08:49:00 AM"),
                new Data_For_Bus("6. SADAR BAZAR" , "06:50:00 AM" , "08:50:00 AM"),
                new Data_For_Bus("7. RAJWADA" , "06:55:00 AM" , "08:55:00 AM"),
                new Data_For_Bus("8. KOTHARI MARKET" , "06:57:00 AM" , "08:57:00 AM"),
                new Data_For_Bus("9. REGAL SQUARE" , "06:59:00 AM" , "08:59:00 AM"),
                new Data_For_Bus("10. HIGH COURT" , "07:00:00 AM" , "09:00:00 AM"),
                new Data_For_Bus("11. TREASUR ISLAND" , "07:02:00 AM" , "09:02:00 AM"),
                new Data_For_Bus("12. INDRAPRAST TWR." , "07:03:00 AM" , "09:03:00 AM"),
                new Data_For_Bus("13. 56 DUKAN" , "07:04:00 AM" , "09:04:00 AM"),
                new Data_For_Bus("14. PALASIA SQUARE" , "07:05:00 AM" , "09:05:00 AM"),
                new Data_For_Bus("15. SHEIKH HATIM" , "07:06:00 AM" , "09:06:00 AM"),
                new Data_For_Bus("16. GEETA BHWN. TPL." , "07:08:00 AM" , "09:08:00 AM"),
                new Data_For_Bus("17. GEETA BHAWAN SQ." , "07:10:00 AM" , "09:10:00 AM"),
                new Data_For_Bus("18. ST. PAUL" , "07:13:00 AM" , "09:13:00 AM"),
                new Data_For_Bus("19. AZAD NAGAR SQ." , "07:17:00 AM" , "09:17:00 AM"),
                new Data_For_Bus("20. CDGI" , "07:45:00 AM" , "09:45:00 AM"),
        };

        Adapter_for_Bus adapter = new Adapter_for_Bus(mydata, BUS_2.this);
        recyclerView.setAdapter(adapter);

    }

    public void onBackPressed() {
        Intent intent = new Intent(BUS_2.this, Bus_List.class);
        startActivity(intent);
        finish();
    }
}