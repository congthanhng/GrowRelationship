package com.spark.cong.growrelationship.Architecture.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "group_people")
public class GroupPeole {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "g_id")
    private int id;

    @ColumnInfo(name= "g_name")
    private String name;

    public GroupPeole(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
