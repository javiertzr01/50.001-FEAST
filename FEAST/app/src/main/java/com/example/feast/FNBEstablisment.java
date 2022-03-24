package com.example.feast;

import java.util.Date;

public class FNBEstablisment {
    int maxCapacity;
    String name;
    String openingHour;
    String closingHour;
    String description;


    FNBEstablisment()
    {
        maxCapacity = 1;
        name = "defaultFNB";
        openingHour = "08:00:00";
        closingHour = "18:00:00";
        String description = "A place to eat";
    }

    FNBEstablisment(int maxCapacity, String name, String openingHour, String closingHour, String description)
    {
        this.maxCapacity = maxCapacity;
        this.name = name;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.description = description;
    }

    public void setOpeningClosingTime(String newOpeningHour, String newClosingHour)
    {
        this.openingHour = newOpeningHour;
        this.closingHour = newClosingHour;

    }

    public void setDescription(String newDescription)
    {
        this.description = newDescription;
    }

    public String IsOpen()
    {
        //Check if current time is within opening and closing hours
        return "";
    }

}
