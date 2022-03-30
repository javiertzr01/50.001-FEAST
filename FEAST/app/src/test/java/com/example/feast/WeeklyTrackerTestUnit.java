package com.example.feast;

import org.junit.Test;

import static org.junit.Assert.*;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.DayOfWeek;

public class WeeklyTrackerTestUnit {

    final DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    @Test
    public void WeeklyTrackerDefaultCheck()
    {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //WeeklyTracker defaultWeeklyTracker = new WeeklyTracker(snapshot);
                //System.out.println(defaultWeeklyTracker.getWeeklyTrackerTable());
                String msg = snapshot.child("people_count").child("Canteen").child("Mon").child("0000").getValue(String.class);
                System.out.println(msg);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /*@Test
    public void DailyTrackerCheck()
    {
        DailyTracker defaultDailyTracker = new DailyTracker();
    }*/

}
