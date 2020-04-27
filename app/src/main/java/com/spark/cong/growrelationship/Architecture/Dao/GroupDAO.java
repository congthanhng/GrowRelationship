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

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface GroupDAO {

    /*----------------------------------select -------------------------------------*/
    //get all
    @Query("SELECT * FROM `Group`")
    LiveData<List<Group>> getAllGroup();

    //get group by id
    @Query("SELECT * FROM `group` WHERE group_id = :groupId LIMIT 1")
    Group getGroupById(int groupId);


    /*----------------------------------insert-------------------------------------*/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertGroup(Group group);


    /*----------------------------------delete-------------------------------------*/
    //delete by id
    @Query("DELETE FROM `Group` WHERE group_id = :groupId")
    void deleteGroupById(int groupId);

    //delete all
    @Query("DELETE FROM `Group`")
    void deleteAllGroup();

    //delete a row
    @Delete
    void deleteGroup(Group group);


    /*----------------------------------update-------------------------------------*/
    @Update
    void updateGroup(Group group);




}
