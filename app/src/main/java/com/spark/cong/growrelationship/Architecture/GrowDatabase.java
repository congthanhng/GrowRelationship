package com.spark.cong.growrelationship.Architecture;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.spark.cong.growrelationship.Architecture.Dao.GroupPeopleDAO;
import com.spark.cong.growrelationship.Architecture.Entity.GroupPeole;

@Database(entities = {GroupPeole.class},version = 1,exportSchema = false)
public abstract class GrowDatabase extends RoomDatabase {
    private static String NAME_OF_DB = "GROW_RELATIONSHIP";

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
            new OnCreateDBAsyncTask(sINSTANCE.groupPeopleDAO()).execute();
        }
    };

    private static class OnCreateDBAsyncTask extends AsyncTask<Void,Void,Void> {
        private GroupPeopleDAO groupPeopleDAO;
        private OnCreateDBAsyncTask(GroupPeopleDAO groupPeopleDAO){this.groupPeopleDAO = groupPeopleDAO;}
        @Override
        protected Void doInBackground(Void... voids) {
            sINSTANCE.groupPeopleDAO().insertGroupPeople(new GroupPeole("Family"));
            sINSTANCE.groupPeopleDAO().insertGroupPeople(new GroupPeole("Friend"));
            sINSTANCE.groupPeopleDAO().insertGroupPeople(new GroupPeole("Work"));
            sINSTANCE.groupPeopleDAO().insertGroupPeople(new GroupPeole("Gym"));
            sINSTANCE.groupPeopleDAO().insertGroupPeople(new GroupPeole("Football"));
            return null;
        }
    }
}
