package com.spark.cong.growrelationship.Architecture.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.spark.cong.growrelationship.Architecture.Entity.GroupPeople;
import com.spark.cong.growrelationship.Architecture.Entity.People;
import com.spark.cong.growrelationship.Architecture.Repository.GroupPeopleRepository;
import com.spark.cong.growrelationship.Architecture.Repository.PeopleRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GroupPeopleViewModel extends AndroidViewModel {
    private GroupPeopleRepository groupPeopleRepository;
    private MutableLiveData<Integer> mGroupId;

    public GroupPeopleViewModel(@NonNull Application application) {
        super(application);
        groupPeopleRepository = new GroupPeopleRepository(application);
        mGroupId = new MutableLiveData<>();
    }


    //get all by groupId
    public LiveData<List<GroupPeople>> getAllGroupPeopleByGroupId() {
        return Transformations.switchMap(mGroupId, new Function<Integer, LiveData<List<GroupPeople>>>() {
            @Override
            public LiveData<List<GroupPeople>> apply(Integer input) {
                return groupPeopleRepository.getAllGroupPeopleByGroupId(input);
            }
        });
    }

    //getAllPeopleOfGroup
    public LiveData<List<People>> getAllPeopleOfGroup(int groupId){
        return groupPeopleRepository.getAllPeopleOfGroup(groupId);
    }

    //set value of MutableLiveData
    public void setGroupId(int groupId){
        mGroupId.setValue(groupId);
    }

    //insert
    public void insertGroupPeople(GroupPeople groupPeople){
        groupPeopleRepository.insertGroupPeople(groupPeople);
    }

    //get all peopleId without Groupid - use for SelectPeopleActivity
    public LiveData<int[]> getAllPeopleIdByWithoutGroupId(int groupId){
       return groupPeopleRepository.getAllPeopleIdWithoutGroupId(groupId);
    }

    //get all People is not given groupId
    public LiveData<List<People>> getAllPeopleIsNotGroupId(int groupId){
        return groupPeopleRepository.getAllPeopleIsNotGroupId(groupId);
    }

    //delete
    public void deleteGroupPeople(GroupPeople groupPeople){
        groupPeopleRepository.deleteGroupPeople(groupPeople);
    }

}
