package com.spark.cong.growrelationship.Architecture.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.spark.cong.growrelationship.Architecture.Entity.GroupPeople;
import com.spark.cong.growrelationship.Architecture.Repository.GroupPeopleRepository;

import java.util.List;

public class GroupPeopleViewModel extends AndroidViewModel {
    private GroupPeopleRepository groupPeopleRepository;
    public GroupPeopleViewModel(@NonNull Application application) {
        super(application);
        groupPeopleRepository = new GroupPeopleRepository(application);
    }

    public LiveData<List<GroupPeople>> getAllGroupPeople(){
        return groupPeopleRepository.getAllGroupPeople();
    }

}
