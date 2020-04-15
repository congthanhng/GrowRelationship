package com.spark.cong.growrelationship.Architecture.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "people")
public class People {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "people_id")
    private int peopleId;

    @ColumnInfo(name = "people_name")
    private String peopleName;

    /*---------------------------------------------------------*/
    //Constructer
    public People(String peopleName) {
        this.peopleName = peopleName;
    }
    public People(){}

    /*---------------------------------------------------------*/
    //getter & setter
    public int getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(int peopleId) {
        this.peopleId = peopleId;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }
}
