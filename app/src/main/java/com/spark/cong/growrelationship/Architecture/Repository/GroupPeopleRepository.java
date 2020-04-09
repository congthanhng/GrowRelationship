package com.spark.cong.growrelationship.Architecture.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.spark.cong.growrelationship.Architecture.Dao.GroupPeopleDAO;
import com.spark.cong.growrelationship.Architecture.Entity.GroupPeole;
import com.spark.cong.growrelationship.Architecture.GrowDatabase;

import java.util.List;

public class GroupPeopleRepository {
    private GroupPeopleDAO mGroupPeopleDAO;
    private LiveData<List<GroupPeole>> mAllGroupPeople;

    //init
    public GroupPeopleRepository(Application application){
        GrowDatabase db = GrowDatabase.getInstance(application);
        mGroupPeopleDAO = db.groupPeopleDAO();
        mAllGroupPeople = mGroupPeopleDAO.getAllGroupPeople();
    }

    //get All record
    public LiveData<List<GroupPeole>>getAllGroupPeople(){return mAllGroupPeople;}

    //insert
    public void insertGroupPeople(GroupPeole groupPeole){
        new InsertGroupPeopleAsyncTask(mGroupPeopleDAO).execute(groupPeole);
    }

    //delete all record
    public void deleteAllGroupPeople(){
        new DeleteAllGroupPeopleAsyncTask(mGroupPeopleDAO).execute();
    }

    //delete a record
    public void deleteGroupById(int groupId){
        new DeleteGroupAsyncTask(mGroupPeopleDAO).execute(groupId);
    }

    //update a record
    public void updateGroup(GroupPeole groupPeole){
        new UpdateGroupAsyncTask(mGroupPeopleDAO).execute(groupPeole);
    }

    //asynctask insert
    public static class InsertGroupPeopleAsyncTask extends AsyncTask<GroupPeole,Void,Void>{
        private GroupPeopleDAO groupPeopleDAO;
        InsertGroupPeopleAsyncTask(GroupPeopleDAO groupPeopleDAO){this.groupPeopleDAO = groupPeopleDAO;}
        @Override
        protected Void doInBackground(GroupPeole... groupPeoles) {
            groupPeopleDAO.insertGroupPeople(groupPeoles[0]);
            return null;
        }
    }
    //asynctask delete all record
    public static class DeleteAllGroupPeopleAsyncTask extends AsyncTask<Void,Void,Void>{
        private GroupPeopleDAO groupPeopleDAO;
        DeleteAllGroupPeopleAsyncTask(GroupPeopleDAO groupPeopleDAO){this.groupPeopleDAO = groupPeopleDAO;}
        @Override
        protected Void doInBackground(Void... voids) {
            groupPeopleDAO.deleteAllGroupPeople();
            return null;
        }
    }
    //asynctask delete a group
    public static class DeleteGroupAsyncTask extends AsyncTask<Integer,Void,Void>{
        private GroupPeopleDAO groupPeopleDAO;
        DeleteGroupAsyncTask(GroupPeopleDAO groupPeopleDAO){this.groupPeopleDAO = groupPeopleDAO;}

        @Override
        protected Void doInBackground(Integer... integers) {
            groupPeopleDAO.deleteGroupById(integers[0]);
            return null;
        }
    }

    //asynctask update a group
    public static class UpdateGroupAsyncTask extends AsyncTask<GroupPeole,Void,Void>{
        private GroupPeopleDAO groupPeopleDAO;
        UpdateGroupAsyncTask(GroupPeopleDAO groupPeopleDAO){this.groupPeopleDAO = groupPeopleDAO;}
        @Override
        protected Void doInBackground(GroupPeole... groupPeoles) {
            groupPeopleDAO.updateGroup(groupPeoles[0]);
            return null;
        }
    }

}
