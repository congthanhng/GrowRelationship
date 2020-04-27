package com.spark.cong.growrelationship.Architecture.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.spark.cong.growrelationship.Architecture.Dao.GroupDAO;
import com.spark.cong.growrelationship.Architecture.Entity.Group;
import com.spark.cong.growrelationship.Architecture.GrowDatabase;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class GroupRepository {
    private GroupDAO mGroupDAO;
    private LiveData<List<Group>> mAllGroup;

    //init
    public GroupRepository(Application application){
        GrowDatabase db = GrowDatabase.getInstance(application);
        mGroupDAO = db.groupDAO();
        mAllGroup = mGroupDAO.getAllGroup();
    }

    //get All record
    public LiveData<List<Group>>getAllGroup(){return mAllGroup;}

    //insert
    public void insertGroup(Group group){
        mGroupDAO.insertGroup(group);
//        new InsertGroupAsyncTask(mGroupDAO).execute(group);
    }

    //delete all record
    public void deleteAllGroup(){
        new DeleteAllGroupAsyncTask(mGroupDAO).execute();
    }

    //delete a record
    public void deleteGroupById(int groupId){
//        new DeleteGroupByIdAsyncTask(mGroupDAO).execute(groupId);
        mGroupDAO.deleteGroupById(groupId);
    }

    //update a record
    public void updateGroup(Group group){
        new UpdateGroupAsyncTask(mGroupDAO).execute(group);
    }

    // get row by Id
    public Flowable<Group> getGroupById(int groupId){
        return mGroupDAO.getGroupById(groupId);
    }

    //delete group
    public void deleteGroup(Group group){
//        new DeleteGroupAsyncTask(mGroupDAO).execute(group);
        mGroupDAO.deleteGroup(group);
    }


    //asynctask delete all record
    public static class DeleteAllGroupAsyncTask extends AsyncTask<Void,Void,Void>{
        private GroupDAO groupDAO;
        DeleteAllGroupAsyncTask(GroupDAO groupDAO){this.groupDAO = groupDAO;}
        @Override
        protected Void doInBackground(Void... voids) {
            groupDAO.deleteAllGroup();
            return null;
        }
    }


    //asynctask update a group
    public static class UpdateGroupAsyncTask extends AsyncTask<Group,Void,Void>{
        private GroupDAO groupDAO;
        UpdateGroupAsyncTask(GroupDAO groupDAO){this.groupDAO = groupDAO;}
        @Override
        protected Void doInBackground(Group... groups) {
            groupDAO.updateGroup(groups[0]);
            return null;
        }
    }
}
