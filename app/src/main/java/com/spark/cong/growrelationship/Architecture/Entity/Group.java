package com.spark.cong.growrelationship.Architecture.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "group")
public class Group implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "group_id")
    private int groupId;

    @ColumnInfo(name= "group_name")
    private String groupName;

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
