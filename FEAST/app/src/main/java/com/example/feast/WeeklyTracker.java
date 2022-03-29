package com.example.feast;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.*;
import java.util.HashMap;

@RequiresApi(api = Build.VERSION_CODES.O)
public class WeeklyTracker extends CrowdLevel{

    private final LocalDate dateCreated;

    private HashMap<DayOfWeek, DailyTracker> weeklyHistoricalData;

    WeeklyTracker()
    {
        dateCreated = LocalDate.now();

        weeklyHistoricalData = new HashMap<>();

        for (DayOfWeek d: DayOfWeek.values())
        {
            weeklyHistoricalData.put(d, new DailyTracker());
        }
    }

    public LocalDate getDateCreated() { return dateCreated; }

    public void setWeeklyHistoricalData(DayOfWeek currentDay, int currentHour)
    {
        DailyTracker dailyTrackerToUpdate = weeklyHistoricalData.get(currentDay);
        dailyTrackerToUpdate.setHourlyHistoricalData(currentHour, hourlyHighestCapacity.get(currentHour));
    }

    public HashMap<DayOfWeek, DailyTracker> getWeeklyHistoricalData()
    {
        return weeklyHistoricalData;
    }


}
