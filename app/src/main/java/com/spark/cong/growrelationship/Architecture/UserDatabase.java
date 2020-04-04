package com.spark.cong.growrelationship.Architecture;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.spark.cong.growrelationship.Architecture.Dao.UserDAO;
import com.spark.cong.growrelationship.Architecture.Entity.User;


@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase INSTANCE;

    public abstract UserDAO userDAO();

    public static UserDatabase getINSTANCE(final Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "user_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomDBCallBack)
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDBCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.i("TEST", "i am here ");
            new OnCreateDBAsyncTask(INSTANCE.userDAO()).execute();
        }
    };

    private static class OnCreateDBAsyncTask extends AsyncTask<Void,Void,Void>{
        private UserDAO userDAO;
        private OnCreateDBAsyncTask(UserDAO userDAO){this.userDAO = userDAO;}
        @Override
        protected Void doInBackground(Void... voids) {
            userDAO.insertUser(new User("Nguyen Thanh Cong"));
            userDAO.insertUser(new User("Nguyen Thanh Trinh"));
            userDAO.insertUser(new User("Nguyen Duc Tuan"));
            userDAO.insertUser(new User("Bui Khuong Duy"));
            userDAO.insertUser(new User("Luu Hoang Quyen"));
            return null;
        }
    }
}
