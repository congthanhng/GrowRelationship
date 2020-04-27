package com.spark.cong.growrelationship.Architecture.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.spark.cong.growrelationship.Architecture.Entity.Group;
import com.spark.cong.growrelationship.Architecture.Repository.GroupRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Action;

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
    public Flowable<Group> getGroupById(int groupId){
        return mGroupRepository.getGroupById(groupId);
    }


    /*----------------------------------Delete-------------------------------------*/
    //delete all record
    public void deleteAllGroup(){
        mGroupRepository.deleteAllGroup();
    }
    //delete by group id
    public Completable deleteGroupById(final int groupId){
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                mGroupRepository.deleteGroupById(groupId);

            }
        });
    }

    //delete a group
    public Completable deleteGroup(final Group group){
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                mGroupRepository.deleteGroup(group);
            }
        });
    }


    /*----------------------------------Insert-------------------------------------*/
    //insert a record
    public void insertGroup(final Group group){
        mGroupRepository.insertGroup(group);
    }


    /*----------------------------------Update-------------------------------------*/
    //update a record
    public void updateGroup(Group group){
        mGroupRepository.updateGroup(group);
    }


}
