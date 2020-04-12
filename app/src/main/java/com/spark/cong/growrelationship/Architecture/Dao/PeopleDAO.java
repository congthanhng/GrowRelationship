package com.spark.cong.growrelationship.Architecture.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.spark.cong.growrelationship.Architecture.Entity.People;

import java.util.List;

@Dao
public interface PeopleDAO {
    //get all
    @Query("SELECT * FROM people")
    LiveData<List<People>> getAllPeople();
}
