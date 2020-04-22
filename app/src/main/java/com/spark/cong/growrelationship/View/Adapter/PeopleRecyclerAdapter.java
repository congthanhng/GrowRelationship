package com.spark.cong.growrelationship.View.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.spark.cong.growrelationship.Architecture.Entity.People;
import com.spark.cong.growrelationship.Commons.ItemClickListener;
import com.spark.cong.growrelationship.Commons.ItemLongClickListener;
import com.spark.cong.growrelationship.R;

import java.util.List;

public class PeopleRecyclerAdapter extends RecyclerView.Adapter<PeopleRecyclerAdapter.PeopleViewHolder> {
    private List<People> mPeoples;
    private Context context;
    private ItemClickListener itemClickListener;
    private ItemLongClickListener itemLongClickListener;
    public PeopleRecyclerAdapter(Context context, ItemClickListener listener, ItemLongClickListener longListener){
        this.context = context;
        this.itemClickListener = listener;
        this.itemLongClickListener = longListener;
    }
    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_people,parent,false);
        return new PeopleViewHolder(v,itemClickListener,itemLongClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder holder, int position) {
        if(mPeoples!=null){
            holder.txtPeopleName.setText(mPeoples.get(position).getPeopleName().toString());
        }

    }

    @Override
    public int getItemCount() {
        if(mPeoples != null){
            return mPeoples.size();
        }
        return 0;
    }

    //setData
    public void setData(List<People> lstPeople){
        Log.i("sdfsf", "setData: sdfafaf");
        if(lstPeople!=null){
            this.mPeoples = lstPeople;
            notifyDataSetChanged();
        }
    }

    //view holder
    public class PeopleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        private TextView txtPeopleName;
        private ItemClickListener itemClick;
        private ItemLongClickListener itemLongClick;
        public PeopleViewHolder(@NonNull View itemView, ItemClickListener itemClickListener, ItemLongClickListener itemLongClickListener) {
            super(itemView);
            txtPeopleName = (TextView) itemView.findViewById(R.id.txt_gp_name);
            this.itemClick = itemClickListener;
            this.itemLongClick = itemLongClickListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClick.onItemClick(v,getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            itemLongClick.onItemLongClick(v,getAdapterPosition());
            return true;
        }


    }
}
