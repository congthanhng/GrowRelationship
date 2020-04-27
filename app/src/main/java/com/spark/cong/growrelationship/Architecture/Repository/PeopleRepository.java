package com.spark.cong.growrelationship.Architecture.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.spark.cong.growrelationship.Architecture.Dao.PeopleDAO;
import com.spark.cong.growrelationship.Architecture.Entity.People;
import com.spark.cong.growrelationship.Architecture.GrowDatabase;

import java.util.List;

import io.reactivex.Flowable;

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
        peopleDAO.insertPeople(people);
    }

    //get row by id
    public Flowable<People> getPeopleById(int id){

        return peopleDAO.getPeopleById(id);

    }

    //deletePeople
    public void deletePeople(People people){
        peopleDAO.deletePeople(people);
    }
}
