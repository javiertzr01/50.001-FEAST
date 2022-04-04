package com.example.feast;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;

import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@RequiresApi(api = Build.VERSION_CODES.O)
public class WeeklyTracker{

    private static final String[] dbDayReference = {"Mon", "Tue", "Wed", "Thurs", "Fri", "Sat", "Sun"};
    private static final String[] dbHourReference = {"0000", "0100", "0200", "0300", "0400", "0500", "0600", "0700", "0800", "0900", "1000", "1100", "1200",
                                                        "1300", "1400", "1500", "1600", "1700", "1800", "1900", "2000", "2100", "2200", "2300"};

    //Hashmap that contains the day (Mon-Sun) as key and an Integer ArrayList containing
    //hourly maximum capacity values from Firebase. Values in ArrayList are stored based on
    //chronological order (index 0 = capacity at 0000, index 1 = capacity at 0100 ...)
    HashMap<String, ArrayList<Integer>> weeklyTrackerTable;

    WeeklyTracker()
    {
        this.weeklyTrackerTable = new HashMap<>();
    }

    public HashMap<String, ArrayList<Integer>> setWeeklyTrackerTable(DataSnapshot ds, FNBEstablishment fnb)
    {
            for(DataSnapshot dsChild: ds.child(fnb.name).getChildren())
            {
                ArrayList<Integer> hourlyMaxCap = new ArrayList<>();
                if (Arrays.asList(dbDayReference).contains(dsChild.getKey())) {
                    for (DataSnapshot dayChild : dsChild.getChildren())
                    {
                        //System.out.println(dsChild.getKey());
                        hourlyMaxCap.add(dayChild.getValue(Integer.class));
                    }
                    weeklyTrackerTable.put(dsChild.getKey(), hourlyMaxCap);
                }
            }
        return weeklyTrackerTable;
    }

    public HashMap<String, ArrayList<Integer>> getWeeklyTrackerTable()
    {
        return this.weeklyTrackerTable;
    }

}
