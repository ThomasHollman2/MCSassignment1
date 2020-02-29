package com.example.weekend1;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;


public class DataViewHolder extends RecyclerView.ViewHolder {

    TextView rvName, rvCountry, rvGender, rvDate;


    public DataViewHolder(@NonNull View itemView) {
        super(itemView);
        rvName = itemView.findViewById(R.id.rv_name);
        rvCountry = itemView.findViewById(R.id.rv_country);
        rvGender = itemView.findViewById(R.id.rv_gender);
        rvDate = itemView.findViewById(R.id.rv_date);


    }
}
