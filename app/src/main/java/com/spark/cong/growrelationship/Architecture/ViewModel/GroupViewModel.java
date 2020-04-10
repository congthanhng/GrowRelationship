package com.spark.cong.growrelationship.Architecture.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.spark.cong.growrelationship.Architecture.Entity.Group;
import com.spark.cong.growrelationship.Architecture.Repository.GroupRepository;

import java.util.List;

public class GroupViewModel extends AndroidViewModel {
    private GroupRepository mGroupRepository;
    private LiveData<List<Group>> mAllGroup;

    public GroupViewModel(@NonNull Application application) {
        super(application);
        mGroupRepository = new GroupRepository(application);
        mAllGroup= mGroupRepository.getAllGroup();
    }

    //get all record
    public LiveData<List<Group>> getAllGroup() {
        return mAllGroup;
    }

    //delete all record
    public void deleteAllGroup(){
        mGroupRepository.deleteAllGroup();
    }

    //delete a recoed
    public void deleteGroupById(int groupId){
        mGroupRepository.deleteGroupById(groupId);}

    //insert a record
    public void insertGroup(Group group){
        mGroupRepository.insertGroup(group);
    }

    //update a record
    public void updateGroup(Group group){
        mGroupRepository.updateGroup(group);
    }
}
