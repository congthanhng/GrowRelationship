package com.spark.cong.growrelationship.Architecture.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.spark.cong.growrelationship.Architecture.Entity.Group;

import java.util.List;

@Dao
public interface GroupDAO {

    @Query("SELECT * FROM `Group`")
    LiveData<List<Group>> getAllGroup();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertGroup(Group group);

    @Query("DELETE FROM `Group` WHERE group_id = :groupId")
    void deleteGroupById(int groupId);

    @Query("DELETE FROM `Group`")
    void deleteAllGroup();

    @Update
    void updateGroup(Group group);

    //get group by id
    @Query("SELECT * FROM `group` WHERE group_id = :groupId LIMIT 1")
    Group getGroupById(int groupId);

    //delete group
    @Delete
    void deleteGroup(Group group);
}
