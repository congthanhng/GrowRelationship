package com.spark.cong.growrelationship;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.spark.cong.growrelationship.Architecture.Dao.UserDAO;
import com.spark.cong.growrelationship.Architecture.Entity.User;
import com.spark.cong.growrelationship.Architecture.UserDatabase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private UserDAO userDAO;
    private UserDatabase database;

    @Before
    public void initDb(){
        Context context = ApplicationProvider.getApplicationContext();
        database = Room.inMemoryDatabaseBuilder(context, UserDatabase.class)
                .build();
        userDAO = database.userDAO();
    }

    @After
    public void closeDb() throws IOException {
        database.close();
    }

    @Test
    public void insertUser() throws Exception {
        User user = new User(1, "cong");
        userDAO.insertUser(user);
        List<User> user2 = userDAO.findByname("cong");
        assertEquals(user.getUserName().toString(),user2.get(0).getUserName().toString());
    }


}
