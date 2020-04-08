package com.spark.cong.growrelationship.Adapter;

import android.app.Activity;
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

import static com.spark.cong.growrelationship.Commons.Constant.REQUEST_CODE_PEOPLE;

public class GroupPeopleRecyclerAdapter extends RecyclerView.Adapter<GroupPeopleRecyclerAdapter.GpeopleViewHolder> {

    private List<GroupPeole> lstGroupPeople;
    private Context context;

    public GroupPeopleRecyclerAdapter(Context context) {
        this.context = context;
    }

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

            //item onClick listener
            holder.cardView_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(holder.itemView.getContext(), "You select " + nameOfGroupPeople, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, PeopleActivity.class);
                    ((Activity) context).startActivityForResult(intent, REQUEST_CODE_PEOPLE); //cast context to activity type to use startActivityForResult()
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
