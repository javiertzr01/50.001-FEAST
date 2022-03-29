package com.example.feast;

import org.junit.Test;

import static org.junit.Assert.*;

import java.time.DayOfWeek;

public class WeeklyTrackerTestUnit {

    @Test
    public void WeeklyTrackerDefaultCheck()
    {
        WeeklyTracker defaultWeeklyTracker = new WeeklyTracker();
        System.out.println(defaultWeeklyTracker.getDateCreated());
    }

    /*@Test
    public void DailyTrackerCheck()
    {
        DailyTracker defaultDailyTracker = new DailyTracker();
    }*/

}
