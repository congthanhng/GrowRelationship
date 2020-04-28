package com.spark.cong.growrelationship.Architecture.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.spark.cong.growrelationship.Architecture.Entity.GroupPeople;
import com.spark.cong.growrelationship.Architecture.Entity.People;

import java.util.List;

@Dao
public interface GroupPeopleDAO {
    /*----------------------------------select -------------------------------------*/
    //get all
    @Query("SELECT * FROM GROUP_PEOPLE")
    List<GroupPeople> getAllGroupPeople();

    //get people of group
    @Query("select * From people as p " +
            "inner join (Select * from group_people where group_id = :groupId) as gp " +
            "on p.people_id = gp.people_id")
    LiveData<List<People>> getAllPeopleOfGroup(int groupId);

    //get all people in Group by groupId
    @Query("SELECT * FROM group_people WHERE group_id = :groupId")
    LiveData<List<GroupPeople>> getAllPeopleByGroupId(int groupId);

    //get all record not have GroupId
    @Query("SELECT people_id FROM group_people " +
            "WHERE people_id NOT IN (" +
            "SELECT people_id FROM group_people WHERE group_id = :groupId) " +
            "UNION " +
            "SELECT people_id FROM people WHERE people_id NOT IN (" +
            "SELECT people_id FROM group_people)")
    LiveData<int[]> getAllPeopleIdWithoutGroupId(int groupId);

    //getAllPeopleIsNotGroupId
    @Query("SELECT * FROM people as a " +
            "INNER JOIN " +
            "(SELECT people_id FROM group_people " +
            "WHERE people_id NOT IN (" +
            "SELECT people_id FROM group_people WHERE group_id = :groupId) " +
            "UNION " +
            "SELECT people_id FROM people WHERE people_id NOT IN (" +
            "SELECT people_id FROM group_people)) as b " +
            "ON a.people_id = b.people_id")
    LiveData<List<People>>getAllPeopleIsNotGroupId(int groupId);

    /*----------------------------------insert -------------------------------------*/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertGroupPeople(GroupPeople groupPeople);

    /*----------------------------------delete -------------------------------------*/
    @Delete
    void deleteGroupPeople(GroupPeople groupPeople);

}
