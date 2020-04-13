package com.spark.cong.growrelationship.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spark.cong.growrelationship.Adapter.GroupPeopleRecyclerAdapter;
import com.spark.cong.growrelationship.Architecture.Entity.GroupPeople;
import com.spark.cong.growrelationship.Architecture.ViewModel.GroupPeopleViewModel;
import com.spark.cong.growrelationship.Commons.ItemSpacingDecorator;
import com.spark.cong.growrelationship.R;

import java.util.ArrayList;
import java.util.List;

import static com.spark.cong.growrelationship.Commons.Constant.BUNDLE_MAIN_TO_PEOPLE;
import static com.spark.cong.growrelationship.Commons.Constant.INTENT_MAIN_TO_PEOPLE;
import static com.spark.cong.growrelationship.Commons.Constant.ITEM_SPACING;

public class GroupPeopleActivity extends AppCompatActivity implements View.OnClickListener{

    private int groupId;
    private List<GroupPeople> lstGroupPeople;
    private GroupPeopleViewModel groupPeopleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_people);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(INTENT_MAIN_TO_PEOPLE);
        groupId = bundle.getInt(BUNDLE_MAIN_TO_PEOPLE);

        //set and map View
        mapView();
    }
    public void mapView(){
        //set action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //RecyclerView
        RecyclerView groupPeopleRecycler = (RecyclerView) findViewById(R.id.recyclerView_GroupPeople);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        groupPeopleRecycler.setLayoutManager(layoutManager);
        final GroupPeopleRecyclerAdapter adapter = new GroupPeopleRecyclerAdapter(this);
        groupPeopleRecycler.setAdapter(adapter);
//        adapter.setData(listGroupPeopleFake());
        groupPeopleRecycler.addItemDecoration(new ItemSpacingDecorator(ITEM_SPACING,1));

        //button
        Button btnAdd = (Button) findViewById(R.id.button_test);
        btnAdd.setOnClickListener(this);

        //ViewModel
        groupPeopleViewModel = new ViewModelProvider(this).get(GroupPeopleViewModel.class);
        //observe data
        groupPeopleViewModel.getAllGroupPeopleByGroupId(groupId).observe(this, new Observer<List<GroupPeople>>() {
            @Override
            public void onChanged(List<GroupPeople> groupPeople) {
                adapter.setData(groupPeople);
                lstGroupPeople = groupPeople;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public List<GroupPeople> listGroupPeopleFake(){
        List<GroupPeople> lstPeoPle = new ArrayList<>();
        lstPeoPle.add(new GroupPeople(1,1));
        lstPeoPle.add(new GroupPeople(2,2));
        lstPeoPle.add(new GroupPeople(3,1));
        lstPeoPle.add(new GroupPeople(4,1));
        lstPeoPle.add(new GroupPeople(5,1));
        return lstPeoPle;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_test :{
                GroupPeople  groupPeople= new GroupPeople(this.groupId, 6);
                groupPeopleViewModel.insertGroupPeople(groupPeople);
            }break;
        }
    }
}
