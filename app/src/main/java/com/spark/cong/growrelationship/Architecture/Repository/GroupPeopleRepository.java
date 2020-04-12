package com.spark.cong.growrelationship.Architecture.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.spark.cong.growrelationship.Architecture.Dao.GroupPeopleDAO;
import com.spark.cong.growrelationship.Architecture.Entity.GroupPeople;
import com.spark.cong.growrelationship.Architecture.GrowDatabase;

import java.util.List;

public class GroupPeopleRepository {
    private GroupPeopleDAO groupPeopleDAO;
    private LiveData<List<GroupPeople>> mGetPeopleByGroupId;

    public GroupPeopleRepository(int groupId, Application application){
        GrowDatabase database = GrowDatabase.getInstance(application);
        groupPeopleDAO = database.groupPeopleDAO();
        mGetPeopleByGroupId = groupPeopleDAO.getPeopleByGroupId(groupId);
    }

    public LiveData<List<GroupPeople>> getPeopleByGroupId(){return mGetPeopleByGroupId;}
}
