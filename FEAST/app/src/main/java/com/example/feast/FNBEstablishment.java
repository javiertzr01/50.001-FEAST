package com.example.feast;

import java.time.LocalDateTime;

import java.util.Date;

public class FNBEstablishment {
    int maxCapacity;
    String name;
    String openingHour;
    String closingHour;
    String description;

    FNBEstablishment()
    {
        maxCapacity = 1;
        name = "defaultFNB";
        openingHour = "08:00:00";
        closingHour = "18:00:00";
        String description = "A place to eat";
    }

    FNBEstablishment(int maxCapacity, String name, String openingHour, String closingHour, String description)
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

    public String isOpen()
    {
        //TODO @Javier Implement a system using java.time to check if the F&B est
        // is open (get current time & day, then  consider the opening hours,
        // closing hours, and day i.e. make sure its not saturday/sunday

        return "";
    }

}
