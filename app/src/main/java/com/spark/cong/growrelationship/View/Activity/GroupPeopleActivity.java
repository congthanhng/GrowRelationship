package com.spark.cong.growrelationship.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spark.cong.growrelationship.Architecture.Entity.Group;
import com.spark.cong.growrelationship.Architecture.Entity.GroupPeople;
import com.spark.cong.growrelationship.Architecture.Entity.People;
import com.spark.cong.growrelationship.Architecture.ViewModel.GroupPeopleViewModel;
import com.spark.cong.growrelationship.Architecture.ViewModel.GroupViewModel;
import com.spark.cong.growrelationship.Architecture.ViewModel.PeopleViewModel;
import com.spark.cong.growrelationship.Commons.CallView;
import com.spark.cong.growrelationship.Commons.ItemClickListener;
import com.spark.cong.growrelationship.Commons.ItemLongClickListener;
import com.spark.cong.growrelationship.Commons.ItemSpacingDecorator;
import com.spark.cong.growrelationship.Commons.impl.CallViewImpl;
import com.spark.cong.growrelationship.R;
import com.spark.cong.growrelationship.View.Adapter.GroupPeopleRecyclerAdapter;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

import static com.spark.cong.growrelationship.Commons.Constant.INTENT_SELECT_PEOPLE;
import static com.spark.cong.growrelationship.Commons.Constant.INTENT_TO_GROUP_PEOPLE;
import static com.spark.cong.growrelationship.Commons.Constant.INTENT_TO_PEOPLE;
import static com.spark.cong.growrelationship.Commons.Constant.ITEM_SPACING;
import static com.spark.cong.growrelationship.Commons.Constant.REQUEST_CODE_PEOPLE_TO_GROUPPEOPLE;
import static com.spark.cong.growrelationship.Commons.Constant.REQUEST_CODE_SELECT_PEOPLE;
import static com.spark.cong.growrelationship.Commons.Constant.TAG_ITEM_PEOPLE_OF_GROUP;
import static com.spark.cong.growrelationship.Commons.ErrorMessage.NOT_FOUND_INTENT;
import static com.spark.cong.growrelationship.Commons.ErrorMessage.NOT_FOUND_PARAMETER;


public class GroupPeopleActivity extends AppCompatActivity implements View.OnClickListener, ItemClickListener, ItemLongClickListener {

    /*------------------------------------global common-----------------------------*/
    //call View
    CallView callView = CallViewImpl.getInstance();

    //parameter from GroupActivity
    private int mGroupId;

    //parameter
    private List<People> lstPeopleOfGroup=new ArrayList<>();
    private List<GroupPeople> lstGroupPeople;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    //ViewModel
    private GroupPeopleViewModel mViewModel;
    private GroupViewModel mGroupViewModel;
    private PeopleViewModel mPeopleViewModel;

    //View
    private TextView txtTitle;
    private ImageButton btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_people);

        //get data from GroupActivity, throw if not found
        if (getIntent() != null) {
            mGroupId = getIntent().getIntExtra(INTENT_TO_GROUP_PEOPLE, -1);
            if (mGroupId < 0) {
                throw new RuntimeException(NOT_FOUND_PARAMETER);
            }
        } else {
            throw new RuntimeException(NOT_FOUND_INTENT);
        }

        //set and map View
        setView();
        setListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    public void setView() {
        //set action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_GroupPeople);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final GroupPeopleRecyclerAdapter adapter = new GroupPeopleRecyclerAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new ItemSpacingDecorator(ITEM_SPACING, 1));

        //imagebutton
        btnAdd = findViewById(R.id.button_add);

        //TextView
        txtTitle = findViewById(R.id.title_group);

        //ViewModel
        mViewModel = new ViewModelProvider(this).get(GroupPeopleViewModel.class);
        mGroupViewModel = new ViewModelProvider(this).get(GroupViewModel.class);
        mPeopleViewModel = new ViewModelProvider(this).get(PeopleViewModel.class);

        //observe data
        mViewModel.getAllGroupPeopleByGroupId(mGroupId).observe(this, new Observer<List<GroupPeople>>() {
            @Override
            public void onChanged(List<GroupPeople> groupPeople) {
//                adapter.setData(groupPeople);
                lstGroupPeople = groupPeople;
                lstPeopleOfGroup.clear();
                for (int i=0; i<groupPeople.size();i++){
                    lstPeopleOfGroup.add(mPeopleViewModel.getPeopleById(groupPeople.get(i).getPeopleId()));
                }
                if(!lstPeopleOfGroup.isEmpty()){
                    adapter.setData(lstPeopleOfGroup);
                }
            }
        });

        //set title of list group
            compositeDisposable.add(mGroupViewModel.getGroupById(mGroupId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableSubscriber<Group>() {
                @Override
                public void onNext(Group group) {
                    txtTitle.setText(group.getGroupName());
                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onComplete() {

                }
            }));
//            Group group = mGroupViewModel.getGroupById(mGroupId);
//            txtTitle.setText(group.getGroupName().toString());


    }

    public void setListener() {
        btnAdd.setOnClickListener(this);
    }

    /**
     * listener when click back home
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add: {
                callSelectPeopleActivity();
            }
            break;
        }
    }
    //item click from Adapter of bottomSheet
    @Override
    public void onItemClick(View view, int position) {
        Log.i("tset", "onItemClick:sdfdsfd ");
        switch (view.getId()){
            case R.id.action_open:{
                Toast.makeText(getApplicationContext(),"open"+position,Toast.LENGTH_SHORT).show();
                callPeopleActivity(position);
            }break;
            case R.id.action_delete:{
                Toast.makeText(getApplicationContext(),"detele"+position,Toast.LENGTH_SHORT).show();
                mViewModel.deleteGroupPeople(lstGroupPeople.get(position));
            }break;
            default:{
                callPeopleActivity(position);
            }
        }

    }

    @Override
    public void onItemLongClick(View view, int position) {
        //show bottom sheet
        callView.callBottomSheet(getSupportFragmentManager(),TAG_ITEM_PEOPLE_OF_GROUP,position);
    }

    public void callPeopleActivity(int position){
        String text = lstPeopleOfGroup.get(position).getPeopleName();
        Intent intent = new Intent(GroupPeopleActivity.this,PeopleActivity.class);
        intent.putExtra(INTENT_TO_PEOPLE,lstPeopleOfGroup.get(position).getPeopleId());
        startActivityForResult(intent,REQUEST_CODE_PEOPLE_TO_GROUPPEOPLE);
    }
    public void callSelectPeopleActivity(){
        Intent intent = new Intent(GroupPeopleActivity.this, SelectPeoplesActivity.class);
        intent.putExtra(INTENT_SELECT_PEOPLE, mGroupId);
        startActivityForResult(intent, REQUEST_CODE_SELECT_PEOPLE);
    }

}
