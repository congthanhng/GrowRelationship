package com.spark.cong.growrelationship.Architecture.ViewModel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.spark.cong.growrelationship.Architecture.Entity.User;
import com.spark.cong.growrelationship.Architecture.Repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private LiveData<List<User>> mAllUser;

    public UserViewModel(Application application) {
        super(application);
        userRepository = new UserRepository(application);
        mAllUser =userRepository.getmAllUser();
    }

    public LiveData<List<User>> getAllUsers(){
        return mAllUser;
    }

    public void insertUser(User user){
        userRepository.insertUser(user);
    }

    public void deleteAlluser(){
        userRepository.deleteAllUser();
    }

}
