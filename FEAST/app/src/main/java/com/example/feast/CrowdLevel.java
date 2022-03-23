package com.example.feast;

import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

public class CrowdLevel extends FNBEstablisment{

    static final String[] crowdLevelsString = {"Not Crowded", "A Little Crowded", "Somewhat Crowded", "Very Crowded", "Full"};
    static final Double[] crowdLevelsDouble = {0.2, 0.5, 0.8, 1.0};

    double currentCapacity;
    double hourlyHighestCapacity;
    double crowdPercentage;
    String currentCrowdLevel;

    CrowdLevel()
    {
        this.currentCapacity = 1;
        this.hourlyHighestCapacity = 1;
        this.crowdPercentage = 1;
        this.currentCrowdLevel = "Not Crowded";
    }

    CrowdLevel(int currentCapacity, int hourlyHighestCapacity, double crowdPercentage, String currentCrowdLevel)
    {
        this.currentCapacity = 1;
        this.hourlyHighestCapacity = 1;
        this.crowdPercentage = 1;
    }

    public double getCurrentCapacity()
    {
        return this.currentCapacity;
    }

    public double getHourlyHighestCapacity()
    {
        return this.hourlyHighestCapacity;
    }

    public double calculateCrowdPercentage()
    {
        this.crowdPercentage = this.currentCapacity/this.maxCapacity;
        return this.crowdPercentage;
    }

    public void refreshInfo(DatabaseReference db)
    {
        //Get value of current capacity of store from database
        //Get value of hourly highest capacity of store from database
    }

    public String getCurrentCrowdLevel()
    {
        if (this.calculateCrowdPercentage() <= crowdLevelsDouble[0])
        {
            return crowdLevelsString[0];
        }
        else if (this.calculateCrowdPercentage() > crowdLevelsDouble[0] && this.calculateCrowdPercentage() <= crowdLevelsDouble[1])
        {
            return crowdLevelsString[1];
        }
        else if (this.calculateCrowdPercentage() > crowdLevelsDouble[1] && this.calculateCrowdPercentage() <= crowdLevelsDouble[2])
        {
            return crowdLevelsString[2];
        }
        else if (this.calculateCrowdPercentage() > crowdLevelsDouble[2] && this.calculateCrowdPercentage() <= crowdLevelsDouble[3])
        {
            return crowdLevelsString[3];
        }
        else
        {
            return crowdLevelsString[4];
        }
    }


}
