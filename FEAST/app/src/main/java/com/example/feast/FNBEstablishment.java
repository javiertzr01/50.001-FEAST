package com.example.feast;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.*;
import java.util.HashMap;

public class FNBEstablishment{
    private int maxCapacity;
    private boolean isFavorite;
    private boolean isHalal;
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
    private FNBEstablishment(FNBBuilder fnBbuilder){
        this.maxCapacity = fnBbuilder.maxCapacity;
        this.isFavorite = fnBbuilder.isFavorite;
        this.isHalal = fnBbuilder.isHalal;
        this.name = fnBbuilder.name;

        this.openHour = fnBbuilder.openHour;
        this.openMin = fnBbuilder.openMin;
        this.openSec = fnBbuilder.openSec;

        this.closeHour = fnBbuilder.closeHour;
        this.closeMin = fnBbuilder.closeMin;
        this.closeSec = fnBbuilder.closeSec;


        this.description = fnBbuilder.description;
        for (DayOfWeek d: DayOfWeek.values()){      //From Sunday to Saturday
            daysOpen.put(d, true);
        }
        this.crowdLevel = new CrowdLevel();
        this.weeklyTracker = new WeeklyTracker();
    }

    static class FNBBuilder {
        private int maxCapacity;
        private boolean isFavorite;
        private boolean isHalal;
        private String name;
        private String openHour;
        private String closeHour;
        private String openMin;
        private String closeMin;
        private String openSec;
        private String closeSec;
        private String description;

        FNBBuilder(){}

        public FNBBuilder setMaxCapacity(int maxCapacity) {
            this.maxCapacity = maxCapacity;
            return this;
        }

        public FNBBuilder setFavorite(boolean favorite) {
            isFavorite = favorite;
            return this;
        }

        public FNBBuilder setHalal(boolean halal) {
            isHalal = halal;
            return this;
        }

        public FNBBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public FNBBuilder setOpeningHour(String openingHour) {
            char[] openingHourCharArray = openingHour.toCharArray();
            char[] openHourCharArray = {openingHourCharArray[0], openingHourCharArray[1]};
            char[] openMinCharArray = {openingHourCharArray[3], openingHourCharArray[4]};
            char[] openSecCharArray = {openingHourCharArray[6], openingHourCharArray[7]};
            this.openHour = String.valueOf(openHourCharArray);
            this.openMin = String.valueOf(openMinCharArray);
            this.openSec = String.valueOf(openSecCharArray);
            return this;
        }

        public FNBBuilder setClosingHour(String closingHour) {
            char[] closingHourCharArray = closingHour.toCharArray();
            char[] closeHourCharArray = {closingHourCharArray[0], closingHourCharArray[1]};
            char[] closeMinCharArray = {closingHourCharArray[3], closingHourCharArray[4]};
            char[] closeSecCharArray = {closingHourCharArray[6], closingHourCharArray[7]};
            this.closeHour = String.valueOf(closeHourCharArray);
            this.closeMin = String.valueOf(closeMinCharArray);
            this.closeSec = String.valueOf(closeSecCharArray);
            return this;
        }

        public FNBBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public FNBEstablishment build(){
            return new FNBEstablishment(this);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)       //Make sure version is high enough (Oreo/Android 8.0)
    FNBEstablishment()
    {
        maxCapacity = 1;
        isFavorite = false;
        isHalal = false;
        name = "defaultFNB";
        openHour = "08";
        openMin = "00";
        openSec = "00";
        closeHour = "18";
        closeMin = "00";
        closeSec = "00";
        String description = "A place to eat";
        for (DayOfWeek d: DayOfWeek.values()){      //From Sunday to Saturday
            daysOpen.put(d, false);
        }
        crowdLevel = new CrowdLevel();
        weeklyTracker = new WeeklyTracker();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    FNBEstablishment(Integer maxCapacity, boolean isFavorite, boolean isHalal, String name, String openingHour, String closingHour, String description) //openingHour and closingHour must be in "hh:mm" format
    {
        this.maxCapacity = maxCapacity;
        this.isFavorite = isFavorite;
        this.isHalal = isHalal;
        this.name = name;
        this.description = description;
        for (DayOfWeek d: DayOfWeek.values()){      //From Sunday to Saturday
            daysOpen.put(d, true);
        }
        this.crowdLevel = new CrowdLevel();
        this.weeklyTracker = new WeeklyTracker();

        char[] openingHourCharArray = openingHour.toCharArray();
        char[] openHourCharArray = {openingHourCharArray[0], openingHourCharArray[1]};
        char[] openMinCharArray = {openingHourCharArray[3], openingHourCharArray[4]};
        char[] openSecCharArray = {openingHourCharArray[6], openingHourCharArray[7]};
        this.openHour = String.valueOf(openHourCharArray);
        this.openMin = String.valueOf(openMinCharArray);
        this.openSec = String.valueOf(openSecCharArray);

        char[] closingHourCharArray = closingHour.toCharArray();
        char[] closeHourCharArray = {closingHourCharArray[0], closingHourCharArray[1]};
        char[] closeMinCharArray = {closingHourCharArray[3], closingHourCharArray[4]};
        char[] closeSecCharArray = {closingHourCharArray[6], closingHourCharArray[7]};
        this.closeHour = String.valueOf(closeHourCharArray);
        this.closeMin = String.valueOf(closeMinCharArray);
        this.closeSec = String.valueOf(closeSecCharArray);
    }

    public void setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; }

    public int getMaxCapacity() { return this.maxCapacity; }

    public void invIsFavorite(boolean isFavorite) { this.isFavorite = !this.isFavorite; }

    public void setIsFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public boolean getIsFavorite() { return this.isFavorite; }

    public boolean getIsHalal(){return this.isHalal;}

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

   

//Future Improvements: (If we not lazy)
// does not account for specific dates closed
// does not account for different closing times on different days
}
