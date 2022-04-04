package com.example.feast;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.time.*;
import java.util.HashMap;

public class CrowdLevel extends FNBEstablishment{

    static final String[] crowdLevelsString = {"Not Crowded", "Crowded", "Very Crowded", "Full"};
    static final Integer[] crowdLevelsInteger = {0, 1, 2 ,3};
    static final Double[] crowdLevelsThresholdDouble = {0.4, 0.7, 1.0};

    double currentCapacity;
    double crowdPercentage;
    String currentCrowdLevel;

    @RequiresApi(api = Build.VERSION_CODES.O)
    CrowdLevel()
    {
        this.currentCapacity = 1;
        this.crowdPercentage = 1;
        this.currentCrowdLevel = "Not Crowded";
    }

    public double getCurrentCapacity()
    {
        return this.currentCapacity;
    }
    public void setCurrentCapacity(DataSnapshot ds, FNBEstablishment fnb)
    {
        //Get value of current capacity of store from database
        this.currentCapacity = ds.child(fnb.name).child("currentCapacity").getValue(Integer.class);
    }


    public void setCrowdPercentage(FNBEstablishment fnb)
    {
        this.crowdPercentage = this.currentCapacity/ fnb.maxCapacity;
    }

    public double getCrowdPercentage()
    {
        return this.crowdPercentage;
    }

    public String getCurrentCrowdLevelString()
    {
        if (this.getCrowdPercentage() <= crowdLevelsThresholdDouble[0])
        {
            return crowdLevelsString[0];
        }
        else if (this.getCrowdPercentage() > crowdLevelsThresholdDouble[0] && this.getCrowdPercentage() <= crowdLevelsThresholdDouble[1])
        {
            return crowdLevelsString[1];
        }
        else if (this.getCrowdPercentage() > crowdLevelsThresholdDouble[1] && this.getCrowdPercentage() <= crowdLevelsThresholdDouble[2])
        {
            return crowdLevelsString[2];
        }
        else
        {
            return crowdLevelsString[3];
        }
    }

    public Integer getCurrentCrowdLevelInteger()
    {
        if (this.getCrowdPercentage() <= crowdLevelsThresholdDouble[0])
        {
            return crowdLevelsInteger[0];
        }
        else if (this.getCrowdPercentage() > crowdLevelsThresholdDouble[0] && this.getCrowdPercentage() <= crowdLevelsThresholdDouble[1])
        {
            return crowdLevelsInteger[1];
        }
        else if (this.getCrowdPercentage() > crowdLevelsThresholdDouble[1] && this.getCrowdPercentage() <= crowdLevelsThresholdDouble[2])
        {
            return crowdLevelsInteger[2];
        }
        else
        {
            return crowdLevelsInteger[3];
        }
    }
}
