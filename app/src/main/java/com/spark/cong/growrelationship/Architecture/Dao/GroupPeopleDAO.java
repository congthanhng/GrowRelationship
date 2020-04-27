package com.spark.cong.growrelationship.Architecture.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.spark.cong.growrelationship.Architecture.Entity.GroupPeople;

import java.util.List;

@Dao
public interface GroupPeopleDAO {

    //get all
    @Query("SELECT * FROM GROUP_PEOPLE")
    List<GroupPeople> getAllGroupPeople();

    //get all people in Group by groupid
    @Query("SELECT * FROM group_people WHERE group_id = :groupId")
    LiveData<List<GroupPeople>> getAllPeopleByGroupId(int groupId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertGroupPeople(GroupPeople groupPeople);

    @Query("SELECT people_id FROM group_people " +
            "WHERE people_id NOT IN (" +
            "SELECT people_id FROM group_people WHERE group_id = :groupId) " +
            "UNION " +
            "SELECT people_id FROM people WHERE people_id NOT IN (" +
            "SELECT people_id FROM group_people)")
    LiveData<int[]> getAllPeopleIdWithoutGroupId(int groupId);

    //delete
    @Delete
    void deleteGroupPeople(GroupPeople groupPeople);
}
