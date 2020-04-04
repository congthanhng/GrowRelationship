package com.spark.cong.growrelationship.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.spark.cong.growrelationship.Architecture.Entity.GroupPeole;
import com.spark.cong.growrelationship.R;

import java.util.List;

public class GroupPeopleRecyclerViewAdapter extends RecyclerView.Adapter<GroupPeopleRecyclerViewAdapter.GpeopleViewHolder> {
    private List<GroupPeole> lstGroupPeople;
    @NonNull
    @Override
    public GpeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_people,parent,false);
        return new GpeopleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GpeopleViewHolder holder, int position) {
        if(lstGroupPeople != null){
            GroupPeole groupPeole =lstGroupPeople.get(position);
            holder.txt_g_name.setText(groupPeole.getName().toString());
        }
    }

    @Override
    public int getItemCount() {
        if(lstGroupPeople != null){
            return lstGroupPeople.size();
        }
        return 0;
    }

    public void setData(List<GroupPeole> groupPeoles){
        this.lstGroupPeople = groupPeoles;
        notifyDataSetChanged();
    }

    //viewHolder
    public class GpeopleViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_g_name;
        public GpeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_g_name = (TextView) itemView.findViewById(R.id.txt_g_name);
        }
    }
}
