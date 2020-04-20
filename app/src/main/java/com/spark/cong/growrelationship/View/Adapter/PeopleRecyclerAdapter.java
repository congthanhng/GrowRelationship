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
import com.spark.cong.growrelationship.R;

import java.util.List;

public class PeopleRecyclerAdapter extends RecyclerView.Adapter<PeopleRecyclerAdapter.PeopleViewHolder> {
    private List<People> mPeoples;
    private Context context;
    public PeopleRecyclerAdapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_people,parent,false);
        return new PeopleViewHolder(v);
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
    public class PeopleViewHolder extends RecyclerView.ViewHolder{
        private TextView txtPeopleName;
        public PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPeopleName = (TextView) itemView.findViewById(R.id.txt_gp_name);
        }
    }
}
