package com.spark.cong.growrelationship.Architecture.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.spark.cong.growrelationship.Architecture.Dao.PeopleDAO;
import com.spark.cong.growrelationship.Architecture.Entity.People;
import com.spark.cong.growrelationship.Architecture.GrowDatabase;

import java.util.List;

public class PeopleRepository {
    private PeopleDAO peopleDAO;

    public PeopleRepository(Application application){
        GrowDatabase db = GrowDatabase.getInstance(application);
        peopleDAO = db.peopleDAO();
    }

    //get all
    public LiveData<List<People>> getAllPeople(){
        return peopleDAO.getAllPeople();
    }

    //insert People
    public void insertPeople(People people){
        new InsertPeopleAsyncTask(peopleDAO).execute(people);
    }

    public class InsertPeopleAsyncTask extends AsyncTask<People,Void,Void>{
        private PeopleDAO peopleDAO;
        public InsertPeopleAsyncTask(PeopleDAO peopleDAO){
            this.peopleDAO = peopleDAO;
        }
        @Override
        protected Void doInBackground(People... peoples) {
            peopleDAO.insertPeople(peoples[0]);
            return null;
        }
    }
}
