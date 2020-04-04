package com.spark.cong.growrelationship;

import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.InstrumentationInfo;

import androidx.room.Room;

import com.spark.cong.growrelationship.Architecture.Entity.User;
import com.spark.cong.growrelationship.Architecture.UserDatabase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    Context context;
    @Before
    public void initDb() throws Exception {
    }
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}