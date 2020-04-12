package com.spark.cong.growrelationship.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.spark.cong.growrelationship.Architecture.Entity.GroupPeople;
import com.spark.cong.growrelationship.R;

import java.util.List;

public class GroupPeopleRecyclerAdapter extends RecyclerView.Adapter<GroupPeopleRecyclerAdapter.GroupPeopleViewHolder>  {
    private List<GroupPeople> lstGroupPeoplel;
    private Context context;
    @NonNull
    @Override
    public GroupPeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_people,parent,false);
        return new GroupPeopleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupPeopleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if(lstGroupPeoplel.size()!= 0 && lstGroupPeoplel != null){
            return lstGroupPeoplel.size();
        }
        return 0;
    }

    //ViewHolder
    public class GroupPeopleViewHolder extends RecyclerView.ViewHolder{

        private TextView txtName;
        public GroupPeopleViewHolder(@NonNull View itemView) {
            super(itemView);
//            txtName = (TextView) itemView.findViewById(R.id.txt_group_people_name);
        }
    }
}
