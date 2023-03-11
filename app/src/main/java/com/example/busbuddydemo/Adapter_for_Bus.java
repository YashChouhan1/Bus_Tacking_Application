package com.example.busbuddydemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.busbuddydemo.Bus_List_Package.BUS_1;
import com.example.busbuddydemo.Bus_List_Package.BUS_10;
import com.example.busbuddydemo.Bus_List_Package.BUS_11;
import com.example.busbuddydemo.Bus_List_Package.BUS_12;
import com.example.busbuddydemo.Bus_List_Package.BUS_2;
import com.example.busbuddydemo.Bus_List_Package.BUS_3;
import com.example.busbuddydemo.Bus_List_Package.BUS_4;
import com.example.busbuddydemo.Bus_List_Package.BUS_5;
import com.example.busbuddydemo.Bus_List_Package.BUS_6;
import com.example.busbuddydemo.Bus_List_Package.BUS_8;
import com.example.busbuddydemo.Bus_List_Package.BUS_9;

public class Adapter_for_Bus extends RecyclerView.Adapter<Adapter_for_Bus.ViewHolder>{

    Data_For_Bus[] data;
    Context context;

    public Adapter_for_Bus(Data_For_Bus[] data , BUS_1 activity){
         this.data = data;
         this.context = activity;
    }
    public Adapter_for_Bus(Data_For_Bus[] data , BUS_2 activity){
        this.data = data;
        this.context = activity;
    }

    public Adapter_for_Bus(Data_For_Bus[] data , BUS_3 activity){
        this.data = data;
        this.context = activity;
    }

    public Adapter_for_Bus(Data_For_Bus[] data , BUS_4 activity){
        this.data = data;
        this.context = activity;
    }

    public Adapter_for_Bus(Data_For_Bus[] data , BUS_5 activity){
        this.data = data;
        this.context = activity;
    }

    public Adapter_for_Bus(Data_For_Bus[] data , BUS_6 activity){
        this.data = data;
        this.context = activity;
    }

    public Adapter_for_Bus(Data_For_Bus[] data , BUS_8 activity){
        this.data = data;
        this.context = activity;
    }
    public Adapter_for_Bus(Data_For_Bus[] data , BUS_9 activity){
        this.data = data;
        this.context = activity;
    }
    public Adapter_for_Bus(Data_For_Bus[] data , BUS_10 activity){
        this.data = data;
        this.context = activity;
    }
    public Adapter_for_Bus(Data_For_Bus[] data , BUS_11 activity){
        this.data = data;
        this.context = activity;
    }
    public Adapter_for_Bus(Data_For_Bus[] data , BUS_12 activity){
        this.data = data;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview_for_bus_adapter , parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Data_For_Bus mylist = data[position];
        holder.textplaceName.setText(mylist.getPlaceName());
        holder.textfirstShift.setText(mylist.getFirstShift());
        holder.textsecondShift.setText(mylist.getSecondShift());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, mylist.getPlaceName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textplaceName;
        TextView textfirstShift;
        TextView textsecondShift;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textplaceName = itemView.findViewById(R.id.placename);
            textfirstShift = itemView.findViewById(R.id.firstShift);
            textsecondShift = itemView.findViewById(R.id.secondShift);
        }
    }
}
