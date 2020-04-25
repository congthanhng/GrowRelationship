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

    /* -------------------------constructor--------------------------*/
    public PeopleRepository(Application application){
        GrowDatabase db = GrowDatabase.getInstance(application);
        peopleDAO = db.peopleDAO();
    }


    /* -------------------------function--------------------------*/
    //get all
    public LiveData<List<People>> getAllPeople(){
        return peopleDAO.getAllPeople();
    }

    //insert a row
    public void insertPeople(People people){
        new InsertPeopleAsyncTask(peopleDAO).execute(people);
    }

    //get row by id
    public People getPeopleById(int id){
        
        try {
            People people = new GetPeopleByIdAsyncTask(peopleDAO).execute(id).get();
            return people;
        }catch (Exception e){
            throw new RuntimeException("error to get people");
        }

    }

    //deletePeople
    public void deletePeople(People people){
        new DeletePeopleAsyncTask(peopleDAO).execute(people);
    }


    /* -------------------------synchronous--------------------------*/
    //synchronous of insertPeople
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
    //synchronous of getPeopleById
    public class GetPeopleByIdAsyncTask extends AsyncTask<Integer,Void, People>{
        private PeopleDAO peopleDAO;
        public GetPeopleByIdAsyncTask(PeopleDAO peopleDAO){
            this.peopleDAO = peopleDAO;
        }
        @Override
        protected People doInBackground(Integer... integers) {
            return peopleDAO.getPeopleById(integers[0]);
        }

    }

    //synchronous of deletePeople
    public class DeletePeopleAsyncTask extends AsyncTask<People,Void, People>{
        private PeopleDAO peopleDAO;
        public DeletePeopleAsyncTask(PeopleDAO peopleDAO){
            this.peopleDAO = peopleDAO;
        }
        @Override
        protected People doInBackground(People... peoples) {
            peopleDAO.deletePeople(peoples[0]);
            return null;
        }

    }
}
