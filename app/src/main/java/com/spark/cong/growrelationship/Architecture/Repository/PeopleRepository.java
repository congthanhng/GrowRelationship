package com.spark.cong.growrelationship.Architecture.Repository;

import android.app.Application;

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
    public LiveData<List<People>> getAllPeople(){
        return peopleDAO.getAllPeople();
    }
}
