package com.spark.cong.growrelationship.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.spark.cong.growrelationship.Architecture.Entity.People;
import com.spark.cong.growrelationship.Architecture.ViewModel.GroupPeopleViewModel;
import com.spark.cong.growrelationship.Architecture.ViewModel.PeopleViewModel;
import com.spark.cong.growrelationship.Commons.ItemSpacingDecorator;
import com.spark.cong.growrelationship.R;
import com.spark.cong.growrelationship.View.Adapter.SelectPeopleRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.spark.cong.growrelationship.Commons.Constant.INTENT_SELECT_PEOPLE;
import static com.spark.cong.growrelationship.Commons.Constant.ITEM_SPACING;

public class SelectPeoplesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private int mGroupId;
    private GroupPeopleViewModel mViewModel;
    private SelectPeopleRecyclerAdapter adapter;
    private PeopleViewModel mPeopleViewModel;
    private int[] lstPeopleId;
    private List<People> lstPeopleSelect = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_peoples);

        //get data from GroupPeopleActivity
        if(getIntent()!=null){
            mGroupId = getIntent().getIntExtra(INTENT_SELECT_PEOPLE,-1);
        }

        setView();
        setListener();
    }

    public void setView(){
        //ViewModel
        mViewModel = new ViewModelProvider(this).get(GroupPeopleViewModel.class);
        mPeopleViewModel = new ViewModelProvider(this).get(PeopleViewModel.class);

        //recyclerView
        recyclerView = (RecyclerView)findViewById(R.id.recycler_select_people);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SelectPeopleRecyclerAdapter();
        recyclerView.addItemDecoration(new ItemSpacingDecorator(ITEM_SPACING,1));
        recyclerView.setAdapter(adapter);
        mViewModel.getAllPeopleIdByWithoutGroupId(mGroupId).observe(this, new Observer<int[]>() {
            @Override
            public void onChanged(int[] ints) {
                lstPeopleId = ints;
                if(lstPeopleId != null){
                    lstPeopleSelect.clear();
                    for(int item:lstPeopleId){
                        lstPeopleSelect.add(mPeopleViewModel.getPeopleById(item));
                    }
                }
                if(lstPeopleSelect!=null){
                    adapter.setData(lstPeopleSelect);
                }
            }
        });

//        if(mGroupId>=0){
//            lstPeopleId = mViewModel.getAllPeopleIdByWithoutGroupId(mGroupId);
//            if(lstPeopleId != null){
//                for(int item:lstPeopleId){
//                    lstPeopleSelect.add(mPeopleViewModel.getPeopleById(item));
//                }
//            }
//            if(lstPeopleSelect!=null){
//                adapter.setData(lstPeopleSelect);
//            }
//
////            mViewModel.ge
//        }

    }

    public void setListener(){

    }
}
