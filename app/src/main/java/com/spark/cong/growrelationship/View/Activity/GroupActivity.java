package com.spark.cong.growrelationship.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.spark.cong.growrelationship.Architecture.Entity.Group;
import com.spark.cong.growrelationship.Architecture.Entity.GroupPeople;
import com.spark.cong.growrelationship.Architecture.ViewModel.GroupPeopleViewModel;
import com.spark.cong.growrelationship.Architecture.ViewModel.GroupViewModel;
import com.spark.cong.growrelationship.Commons.impl.CommonImpl;
import com.spark.cong.growrelationship.R;

import java.util.List;

import static com.spark.cong.growrelationship.Commons.Constant.INTENT_TO_GROUP;

public class GroupActivity extends AppCompatActivity {

    private CommonImpl mCommon = CommonImpl.getInstance();
    private int mGroupId;
    private GroupPeopleViewModel mViewModel;
    private List<GroupPeople> mLstGroupPeople;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);


        if(getIntent()!=null){
            mGroupId = getIntent().getIntExtra(INTENT_TO_GROUP,-1);
        }

        setView();
        setListener();
    }
    public void setView(){

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView_GroupPeople);


        //ViewModal
        mViewModel = new ViewModelProvider(this).get(GroupPeopleViewModel.class);


    }

    public void setListener(){

    }

}
