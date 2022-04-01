package com.example.feast;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;

@RequiresApi(api = Build.VERSION_CODES.O)
public class WeeklyTracker extends FNBEstablishment{

    private static final String[] dbDayReference = {"Mon", "Tue", "Wed", "Thurs", "Fri", "Sat", "Sun"};
    private static final String[] dbHourReference = {"0000", "0100", "0200", "0300", "0400", "0500", "0600", "0700", "0800", "0900", "1000", "1100", "1200",
                                                        "1300", "1400", "1500", "1600", "1700", "1800", "1900", "2000", "2100", "2200", "2300"};

    HashMap<String, Integer[]> weeklyTrackerTable;
    DataSnapshot ds;

    WeeklyTracker(DataSnapshot ds)
    {
        this.ds = ds;
        this.weeklyTrackerTable = setWeeklyTrackerTable(this.ds);
    }

    public HashMap<String, Integer[]> setWeeklyTrackerTable(DataSnapshot ds)
    {
        for (String day: dbDayReference)
        {
            ArrayList<Integer> hourlyCapacityValues = new ArrayList<>();
            for (String hour: dbHourReference)
            {
                int currentHourlyMaxCapacity = ds.child("people_count").child(super.name).child(day).child(hour).getValue(Integer.class);
                hourlyCapacityValues.add(currentHourlyMaxCapacity);
            }
            Integer[] hourlyCapacityValuesArray = new Integer[hourlyCapacityValues.size()];
            hourlyCapacityValuesArray = hourlyCapacityValues.toArray(hourlyCapacityValuesArray);
            weeklyTrackerTable.put(day, hourlyCapacityValuesArray);
        }

        return weeklyTrackerTable;
    }

    public HashMap<String, Integer[]> getWeeklyTrackerTable()
    {
        return this.weeklyTrackerTable;
    }

}
