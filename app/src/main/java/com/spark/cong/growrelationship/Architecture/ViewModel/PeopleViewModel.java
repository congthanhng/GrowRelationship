package com.spark.cong.growrelationship.Architecture.ViewModel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.spark.cong.growrelationship.Architecture.Entity.People;
import com.spark.cong.growrelationship.Architecture.Repository.PeopleRepository;

import java.util.List;

public class PeopleViewModel extends AndroidViewModel {
    private PeopleRepository peopleRepository;
    public PeopleViewModel(@NonNull Application application) {
        super(application);
        peopleRepository = new PeopleRepository(application);
    }

    public LiveData<List<People>> getAllPeople(){
        return peopleRepository.getAllPeople();
    }

    public void insertPeople(People people){
        peopleRepository.insertPeople(people);
    }

}
