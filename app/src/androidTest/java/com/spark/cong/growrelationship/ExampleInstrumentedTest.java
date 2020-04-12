package com.spark.cong.growrelationship;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.spark.cong.growrelationship.Architecture.Dao.GroupDAO;
import com.spark.cong.growrelationship.Architecture.Entity.Group;
import com.spark.cong.growrelationship.Architecture.GrowDatabase;

import org.junit.After;
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
    private GrowDatabase db;
    private GroupDAO groupDAO;
    @Before
    public void createDb(){
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context,GrowDatabase.class).build();
        groupDAO = db.groupDAO();
        System.out.println("test Start");

    }

    @After
    public void closeDb() throws IOException {
        db.close();
        System.out.println("test Close");
    }
    @Test
    public void useAppContext() throws Exception {
        Group group = new Group("TCong");
        groupDAO.insertGroup(group);

        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

//        assertEquals("com.spark.cong.growrelationship", appContext.getPackageName());
    }
}
