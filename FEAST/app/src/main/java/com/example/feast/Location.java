package com.example.feast;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;

abstract class Location {
    String name;
    String openHour;
    String closeHour;
    String openMin;
    String closeMin;
    String openSec;
    String closeSec;
    String description;
    HashMap<DayOfWeek, Boolean> daysOpen = new HashMap<>();
    CrowdLevel crowdLevel;
    WeeklyTracker weeklyTracker;

    @RequiresApi(api = Build.VERSION_CODES.O)       //Make sure version is high enough (Oreo/Android 8.0)
    public String isOpen()
    {
        //Getting Local Date
        LocalDate localDate = LocalDate.now();

        //Getting Local Time
        LocalTime localTime = LocalTime.now();

        //Getting Local DateTime
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

        //Getting Day
        DayOfWeek day = localDate.getDayOfWeek();

        //Check Day
        boolean checkDay = daysOpen.get(day);

        //Check Time
        LocalTime open = LocalTime.of(Integer.valueOf(openHour), Integer.valueOf(openMin), Integer.valueOf(openSec));
        LocalTime close = LocalTime.of(Integer.valueOf(closeHour), Integer.valueOf(closeMin), Integer.valueOf(closeSec));
        boolean inOpeningHours = (localTime.isAfter(open) && localTime.isBefore(close));

        if (checkDay && inOpeningHours){
            return "Open";
        }

        return "Closed";
    }

    public void setOpeningClosingTime(String newOpeningHour, String newClosingHour)     //must be in "hh:mm" format
    {
        char[] openingHourCharArray = newOpeningHour.toCharArray();
        char[] openHourCharArray = {openingHourCharArray[0], openingHourCharArray[1]};
        char[] openMinCharArray = {openingHourCharArray[3], openingHourCharArray[4]};
        char[] openSecCharArray = {openingHourCharArray[6], openingHourCharArray[7]};
        this.openHour = String.valueOf(openHourCharArray);
        this.openMin = String.valueOf(openMinCharArray);
        this.openSec = String.valueOf(openSecCharArray);

        char[] closingHourCharArray = newClosingHour.toCharArray();
        char[] closeHourCharArray = {closingHourCharArray[0], closingHourCharArray[1]};
        char[] closeMinCharArray = {closingHourCharArray[3], closingHourCharArray[4]};
        char[] closeSecCharArray = {closingHourCharArray[6], closingHourCharArray[7]};
        this.closeHour = String.valueOf(closeHourCharArray);
        this.closeMin = String.valueOf(closeMinCharArray);
        this.closeSec = String.valueOf(closeSecCharArray);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)       //Make sure version is high enough (Oreo/Android 8.0)
    public void setDaysOpen(boolean sunday, boolean monday, boolean tuesday, boolean wednesday, boolean thursday, boolean friday, boolean saturday){
        daysOpen.put(DayOfWeek.SUNDAY, sunday);
        daysOpen.put(DayOfWeek.MONDAY, monday);
        daysOpen.put(DayOfWeek.TUESDAY, tuesday);
        daysOpen.put(DayOfWeek.WEDNESDAY, wednesday);
        daysOpen.put(DayOfWeek.THURSDAY, thursday);
        daysOpen.put(DayOfWeek.FRIDAY, friday);
        daysOpen.put(DayOfWeek.SATURDAY, saturday);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)       //Make sure version is high enough (Oreo/Android 8.0)
    public void setDaysOpen(String presets) throws Exception {      //presets are everyday, weekday and weekend
        try {
            if (presets.equalsIgnoreCase("EVERYDAY")) {
                this.setDaysOpen(true, true, true, true, true, true, true);
            } else if (presets.equalsIgnoreCase("WEEKDAY")) {
                this.setDaysOpen(false, true, true, true, true, true, false);
            } else if (presets.equalsIgnoreCase("WEEKEND")) {
                this.setDaysOpen(true, false, false, false, false, false, true);
            } else {
                throw new Exception();
            }
        }
        catch(Exception ex){
            System.out.println("Error, presets are everyday, weekday or weekend");
        }
    }
}
