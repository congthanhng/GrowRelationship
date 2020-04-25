package com.spark.cong.growrelationship.Architecture.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.spark.cong.growrelationship.Architecture.Dao.GroupDAO;
import com.spark.cong.growrelationship.Architecture.Entity.Group;
import com.spark.cong.growrelationship.Architecture.GrowDatabase;

import java.util.List;

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
        new InsertGroupAsyncTask(mGroupDAO).execute(group);
    }

    //delete all record
    public void deleteAllGroup(){
        new DeleteAllGroupAsyncTask(mGroupDAO).execute();
    }

    //delete a record
    public void deleteGroupById(int groupId){
        new DeleteGroupByIdAsyncTask(mGroupDAO).execute(groupId);
    }

    //update a record
    public void updateGroup(Group group){
        new UpdateGroupAsyncTask(mGroupDAO).execute(group);
    }

    // get row by Id
    public Group getGroupById(int groupId){
        try {
            Group group = new GetGroupByIdAsyncTask(mGroupDAO).execute(groupId).get();
            return group;
        }catch (Exception e){
            throw new RuntimeException("error to get group by id");
        }
    }

    //delete group
    public void deleteGroup(Group group){
        new DeleteGroupAsyncTask(mGroupDAO).execute(group);
    }

    /*--------------------------------synchronous--------------------------------*/
    //asynctask insert
    public static class InsertGroupAsyncTask extends AsyncTask<Group,Void,Void>{
        private GroupDAO groupDAO;
        InsertGroupAsyncTask(GroupDAO groupDAO){this.groupDAO = groupDAO;}
        @Override
        protected Void doInBackground(Group... groups) {
            groupDAO.insertGroup(groups[0]);
            return null;
        }
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
    //asynctask delete a group
    public static class DeleteGroupAsyncTask extends AsyncTask<Group,Void,Void>{
        private GroupDAO groupDAO;
        DeleteGroupAsyncTask(GroupDAO groupDAO){this.groupDAO = groupDAO;}

        @Override
        protected Void doInBackground(Group... groups) {
            groupDAO.deleteGroup(groups[0]);
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

    //asyncTask getGroupById
    public static class GetGroupByIdAsyncTask extends AsyncTask<Integer,Void,Group>{
        private GroupDAO groupDAO;
        GetGroupByIdAsyncTask(GroupDAO groupDAO){this.groupDAO = groupDAO;}
        @Override
        protected Group doInBackground(Integer... integers) {
            return groupDAO.getGroupById(integers[0]);
        }
    }

    //asyncTask delete group
    public static class DeleteGroupByIdAsyncTask extends AsyncTask<Integer,Void,Void>{
        private GroupDAO groupDAO;
        DeleteGroupByIdAsyncTask(GroupDAO groupDAO){this.groupDAO = groupDAO;}
        @Override
        protected Void doInBackground(Integer... integers) {
            groupDAO.deleteGroupById(integers[0]);
            return null;
        }
    }

}
