package com.spark.cong.growrelationship.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.spark.cong.growrelationship.Architecture.Entity.User;
import com.spark.cong.growrelationship.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyHolder>{
    List<User> lstName;
    public static class MyHolder extends RecyclerView.ViewHolder{
        TextView nameView;
        public MyHolder(View itemView){
            super(itemView);
            nameView = (TextView) itemView.findViewById(R.id.name_view);
        }
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.nameView.setText(lstName.get(position).getUserName());
    }

    @Override
    public int getItemCount() {
        if(lstName != null){
        return lstName.size();}
        return 0;
    }

    public void setLstName(List<User> lstName){
        this.lstName = lstName;
        notifyDataSetChanged();
    }


}
