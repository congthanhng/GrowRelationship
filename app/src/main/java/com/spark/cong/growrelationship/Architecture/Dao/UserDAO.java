package com.spark.cong.growrelationship.Architecture.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.spark.cong.growrelationship.Architecture.Entity.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllUser();

    @Query("SELECT * FROM user WHERE uid IN (:userID)")
    List<User> loadAllByIDs(int[] userID);

    @Query("SELECT * FROM user WHERE user_name LIKE :userName")
    List<User> findByname(String userName);

    @Query("DELETE FROM User")
    void deleteAllUser();

    @Insert
    void insertUser(User user);

    @Delete
    void deleteUser(User user);
}
