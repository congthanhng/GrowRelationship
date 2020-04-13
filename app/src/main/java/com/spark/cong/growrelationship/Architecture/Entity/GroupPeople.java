package com.spark.cong.growrelationship.Architecture.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.*;

@Entity(tableName = "group_people",foreignKeys = {
        @ForeignKey(
                entity = Group.class,
                parentColumns = "group_id",
                childColumns = "group_id",
                onDelete = CASCADE),
        @ForeignKey(
                entity = People.class,
                parentColumns = "people_id",
                childColumns = "people_id",
                onDelete = CASCADE)

})
public class GroupPeople {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "group_people_id")
    private int groupPeopleId;

    @NonNull
    @ColumnInfo(name = "group_id",index = true)
    private int groupId;

    @NonNull
    @ColumnInfo(name = "people_id")
    private int peopleId;

    public GroupPeople(int groupId, int peopleId) {
        this.groupId = groupId;
        this.peopleId = peopleId;
    }

    public int getGroupPeopleId() {
        return groupPeopleId;
    }

    public void setGroupPeopleId(int groupPeopleId) {
        this.groupPeopleId = groupPeopleId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(int peopleId) {
        this.peopleId = peopleId;
    }
}
