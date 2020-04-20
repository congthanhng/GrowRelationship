package com.spark.cong.growrelationship.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.spark.cong.growrelationship.Architecture.Entity.Group;
import com.spark.cong.growrelationship.Commons.GroupItemClickListener;
import com.spark.cong.growrelationship.Commons.GroupItemLongClickListener;
import com.spark.cong.growrelationship.R;

import java.util.List;

public class GroupRecyclerAdapter extends RecyclerView.Adapter<GroupRecyclerAdapter.GroupViewHolder> {

    private List<Group> lstGroupPeople;
    private Context context;
    private GroupItemClickListener itemClickListener;
    private GroupItemLongClickListener itemLongClickListener;

    public GroupRecyclerAdapter(Context context, GroupItemClickListener listener,GroupItemLongClickListener longListener) {
        this.context = context;
        this.itemClickListener = listener;
        this.itemLongClickListener = longListener;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group, parent, false);
        return new GroupViewHolder(v, itemClickListener,itemLongClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final GroupViewHolder holder, int position) {
        if (lstGroupPeople != null) {
            Group group = lstGroupPeople.get(position);
            final String nameOfGroupPeople = group.getGroupName();
            holder.txt_g_name.setText(nameOfGroupPeople);

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
    public void setData(List<Group> groups) {
        if (groups!=null){
            this.lstGroupPeople = groups;
            notifyDataSetChanged();
        }
    }

    //viewHolder
    public class GroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        private TextView txt_g_name;
        private GroupItemClickListener itemClick;
        private GroupItemLongClickListener itemLongClick;
//        private CardView cardView_name;

        public GroupViewHolder(@NonNull View itemView, GroupItemClickListener itemClick, GroupItemLongClickListener itemLongClick) {
            super(itemView);
            txt_g_name = (TextView) itemView.findViewById(R.id.txt_g_name);
            this.itemClick = itemClick;
            this.itemLongClick = itemLongClick;

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
//            cardView_name = (CardView) itemView.findViewById(R.id.cardView_g_name);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                default: {
                    itemClick.onItemClick(v,getAdapterPosition());
                }
            }
//            listener.onClick(v,getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            itemLongClick.onItemLongClick(v,getAdapterPosition());
            return true;
        }
    }

}
