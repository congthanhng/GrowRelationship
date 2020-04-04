package com.spark.cong.growrelationship.Architecture.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.spark.cong.growrelationship.Architecture.Entity.GroupPeole;

import java.util.List;

@Dao
public interface GroupPeopleDAO {

    @Query("SELECT * FROM group_people")
    LiveData<List<GroupPeole>> getAllGroupPeople();

    @Insert
    void insertGroupPeople(GroupPeole groupPeole);

    @Delete
    void deleteGroupPeople(GroupPeole groupPeole);

    @Query("DELETE FROM GROUP_PEOPLE")
    void deleteAllGroupPeople();
}
