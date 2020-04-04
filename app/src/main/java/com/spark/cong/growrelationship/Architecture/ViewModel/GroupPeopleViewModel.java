package com.spark.cong.growrelationship.Architecture.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.spark.cong.growrelationship.Architecture.Entity.GroupPeole;
import com.spark.cong.growrelationship.Architecture.Repository.GroupPeopleRepository;

import java.util.List;

public class GroupPeopleViewModel extends AndroidViewModel {
    private GroupPeopleRepository mGroupPeopleRepository;
    private LiveData<List<GroupPeole>> mAllGroupPeople;

    public GroupPeopleViewModel(@NonNull Application application) {
        super(application);
        mGroupPeopleRepository = new GroupPeopleRepository(application);
        mAllGroupPeople= mGroupPeopleRepository.getAllGroupPeople();
    }

    //get all record
    public LiveData<List<GroupPeole>> getAllGroupPeople() {
        return mAllGroupPeople;
    }

    //delete all record
    public void deleteAllGroupPeople(){
        mGroupPeopleRepository.deleteAllGroupPeople();
    }

    //insert a record
    public void insertGroupPeople(GroupPeole groupPeole){
        mGroupPeopleRepository.insertGroupPeople(groupPeole);
    }
}
