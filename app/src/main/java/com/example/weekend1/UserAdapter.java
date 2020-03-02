package com.example.weekend1;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<DataViewHolder> {


    private List<UserData> dataSet;




    public void setDataSet(List<UserData> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DataViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.rv_details,
                                parent,
                                false)

        );

    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        holder.rvDate.setText(dataSet.get(position).UserDate);
        holder.rvGender.setText(dataSet.get(position).UserGender);
        holder.rvCountry.setText(dataSet.get(position).UserCountry);
        holder.rvName.setText(dataSet.get(position).UserName);


    }

    @Override
    public int getItemCount() {

        return dataSet != null ? dataSet.size() : 0;

    }
}

