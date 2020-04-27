package com.spark.cong.growrelationship.Architecture.ViewModel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.spark.cong.growrelationship.Architecture.Entity.People;
import com.spark.cong.growrelationship.Architecture.Repository.PeopleRepository;

import java.util.List;

import io.reactivex.Flowable;

public class PeopleViewModel extends AndroidViewModel {
    private PeopleRepository peopleRepository;

    //constructor
    public PeopleViewModel(@NonNull Application application) {
        super(application);
        peopleRepository = new PeopleRepository(application);
    }

    /*-----------------------------Select-------------------------*/
    //get all record
    public LiveData<List<People>> getAllPeople(){
        return peopleRepository.getAllPeople();
    }
    //get row by id
    public Flowable<People> getPeopleById(int id){
        return peopleRepository.getPeopleById(id);
    }

    /*-----------------------------insert-------------------------*/
    //insert a row
    public void insertPeople(People people){
        peopleRepository.insertPeople(people);
    }


    /*-----------------------------delete-------------------------*/
    //delete people
    public void deletePeople(People people){
        peopleRepository.deletePeople(people);
    }

}
