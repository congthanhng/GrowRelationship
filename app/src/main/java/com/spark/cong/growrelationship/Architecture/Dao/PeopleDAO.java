package com.spark.cong.growrelationship.Architecture.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.spark.cong.growrelationship.Architecture.Entity.People;

import java.util.List;

@Dao
public interface PeopleDAO {
    //get all
    @Query("SELECT * FROM people")
    LiveData<List<People>> getAllPeople();

    //insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPeople(People people);

    //getPeopleById
    @Query("SELECT * FROM people WHERE people_id = :id LIMIT 1")
    People getPeopleById(int id);

    //delete
    @Delete
    void deletePeople(People people);
}
