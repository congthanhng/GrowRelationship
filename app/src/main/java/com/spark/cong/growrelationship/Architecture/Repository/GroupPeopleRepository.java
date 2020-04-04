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

}
