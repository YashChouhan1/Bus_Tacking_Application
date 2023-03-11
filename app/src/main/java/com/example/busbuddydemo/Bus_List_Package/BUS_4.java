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

public class BUS_4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_bus4);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Data_For_Bus[] mydata = new Data_For_Bus[]{
                new Data_For_Bus("1. MHOW NAKA", "07:00:00 AM", "09:00:00 AM"),
                new Data_For_Bus("2. DRAVID NAGAR", "07:03:00 AM", "09:03:00 AM"),
                new Data_For_Bus("3. RANJIT HANUMAN", "07:05:00 AM", "09:05:00 AM"),
                new Data_For_Bus("4. SHANKAR KIRANA", "07:06:00 AM", "09:06:00 AM"),
                new Data_For_Bus("5. SHIVA TEMPLE", "07:09:00 AM", "09:09:00 AM"),
                new Data_For_Bus("6. SETHI GATE", "07:11:00 AM", "09:11:00 AM"),
                new Data_For_Bus("7. DOMINOS SHOP", "07:14:00 AM", "09:14:00 AM"),
                new Data_For_Bus("8. LAXMAN GATE", "07:16:00 AM", "09:16:00 AM"),
                new Data_For_Bus("9. SUDAMA NAGAR", "07:17:00 AM", "09:17:00 AM"),
                new Data_For_Bus("10. MAHARASTRA BK.", "07:18:00 AM", "09:18:00 AM"),
                new Data_For_Bus("11. CDGI", "07:45:00 AM", "09:45:00 AM"),
        };

        Adapter_for_Bus adapter = new Adapter_for_Bus(mydata, BUS_4.this);
        recyclerView.setAdapter(adapter);
    }

    public void onBackPressed() {
        Intent intent = new Intent(BUS_4.this, Bus_List.class);
        startActivity(intent);
        finish();
    }
}