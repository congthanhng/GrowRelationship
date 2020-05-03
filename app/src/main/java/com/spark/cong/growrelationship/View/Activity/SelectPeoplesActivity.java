package com.spark.cong.growrelationship.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import com.spark.cong.growrelationship.Architecture.Entity.GroupPeople;
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

public class SelectPeoplesActivity extends AppCompatActivity implements View.OnClickListener{

    //data from previous activity
    private int mGroupId;

    //ViewModel
    private PeopleViewModel mPeopleViewModel;
    private GroupPeopleViewModel mViewModel;

    //argument
    private List<People> lstPeopleSelect = new ArrayList<>();
    private int[] lstPeopleId;

    //view
    private CheckBox checkBoxAll;
    private SelectPeopleRecyclerAdapter adapter;

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
        //back home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_36dp);

        //ViewModel
        mViewModel = new ViewModelProvider(this).get(GroupPeopleViewModel.class);
        mPeopleViewModel = new ViewModelProvider(this).get(PeopleViewModel.class);

        //recyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_select_people);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SelectPeopleRecyclerAdapter();
        recyclerView.addItemDecoration(new ItemSpacingDecorator(ITEM_SPACING,1));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        //get list people to set list item of recycler
        mViewModel.getAllPeopleIsNotGroupId(mGroupId).observe(this, new Observer<List<People>>() {
            @Override
            public void onChanged(List<People> peoples) {
                adapter.setData(peoples);
                lstPeopleSelect = peoples;
            }
        });
        //checkBox
        checkBoxAll = findViewById(R.id.checkbox_select_people);
    }

    public void setListener(){
        checkBoxAll.setOnClickListener(this);

    }

    //create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.confirm_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    //menu listener
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_selected:{
                List<Integer> lst = adapter.getCheckArray();
                if(!lst.isEmpty()){
                    for (int i =0; i<lst.size();i++){
                        mViewModel.insertGroupPeople(new GroupPeople(mGroupId,lst.get(i)));
                    }
                }
                finish();
            }break;
            case android.R.id.home:{
                finish();
            }break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.checkbox_select_people:{
                adapter.setStateCheckBox(checkBoxAll.isChecked());
            }break;
        }
    }
}
