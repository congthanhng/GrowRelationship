package com.spark.cong.growrelationship.Architecture.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.spark.cong.growrelationship.Architecture.Entity.GroupPeople;
import com.spark.cong.growrelationship.Architecture.Repository.GroupPeopleRepository;

import java.util.List;

public class GroupPeopleViewModel extends AndroidViewModel {
    private GroupPeopleRepository groupPeopleRepository;
    private LiveData<List<GroupPeople>> mAllGroupPeopleByGroupId;

    public GroupPeopleViewModel(@NonNull Application application) {
        super(application);
        groupPeopleRepository = new GroupPeopleRepository(application);
    }

    public LiveData<List<GroupPeople>> getAllGroupPeople(){
        return groupPeopleRepository.getAllGroupPeople();
    }

    public LiveData<List<GroupPeople>> getAllGroupPeopleByGroupId(int groupId) {return groupPeopleRepository.getAllGroupPeopleByGroupId(groupId);}

    public void insertGroupPeople(GroupPeople groupPeople){
        groupPeopleRepository.insertGroupPeople(groupPeople);
    }
}
