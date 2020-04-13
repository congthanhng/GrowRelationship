package com.spark.cong.growrelationship.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.spark.cong.growrelationship.Architecture.Entity.Group;
import com.spark.cong.growrelationship.Commons.ItemClickListener;
import com.spark.cong.growrelationship.R;

import java.util.List;

public class GroupRecyclerAdapter extends RecyclerView.Adapter<GroupRecyclerAdapter.GpeopleViewHolder> {

    private List<Group> lstGroupPeople;
    private Context context;
    private ItemClickListener mListener;

    public GroupRecyclerAdapter(Context context, ItemClickListener listener) {
        this.context = context;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public GpeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group, parent, false);
        return new GpeopleViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final GpeopleViewHolder holder, int position) {
        if (lstGroupPeople != null) {
            Group group = lstGroupPeople.get(position);
            final String nameOfGroupPeople = group.getGroupName();
            holder.txt_g_name.setText(nameOfGroupPeople);
            holder.btnEditGroup.setVisibility(View.GONE);
            holder.btnDeleteGroup.setVisibility(View.GONE);

            //item onClick listener
            /*holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(holder.itemView.getContext(), "You select " + nameOfGroupPeople, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, PeopleActivity.class);
                    ((Activity) context).startActivityForResult(intent, REQUEST_CODE_PEOPLE); //cast context to activity type to use startActivityForResult()
                }
            });*/
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
        this.lstGroupPeople = groups;
        notifyDataSetChanged();
    }

    //viewHolder
    public class GpeopleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        private TextView txt_g_name;
        private ItemClickListener listener;
        private ImageButton btnDeleteGroup,btnEditGroup;
//        private CardView cardView_name;

        public GpeopleViewHolder(@NonNull View itemView, ItemClickListener listeners) {
            super(itemView);
            txt_g_name = (TextView) itemView.findViewById(R.id.txt_g_name);
            btnDeleteGroup = (ImageButton) itemView.findViewById(R.id.btn_delete_group);
            btnEditGroup = (ImageButton) itemView.findViewById(R.id.btn_edit_group);
            this.listener = listeners;

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            btnEditGroup.setOnClickListener(this);
            btnDeleteGroup.setOnClickListener(this);
//            cardView_name = (CardView) itemView.findViewById(R.id.cardView_g_name);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_delete_group : {
                    btnDeleteGroup.setVisibility(View.GONE);
                    btnEditGroup.setVisibility(View.GONE);
                }
                case R.id.btn_edit_group:{
                    btnDeleteGroup.setVisibility(View.GONE);
                    btnEditGroup.setVisibility(View.GONE);
                    listener.onClick(v,getAdapterPosition());
                }break;
                default: {
                    listener.onClick(v,getAdapterPosition());
                }
            }
//            listener.onClick(v,getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            listener.onLongClick(v,getAdapterPosition());
            btnDeleteGroup.setVisibility(View.VISIBLE);
            btnEditGroup.setVisibility(View.VISIBLE);
            return true;
        }
    }

}
