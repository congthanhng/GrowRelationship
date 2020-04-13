package com.spark.cong.growrelationship.Adapter;

import android.content.Context;
import android.util.Log;
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
    private List<GroupPeople> lstGroupPeople;
    private Context context;
    public GroupPeopleRecyclerAdapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public GroupPeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_people,parent,false);
        return new GroupPeopleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupPeopleViewHolder holder, int position) {
        int text = lstGroupPeople.get(position).getGroupId();
        Log.i("TEST", "onBindViewHolder: group name "+ text);
        holder.txtGPName.setText(text+"");
    }

    @Override
    public int getItemCount() {
        if(lstGroupPeople != null){
            return lstGroupPeople.size();
        }
        return 0;
    }

    public void setData(List<GroupPeople> listGroupPeople){
        this.lstGroupPeople = listGroupPeople;
        notifyDataSetChanged();
    }
    //ViewHolder
    public class GroupPeopleViewHolder extends RecyclerView.ViewHolder{

        private TextView txtGPName;
        public GroupPeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            txtGPName = (TextView) itemView.findViewById(R.id.txt_gp_name);
        }
    }
}
