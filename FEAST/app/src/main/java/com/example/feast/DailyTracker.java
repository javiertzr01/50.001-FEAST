package com.example.feast;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalTime;
import java.util.HashMap;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DailyTracker extends WeeklyTracker{

    private HashMap<Integer, Integer> hourlyHistoricalData;

    DailyTracker()
    {
        this.hourlyHistoricalData = new HashMap<>();
        for (int i = Integer.parseInt(super.openHour); i <= Integer.parseInt(super.closeHour); i++)
        {
            hourlyHistoricalData.put(i, 0);
        }

    }

    public void setHourlyHistoricalData(int currentHour, Integer updatedCapacity)
    {
        this.hourlyHistoricalData.put(currentHour, updatedCapacity);
    }
    
    public int getHourlyHistoricalData(LocalTime time)
    {
        return this.hourlyHistoricalData.get(time);
    }

}
