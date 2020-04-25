package com.spark.cong.growrelationship.View.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.spark.cong.growrelationship.Architecture.Entity.GroupPeople;
import com.spark.cong.growrelationship.Architecture.Entity.People;
import com.spark.cong.growrelationship.Commons.ItemClickListener;
import com.spark.cong.growrelationship.Commons.ItemLongClickListener;
import com.spark.cong.growrelationship.R;

import java.util.List;

public class GroupPeopleRecyclerAdapter extends RecyclerView.Adapter<GroupPeopleRecyclerAdapter.GroupPeopleViewHolder>  {
    private List<People> lstPeopleOfGroup;
    private Context context;
    public GroupPeopleRecyclerAdapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public GroupPeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_people,parent,false);
        return new GroupPeopleViewHolder(v,parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull GroupPeopleViewHolder holder, int position) {
        String text = lstPeopleOfGroup.get(position).getPeopleName();
        holder.txtGPName.setText(text+"");
    }

    @Override
    public int getItemCount() {
        if(lstPeopleOfGroup != null){
            return lstPeopleOfGroup.size();
        }
        return 0;
    }

    public void setData(List<People> listPeopleOfGroup){
        this.lstPeopleOfGroup = listPeopleOfGroup;
        notifyDataSetChanged();
    }
    //ViewHolder
    public class GroupPeopleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {

        private TextView txtGPName;
        private ItemClickListener clickListener;
        private ItemLongClickListener longClickListener;
        public GroupPeopleViewHolder(@NonNull View itemView,Context context) {
            super(itemView);
            txtGPName = (TextView) itemView.findViewById(R.id.txt_gp_name);
            clickListener = (ItemClickListener) context;
            longClickListener = (ItemLongClickListener)context;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v,getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            longClickListener.onItemLongClick(v,getAdapterPosition());
            return true;
        }

    }
}
