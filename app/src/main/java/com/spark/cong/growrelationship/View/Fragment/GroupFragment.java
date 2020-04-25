package com.spark.cong.growrelationship.View.Fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.spark.cong.growrelationship.Architecture.Entity.Group;
import com.spark.cong.growrelationship.Architecture.ViewModel.GroupViewModel;
import com.spark.cong.growrelationship.Commons.ItemClickListener;
import com.spark.cong.growrelationship.Commons.ItemLongClickListener;
import com.spark.cong.growrelationship.Commons.ItemSpacingDecorator;
import com.spark.cong.growrelationship.R;
import com.spark.cong.growrelationship.View.Activity.GroupPeopleActivity;
import com.spark.cong.growrelationship.View.Adapter.GroupRecyclerAdapter;
import com.spark.cong.growrelationship.View.Dialog.AddGroupDialog;

import java.util.List;

import static com.spark.cong.growrelationship.Commons.Constant.INTENT_TO_GROUP_PEOPLE;
import static com.spark.cong.growrelationship.Commons.Constant.ITEM_SPACING;
import static com.spark.cong.growrelationship.Commons.Constant.REQUEST_CODE_GROUP_PEOPLE;
import static com.spark.cong.growrelationship.Commons.Constant.SPAN_COUNT;

public class GroupFragment extends Fragment implements ItemClickListener, ItemLongClickListener,View.OnClickListener, AddGroupDialog.EditNameGroupListener {
    private GroupViewModel mViewModel;
    private RecyclerView recyclerView;
    private List<Group> listGroup;
    private GroupRecyclerAdapter adapter;
    private FragmentActivity myContext;

    public static GroupFragment newInstance() {
        GroupFragment fragment = new GroupFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_group, container, false);
        //recyclerView
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerViewGroup);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(),SPAN_COUNT));
        adapter = new GroupRecyclerAdapter(view.getContext(),this,this);
        recyclerView.setAdapter(adapter);
        recyclerView.hasFixedSize();
        recyclerView.addItemDecoration(new ItemSpacingDecorator(ITEM_SPACING, SPAN_COUNT)); // spacing between items

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(GroupViewModel.class);
        //LiveData
        mViewModel.getAllGroup().observe(getViewLifecycleOwner(), new Observer<List<Group>>() {
            @Override
            public void onChanged(List<Group> groups) {
                if(groups!=null){
                    adapter.setData(groups);
                    listGroup = groups;
                }else{
                    Toast.makeText(getContext(),"Please create a new group",Toast.LENGTH_SHORT).show();
                }

            }
        });
        // TODO: Use the ViewModel
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myContext = getActivity();
    }

    @Override
    public void onClick(View v) {
    }

    /*
    listener when click Save button in AddGroupDialog
    * */
    @Override
    public void onFinishEditDialog(String inputText) {
        Group group = new Group(inputText);
        mViewModel.insertGroup(group);
    }

    //item click listener
    @Override
    public void onItemClick(View view, int position) {
        int groupId = listGroup.get(position).getGroupId();
//        Toast.makeText(myContext,"itemClick"+position,Toast.LENGTH_SHORT).show();
        if(groupId >= 0){
            Intent intent = new Intent(getActivity(), GroupPeopleActivity.class);
            intent.putExtra(INTENT_TO_GROUP_PEOPLE,groupId);
            startActivityForResult(intent,REQUEST_CODE_GROUP_PEOPLE);
        }
    }

    //item long click listener
    @Override
    public void onItemLongClick(View view, int position) {
        Toast.makeText(myContext,"itemLongClick"+position,Toast.LENGTH_SHORT).show();
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
        bottomSheetFragment.show(myContext.getSupportFragmentManager(),"group_bottom_sheet");

    }

    //show dialog addgroup when click fab
    public void addNewGroup(){
        AddGroupDialog addGroupDialog = new AddGroupDialog(this);
        addGroupDialog.show(myContext.getSupportFragmentManager(), "group");
    }

}
