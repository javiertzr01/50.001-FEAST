package com.example.feast;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.time.*;
import java.util.HashMap;

public class CrowdLevel extends FNBEstablishment{

    static final String[] crowdLevelsString = {"Not Crowded", "A Little Crowded", "Somewhat Crowded", "Very Crowded", "Full"};
    static final Double[] crowdLevelsThresholdDouble = {0.2, 0.5, 0.8, 1.0};

    int currentCapacity;
    double crowdPercentage;
    String currentCrowdLevel;
    HashMap<Integer, Integer> hourlyHighestCapacity;

    @RequiresApi(api = Build.VERSION_CODES.O)
    CrowdLevel()
    {
        this.currentCapacity = 1;
        this.crowdPercentage = 1;
        this.currentCrowdLevel = "Not Crowded";
        this.hourlyHighestCapacity = new HashMap<>();
        {
            hourlyHighestCapacity.put(LocalTime.now().getHour(), this.currentCapacity);
        }
    }

    public double getCurrentCapacity()
    {
        return this.currentCapacity;
    }

    public HashMap<Integer, Integer> getHourlyHighestCapacity()
    {
        return this.hourlyHighestCapacity;
    }

    public double calculateCrowdPercentage()
    {
        this.crowdPercentage = this.currentCapacity/this.maxCapacity;
        return this.crowdPercentage;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void refreshInfo(DataSnapshot ds)
    {
        //Get value of current capacity of store from database
        this.currentCapacity = ds.child("people_count").child(super.name).child("currentCapacity").getValue(Integer.class);
        //Get value of hourly highest capacity of store from database
        if (hourlyHighestCapacity.containsKey(LocalTime.now().getHour()))
        {
            if (hourlyHighestCapacity.get(LocalTime.now().getHour()) < this.currentCapacity)
            {
                hourlyHighestCapacity.put(LocalTime.now().getHour(), this.currentCapacity);
            }
        }
    }

    public String getCurrentCrowdLevel()
    {
        if (this.calculateCrowdPercentage() <= crowdLevelsThresholdDouble[0])
        {
            return crowdLevelsString[0];
        }
        else if (this.calculateCrowdPercentage() > crowdLevelsThresholdDouble[0] && this.calculateCrowdPercentage() <= crowdLevelsThresholdDouble[1])
        {
            return crowdLevelsString[1];
        }
        else if (this.calculateCrowdPercentage() > crowdLevelsThresholdDouble[1] && this.calculateCrowdPercentage() <= crowdLevelsThresholdDouble[2])
        {
            return crowdLevelsString[2];
        }
        else if (this.calculateCrowdPercentage() > crowdLevelsThresholdDouble[2] && this.calculateCrowdPercentage() <= crowdLevelsThresholdDouble[3])
        {
            return crowdLevelsString[3];
        }
        else
        {
            return crowdLevelsString[4];
        }
    }

}
