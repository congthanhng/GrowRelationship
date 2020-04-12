package com.spark.cong.growrelationship.Architecture;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.spark.cong.growrelationship.Architecture.Dao.GroupDAO;
import com.spark.cong.growrelationship.Architecture.Dao.GroupPeopleDAO;
import com.spark.cong.growrelationship.Architecture.Entity.Group;
import com.spark.cong.growrelationship.Architecture.Entity.GroupPeople;
import com.spark.cong.growrelationship.Architecture.Entity.People;

@Database(entities = {Group.class, GroupPeople.class, People.class},version = 1,exportSchema = false)
public abstract class GrowDatabase extends RoomDatabase {
    private static String NAME_OF_DB = "GROW_RELATIONSHIP";

    public abstract GroupDAO groupDAO();
    public abstract GroupPeopleDAO groupPeopleDAO();
    private static GrowDatabase sINSTANCE;

    public static GrowDatabase getInstance(final Context context){
        if(sINSTANCE == null){
            sINSTANCE = Room.databaseBuilder(context.getApplicationContext(),GrowDatabase.class,NAME_OF_DB)
                    .fallbackToDestructiveMigration()
                    .addCallback(mRoomDBCallBack)
                    .build();
        }
        return sINSTANCE;
    }

    //call callback
    private static RoomDatabase.Callback mRoomDBCallBack = new RoomDatabase.Callback(){

        //init record row of table when database create in the first time
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new OnCreateDBAsyncTask(sINSTANCE.groupDAO()).execute();
        }
    };

    private static class OnCreateDBAsyncTask extends AsyncTask<Void,Void,Void> {
        private GroupDAO groupDAO;
        private OnCreateDBAsyncTask(GroupDAO groupDAO){this.groupDAO = groupDAO;}
        @Override
        protected Void doInBackground(Void... voids) {
            sINSTANCE.groupDAO().insertGroup(new Group("Family"));
            sINSTANCE.groupDAO().insertGroup(new Group("Friend"));
            sINSTANCE.groupDAO().insertGroup(new Group("Work"));
            sINSTANCE.groupDAO().insertGroup(new Group("Gym"));
            sINSTANCE.groupDAO().insertGroup(new Group("Football"));
            return null;
        }
    }
}
