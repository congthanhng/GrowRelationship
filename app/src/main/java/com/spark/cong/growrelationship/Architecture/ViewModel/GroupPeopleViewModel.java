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

import java.util.List;

public class GroupPeopleViewModel extends AndroidViewModel {
    private GroupPeopleRepository groupPeopleRepository;
    private LiveData<List<GroupPeople>> mAllGroupPeopleByGroupId;
    private MutableLiveData<Integer> mGroupId;

    public GroupPeopleViewModel(@NonNull Application application) {
        super(application);
        groupPeopleRepository = new GroupPeopleRepository(application);
        mGroupId = new MutableLiveData<>();
    }


    //get all
//    public LiveData<List<GroupPeople>> getAllGroupPeople(){
//        return groupPeopleRepository.getAllGroupPeople();
//    }

    //get all by groupId
    public LiveData<List<GroupPeople>> getAllGroupPeopleByGroupId() {
//        return groupPeopleRepository.getAllGroupPeopleByGroupId(groupId);
        return Transformations.switchMap(mGroupId, new Function<Integer, LiveData<List<GroupPeople>>>() {
            @Override
            public LiveData<List<GroupPeople>> apply(Integer input) {
                return groupPeopleRepository.getAllGroupPeopleByGroupId(input);
            }
        });
    }

    public void setGroupId(int groupId){
        mGroupId.setValue(groupId);
    }


    public void insertGroupPeople(GroupPeople groupPeople){
        groupPeopleRepository.insertGroupPeople(groupPeople);
    }

    //get all peopleId without Groupid - use for SelectPeopleActivity
    public LiveData<int[]> getAllPeopleIdByWithoutGroupId(int groupId){
       return groupPeopleRepository.getAllPeopleIdWithoutGroupId(groupId);
    }

    //delete by peopleid
    public void deleteGroupPeople(GroupPeople groupPeople){
        groupPeopleRepository.deleteGroupPeople(groupPeople);
    }

    public MutableLiveData<List<GroupPeople>> lstGroupPeople(){
        MutableLiveData<List<GroupPeople>> listMutableLiveData = new MutableLiveData<>();
        listMutableLiveData.setValue(groupPeopleRepository.getAllGroupPeople());
        return listMutableLiveData;
    }

//    void setLstGroupPeople(){
//        this.lstGroupPeople.setValue(groupPeopleRepository.getAllGroupPeople());
//    }

    //get all People by GroupId
//    public LiveData<List<People>> getAllPeopleByGroupId(){
//        return Transformations.sw
//    }
}
