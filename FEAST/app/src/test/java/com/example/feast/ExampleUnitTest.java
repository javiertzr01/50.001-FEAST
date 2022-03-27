package com.example.feast;

import org.junit.Test;

import static org.junit.Assert.*;

import java.time.DayOfWeek;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void FNBEstablishmentDefaultCheck(){
        FNBEstablishment default_ = new FNBEstablishment();
        assertEquals("Close" , default_.isOpen());
    }
    @Test
    public void CreateFNBEstablishmentCheck(){
        FNBEstablishment creation = new FNBEstablishment(10, "creation", "00:00:00", "23:59:59", "24/7");
        System.out.println(creation.openHour);
        assertEquals(10, creation.maxCapacity);
        assertEquals("creation", creation.name);
        assertEquals("00", creation.openHour);
        assertEquals("00", creation.openMin);
        assertEquals("00", creation.openSec);
        assertEquals("23", creation.closeHour);
        assertEquals("59", creation.closeMin);
        assertEquals("59", creation.closeSec);
        assertEquals("24/7", creation.description);
    }
    @Test
    public void FNBEstablishmentSetOpeningClosingCheck(){
        FNBEstablishment default_ = new FNBEstablishment();
        default_.setOpeningClosingTime("00:00:00", "23:59:59");
        assertEquals("00", default_.openHour);
        assertEquals("00", default_.openMin);
        assertEquals("00", default_.openSec);
        assertEquals("23", default_.closeHour);
        assertEquals("59", default_.closeMin);
        assertEquals("59", default_.closeSec);
    }

    @Test
    public void FNBEstablishmentsetDaysOpenCheck() throws Exception {
        FNBEstablishment default_ = new FNBEstablishment();
        default_.setOpeningClosingTime("00:00:00", "23:59:59");
        default_.setDaysOpen("weekend");
        assertEquals(true , default_.daysOpen.get(DayOfWeek.SUNDAY));
        assertEquals(true , default_.daysOpen.get(DayOfWeek.SATURDAY));
        assertEquals(false , default_.daysOpen.get(DayOfWeek.MONDAY));
        assertEquals(false , default_.daysOpen.get(DayOfWeek.TUESDAY));
        assertEquals(false , default_.daysOpen.get(DayOfWeek.WEDNESDAY));
        assertEquals(false , default_.daysOpen.get(DayOfWeek.THURSDAY));
        assertEquals(false , default_.daysOpen.get(DayOfWeek.FRIDAY));
    }
    @Test
    public void FNBEstablishmentOpenCheck() throws Exception {
        FNBEstablishment default_ = new FNBEstablishment();
        default_.setOpeningClosingTime("00:00:00", "23:59:59");
        default_.setDaysOpen("weekend");

        assertEquals("Open", default_.isOpen());
    }

    @Test
    public void FNBEstablishmentDayCloseCheck() throws Exception {
        FNBEstablishment default_ = new FNBEstablishment();
        default_.setOpeningClosingTime("00:00:00", "23:59:59");
        default_.setDaysOpen("weekday");

        assertEquals("Close", default_.isOpen());
    }

    @Test
    public void FNBEstablishmentTimeCloseCheck() throws Exception {
        FNBEstablishment default_ = new FNBEstablishment();
        default_.setOpeningClosingTime("14:00:00", "14:01:00");
        default_.setDaysOpen("weekend");

        assertEquals("Close", default_.isOpen());
    }
}