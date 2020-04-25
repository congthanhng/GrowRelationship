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

    /*----------------------------------constructor-------------------------------------*/
    public GroupViewModel(@NonNull Application application) {
        super(application);
        mGroupRepository = new GroupRepository(application);
        mAllGroup= mGroupRepository.getAllGroup();
    }


    /*----------------------------------Select-------------------------------------*/
    //get all record
    public LiveData<List<Group>> getAllGroup() {
        return mAllGroup;
    }
    //get row by Id
    public Group getGroupById(int groupId){
        return mGroupRepository.getGroupById(groupId);
    }


    /*----------------------------------Delete-------------------------------------*/
    //delete all record
    public void deleteAllGroup(){
        mGroupRepository.deleteAllGroup();
    }
    //delete by group id
    public void deleteGroupById(int groupId){
        mGroupRepository.deleteGroupById(groupId);
    }
    //delete a group
    public void deleteGroup(Group group){
        mGroupRepository.deleteGroup(group);
    }


    /*----------------------------------Insert-------------------------------------*/
    //insert a record
    public void insertGroup(Group group){
        mGroupRepository.insertGroup(group);
    }


    /*----------------------------------Update-------------------------------------*/
    //update a record
    public void updateGroup(Group group){
        mGroupRepository.updateGroup(group);
    }


}
