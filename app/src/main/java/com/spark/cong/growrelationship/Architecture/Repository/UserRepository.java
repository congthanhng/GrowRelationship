package com.spark.cong.growrelationship.Architecture.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.spark.cong.growrelationship.Architecture.Dao.UserDAO;
import com.spark.cong.growrelationship.Architecture.Entity.User;
import com.spark.cong.growrelationship.Architecture.UserDatabase;

import java.util.List;

public class UserRepository {
    private UserDAO mUserDao;
    private LiveData<List<User>> mAllUser;
    public UserRepository(Application application){
        UserDatabase db = UserDatabase.getINSTANCE(application);
        mUserDao = db.userDAO();
        mAllUser = mUserDao.getAllUser();
    }

    public void insertUser(User user){
        new InsertUserAsyncTask(mUserDao).execute(user);
    }

    public void deleteUser(User user){

        mUserDao.deleteUser(user);
    }

    public UserDAO getmUserDao() {
        return mUserDao;
    }

    public void setmUserDao(UserDAO mUserDao) {
        this.mUserDao = mUserDao;
    }

    public LiveData<List<User>> getmAllUser() {
        return mAllUser;
    }

    public void setmAllUser(LiveData<List<User>> mAllUser) {
        this.mAllUser = mAllUser;
    }1

    public void deleteAllUser(){
        new DeleteAllUserAsyncTask(mUserDao).execute();
    }

    private static class DeleteAllUserAsyncTask extends AsyncTask<Void,Void,Void>{
        private UserDAO userDAO;
        DeleteAllUserAsyncTask(UserDAO userDAO){this.userDAO = userDAO;}
        @Override
        protected Void doInBackground(Void... voids) {
            userDAO.deleteAllUser();
            return null;
        }
    }

    private static class InsertUserAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDAO userDAO;
        InsertUserAsyncTask(UserDAO userDAO){this.userDAO = userDAO;}
        @Override
        protected Void doInBackground(User... user) {
            userDAO.insertUser(user[0]);
            return null;
        }
    }
}
