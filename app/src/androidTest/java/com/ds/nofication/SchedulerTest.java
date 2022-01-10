package com.ds.nofication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.text.format.DateFormat;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class SchedulerTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void onCreateNotification(){

        try {
            Scheduler schedule = new Scheduler();
            Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            schedule.startNotification(appContext, 1, 1000);
        }
        catch (RuntimeException e) {
            assertNotNull("missing original exception", e.getCause());
            assertEquals(IOException.class, e.getCause().getClass());
        }
        catch (Exception e) {
            Assert.fail("Throwable was unexpectedly thrown." + e);
        }
    }

    @Test
    public void DateDifference() {
        try {
            Scheduler schedule = new Scheduler();

            Calendar currentDate = Calendar.getInstance();
            currentDate.set(
                    currentDate.get(Calendar.YEAR),
                    currentDate.get(Calendar.MONTH),
                    currentDate.get(Calendar.DATE),
                    19,
                    0);
            Long diff = schedule.getDateDiff(currentDate);
            assertNotNull(diff);
        }
        catch (RuntimeException e) {
            assertNotNull("missing original exception", e.getCause());
            assertEquals(IOException.class, e.getCause().getClass());
        }
        catch (Exception e) {
            Assert.fail("Throwable was unexpectedly thrown." + e);
        }
    }



}
