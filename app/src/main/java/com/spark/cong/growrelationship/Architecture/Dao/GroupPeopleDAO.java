package com.spark.cong.growrelationship.Architecture.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.spark.cong.growrelationship.Architecture.Entity.GroupPeole;

import java.util.List;

@Dao
public interface GroupPeopleDAO {

    @Query("SELECT * FROM group_people")
    LiveData<List<GroupPeole>> getAllGroupPeople();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertGroupPeople(GroupPeole groupPeole);

    @Query("DELETE FROM GROUP_PEOPLE WHERE g_id = :groupId")
    void deleteGroupById(int groupId);

    @Query("DELETE FROM GROUP_PEOPLE")
    void deleteAllGroupPeople();

    @Update
    void updateGroup(GroupPeole groupPeole);
}
