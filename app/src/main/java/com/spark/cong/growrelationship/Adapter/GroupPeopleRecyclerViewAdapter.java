package com.spark.cong.growrelationship.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.spark.cong.growrelationship.Activity.PeopleActivity;
import com.spark.cong.growrelationship.Architecture.Entity.GroupPeole;
import com.spark.cong.growrelationship.R;

import java.util.List;

public class GroupPeopleRecyclerViewAdapter extends RecyclerView.Adapter<GroupPeopleRecyclerViewAdapter.GpeopleViewHolder> {

    private List<GroupPeole> lstGroupPeople;

    @NonNull
    @Override
    public GpeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_people, parent, false);
        return new GpeopleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final GpeopleViewHolder holder, int position) {
        if (lstGroupPeople != null) {
            GroupPeole groupPeole = lstGroupPeople.get(position);
            final String nameOfGroupPeople = groupPeole.getName();
            holder.txt_g_name.setText(nameOfGroupPeople);

            holder.cardView_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(holder.itemView.getContext(), "You select " + nameOfGroupPeople, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(holder.itemView.getContext(), PeopleActivity.class);
                    holder.itemView.getContext().startActivity(intent);

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (lstGroupPeople != null) {
            return lstGroupPeople.size();
        }
        return 0;
    }

    //setData
    public void setData(List<GroupPeole> groupPeoles) {
        this.lstGroupPeople = groupPeoles;
        notifyDataSetChanged();
    }

    //viewHolder
    public class GpeopleViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_g_name;
        private CardView cardView_name;

        public GpeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_g_name = (TextView) itemView.findViewById(R.id.txt_g_name);
            cardView_name = (CardView) itemView.findViewById(R.id.cardView_g_name);
        }
    }
}
