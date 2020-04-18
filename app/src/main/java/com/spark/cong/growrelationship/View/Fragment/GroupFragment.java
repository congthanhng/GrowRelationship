package com.spark.cong.growrelationship.View.Fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spark.cong.growrelationship.Architecture.ViewModel.GroupViewModel;
import com.spark.cong.growrelationship.Commons.GroupItemClickListener;
import com.spark.cong.growrelationship.R;
import com.spark.cong.growrelationship.View.Adapter.GroupRecyclerAdapter;

public class GroupFragment extends Fragment implements GroupItemClickListener {

    private GroupViewModel mViewModel;
    private RecyclerView recyclerView;

    public static GroupFragment newInstance() {
        return new GroupFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_group, container, false);

        //recyclerView
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerViewGroup);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(),2));
        GroupRecyclerAdapter adapter = new GroupRecyclerAdapter(view.getContext(),this);
        recyclerView.setAdapter(adapter);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(GroupViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View view, int position) {

    }

    @Override
    public void onLongClick(View view, int position) {

    }
}
