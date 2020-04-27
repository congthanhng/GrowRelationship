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

    /*----------------------------------Constructor-----------------------------*/
    public GroupPeopleRepository(Application application){
        GrowDatabase database = GrowDatabase.getInstance(application);
        groupPeopleDAO = database.groupPeopleDAO();
    }


    /*----------------------------------method-----------------------------*/
    public List<GroupPeople> getAllGroupPeople(){return groupPeopleDAO.getAllGroupPeople();}

    public LiveData<List<GroupPeople>> getAllGroupPeopleByGroupId(int groupId){return groupPeopleDAO.getAllPeopleByGroupId(groupId);}

    public void insertGroupPeople(GroupPeople groupPeople){
        new InsertGroupPeopleAsyncTask(groupPeopleDAO).execute(groupPeople);
    }

    //get all PeopleId without GroupId
    public LiveData<int[]> getAllPeopleIdWithoutGroupId(int groupId){
//        try {
//            return new GetAllPeopleIdWithoutGroupIdAsyncTask(groupPeopleDAO).execute(groupId).get();
//        }catch (Exception e){
//            throw new RuntimeException("error to get peopleId without GroupID");
//        }
        return groupPeopleDAO.getAllPeopleIdWithoutGroupId(groupId);
    }

    //delete by peopleId
    public void deleteGroupPeople(GroupPeople groupPeople){
        new DeleteGroupPeopleAsyncTask(groupPeopleDAO).execute(groupPeople);
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

    //delete
    public class DeleteGroupPeopleAsyncTask extends AsyncTask<GroupPeople,Void,Void>{
        private GroupPeopleDAO groupPeopleDAO;
        public DeleteGroupPeopleAsyncTask(GroupPeopleDAO groupPeopleDAO){this.groupPeopleDAO = groupPeopleDAO;}
        @Override
        protected Void doInBackground(GroupPeople... integers) {
            groupPeopleDAO.deleteGroupPeople(integers[0]);
            return null;
        }
    }
}
