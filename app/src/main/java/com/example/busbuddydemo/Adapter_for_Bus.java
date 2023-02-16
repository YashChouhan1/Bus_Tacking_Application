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
import com.example.busbuddydemo.Bus_List_Package.BUS_2;

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
