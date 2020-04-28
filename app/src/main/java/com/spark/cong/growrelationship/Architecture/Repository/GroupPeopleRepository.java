package com.spark.cong.growrelationship.Architecture.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.spark.cong.growrelationship.Architecture.Dao.GroupPeopleDAO;
import com.spark.cong.growrelationship.Architecture.Entity.GroupPeople;
import com.spark.cong.growrelationship.Architecture.Entity.People;
import com.spark.cong.growrelationship.Architecture.GrowDatabase;

import java.util.List;

public class GroupPeopleRepository {
    private GroupPeopleDAO groupPeopleDAO;

    /*----------------------------------Constructor-----------------------------*/
    public GroupPeopleRepository(Application application){
        GrowDatabase database = GrowDatabase.getInstance(application);
        groupPeopleDAO = database.groupPeopleDAO();
    }


    /*----------------------------------select-----------------------------*/
    //getAllGroupPeople
    public List<GroupPeople> getAllGroupPeople(){return groupPeopleDAO.getAllGroupPeople();}

    //getAllPeopleOfGroup
    public LiveData<List<People>> getAllPeopleOfGroup(int groupId){
        return groupPeopleDAO.getAllPeopleOfGroup(groupId);
    }
    //get all by groupId
    public LiveData<List<GroupPeople>> getAllGroupPeopleByGroupId(int groupId){return groupPeopleDAO.getAllPeopleByGroupId(groupId);}

    //getAllPeopleIsNotGroupId
    public LiveData<List<People>> getAllPeopleIsNotGroupId(int groupId){
        return groupPeopleDAO.getAllPeopleIsNotGroupId(groupId);
    }

    public void insertGroupPeople(GroupPeople groupPeople){
        new InsertGroupPeopleAsyncTask(groupPeopleDAO).execute(groupPeople);
    }

    //get all PeopleId without GroupId
    public LiveData<int[]> getAllPeopleIdWithoutGroupId(int groupId){
        return groupPeopleDAO.getAllPeopleIdWithoutGroupId(groupId);
    }

    /*----------------------------------delete-----------------------------*/
    //delete
    public void deleteGroupPeople(GroupPeople groupPeople){
        groupPeopleDAO.deleteGroupPeople(groupPeople);
    }


    /*----------------------------------asyncTask-----------------------------*/
    //insert
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
