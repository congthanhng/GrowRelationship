package com.spark.cong.growrelationship.View.Adapter;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.spark.cong.growrelationship.Architecture.Entity.People;
import com.spark.cong.growrelationship.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SelectPeopleRecyclerAdapter extends RecyclerView.Adapter<SelectPeopleRecyclerAdapter.SelectPeopleViewHolder> {
    private List<People> mListPeople;
    private SparseBooleanArray itemCheckArray = new SparseBooleanArray();// map with key,value is boolean

    @NonNull
    @Override
    public SelectPeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_people, parent, false);
        return new SelectPeopleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectPeopleViewHolder holder, int position) {
        holder.txtSelectPeople.setText(mListPeople.get(position).getPeopleName());
        holder.checkBoxItem.setChecked(itemCheckArray.get(position, false));
    }

    @Override
    public int getItemCount() {
        if (mListPeople != null) {
            return mListPeople.size();
        } else {
            return 0;
        }
    }

    //set Data
    public void setData(List<People> lstPeople) {
        Log.i("sdfsf", "setData: " + lstPeople.size());
        if (lstPeople != null) {
            mListPeople = lstPeople;
            notifyDataSetChanged();
        }

    }

    //set state all checkBox when checkBoxAll change
    public void setStateCheckBox(boolean stateChecked) {
        itemCheckArray.clear();
        for (int i = 0; i < mListPeople.size(); i++) {
            itemCheckArray.put(i, stateChecked);
        }
        notifyDataSetChanged();
    }
    //get array item check
    public List<Integer> getCheckArray(){
        List<Integer> lstChecked = new ArrayList<>();
        if (mListPeople!= null){
            for (int i=0;i< mListPeople.size();i++){
                if(itemCheckArray.get(i,false)){
                    lstChecked.add(mListPeople.get(i).getPeopleId());
                }
            }
        }
        return lstChecked;
    }

    //ViewHolder
    public class SelectPeopleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CheckBox checkBoxItem;
        private TextView txtSelectPeople;

        public SelectPeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBoxItem = (CheckBox) itemView.findViewById(R.id.checkbox_people);
            txtSelectPeople = (TextView) itemView.findViewById(R.id.txt_select_name);
            itemView.setOnClickListener(this);
            checkBoxItem.setOnClickListener(this);
        }

        //set state of checkbox when item isClicked and put them to mapArray
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (!itemCheckArray.get(position, false)) {
                checkBoxItem.setChecked(true);
                itemCheckArray.put(position, true);
            } else {
                checkBoxItem.setChecked(false);
                itemCheckArray.put(position, false);
            }
        }


    }


}
