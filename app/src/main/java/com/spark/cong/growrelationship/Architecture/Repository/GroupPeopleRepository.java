package com.spark.cong.growrelationship.Architecture.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.spark.cong.growrelationship.Architecture.Dao.GroupPeopleDAO;
import com.spark.cong.growrelationship.Architecture.Entity.GroupPeople;
import com.spark.cong.growrelationship.Architecture.GrowDatabase;

import java.util.List;

public class GroupPeopleRepository {
    private GroupPeopleDAO groupPeopleDAO;
    public GroupPeopleRepository(Application application){
        GrowDatabase database = GrowDatabase.getInstance(application);
        groupPeopleDAO = database.groupPeopleDAO();
    }

    public LiveData<List<GroupPeople>> getAllGroupPeople(){return groupPeopleDAO.getAllGroupPeople();}

    public LiveData<List<GroupPeople>> getAllGroupPeopleByGroupId(int groupId){return groupPeopleDAO.getAllPeopleByGroupId(groupId);}

    public void insertGroupPeople(GroupPeople groupPeople){
        new InsertGroupPeopleAsyncTask(groupPeopleDAO).execute(groupPeople);
    }

    public class InsertGroupPeopleAsyncTask extends AsyncTask<GroupPeople,Void,Void>{
        private GroupPeopleDAO groupPeopleDAO;
        public InsertGroupPeopleAsyncTask(GroupPeopleDAO groupPeopleDAO){this.groupPeopleDAO = groupPeopleDAO;}
        @Override
        protected Void doInBackground(GroupPeople... groupPeoples) {
            groupPeopleDAO.insertGroupPeople(groupPeoples[0]);
            return null;
        }
    }
}
