package com.spark.cong.growrelationship.Architecture.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.spark.cong.growrelationship.Architecture.Entity.GroupPeople;

import java.util.List;

@Dao
public interface GroupPeopleDAO {

    //get all
    @Query("SELECT * FROM GROUP_PEOPLE")
    LiveData<List<GroupPeople>> getAllGroupPeople();

    //get all people in Group by groupid
    @Query("SELECT * FROM group_people WHERE group_id = :groupId")
    LiveData<List<GroupPeople>> getPeopleByGroupId(int groupId);
}
