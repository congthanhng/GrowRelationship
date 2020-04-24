package com.spark.cong.growrelationship.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spark.cong.growrelationship.Architecture.Entity.Group;
import com.spark.cong.growrelationship.Architecture.ViewModel.GroupViewModel;
import com.spark.cong.growrelationship.View.Adapter.GroupPeopleRecyclerAdapter;
import com.spark.cong.growrelationship.Architecture.Entity.GroupPeople;
import com.spark.cong.growrelationship.Architecture.ViewModel.GroupPeopleViewModel;
import com.spark.cong.growrelationship.Commons.ItemSpacingDecorator;
import com.spark.cong.growrelationship.R;

import java.util.ArrayList;
import java.util.List;

import static com.spark.cong.growrelationship.Commons.Constant.*;


public class GroupPeopleActivity extends AppCompatActivity implements View.OnClickListener{

    private int mGroupId;
    private List<GroupPeople> lstGroupPeople;
    private GroupPeopleViewModel mViewModel;
    private GroupViewModel mGroupViewModel;
    private TextView txtTitle;
    private ImageButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_people);

        if(getIntent()!=null){
            mGroupId = getIntent().getIntExtra(INTENT_MAIN_TO_GROUP_PEOPLE,-1);
        }


        //set and map View
        setView();
        setListener();
    }
    public void setView(){
        //set action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_GroupPeople);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final GroupPeopleRecyclerAdapter adapter = new GroupPeopleRecyclerAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new ItemSpacingDecorator(ITEM_SPACING,1));

        //imagebutton
        btnAdd = (ImageButton) findViewById(R.id.button_add);

        //TextView
        txtTitle = (TextView) findViewById(R.id.title_group);

        //ViewModel
        mViewModel = new ViewModelProvider(this).get(GroupPeopleViewModel.class);
        mGroupViewModel = new ViewModelProvider(this).get(GroupViewModel.class);

        //observe data
        if(mGroupId >= 0){
            mViewModel.getAllGroupPeopleByGroupId(mGroupId).observe(this, new Observer<List<GroupPeople>>() {
                @Override
                public void onChanged(List<GroupPeople> groupPeople) {
                    adapter.setData(groupPeople);
                    lstGroupPeople = groupPeople;
                }
            });
        }

        //set title of list group
        try {
            Group group = mGroupViewModel.getGroupById(mGroupId);
            txtTitle.setText(group.getGroupName().toString());
        }catch (Exception e){

        }

    }

    public void setListener(){
        btnAdd.setOnClickListener(this);
    }

    /**
     * listener when click back home
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //mock data
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
            case R.id.button_add :{
                Intent intent = new Intent(GroupPeopleActivity.this,SelectPeoplesActivity.class);
                intent.putExtra(INTENT_SELECT_PEOPLE,mGroupId);
                startActivityForResult(intent,REQUEST_CODE_SELECT_PEOPLE);
            }break;
        }
    }
}
