package com.spark.cong.growrelationship.View.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.spark.cong.growrelationship.Architecture.Entity.GroupPeople;
import com.spark.cong.growrelationship.Architecture.Entity.People;
import com.spark.cong.growrelationship.R;

import java.util.List;

public class SelectPeopleRecyclerAdapter  extends RecyclerView.Adapter<SelectPeopleRecyclerAdapter.SelectPeopleViewHolder>{
    private List<People> mList;

    @NonNull
    @Override
    public SelectPeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_people,parent,false);
        return new SelectPeopleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectPeopleViewHolder holder, int position) {
        holder.txtSelectPeople.setText(mList.get(position).getPeopleName());
    }

    @Override
    public int getItemCount() {
        if(mList!= null) {
            return mList.size();
        }else{
            return 0;
        }
    }

    public class SelectPeopleViewHolder extends RecyclerView.ViewHolder{
        private CheckBox selected;
        private TextView txtSelectPeople;
        public SelectPeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            selected = (CheckBox) itemView.findViewById(R.id.checkbox_people);
            txtSelectPeople = (TextView) itemView.findViewById(R.id.txt_select_name);
        }
    }

    public void setData(List<People> lstPeople){
        Log.i("sdfsf", "setData: "+lstPeople.size());
        if(lstPeople!=null){
            mList = lstPeople;
            notifyDataSetChanged();
        }

    }
}
