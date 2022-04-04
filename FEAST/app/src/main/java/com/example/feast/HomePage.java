package com.example.feast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

@RequiresApi(api = Build.VERSION_CODES.O)
public class HomePage extends AppCompatActivity {
    LinearLayout linearlayout; // declaration of the LinearLayout of activity_home_page.xml

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        linearlayout = findViewById(R.id.homePageLayout); // initialises the LinearLayout into linearlayout

        int n = 5;

        for (int i = 0; i <= n; i++){
            if (i == 0){
                TextView firstEmptySpace = new TextView(this); // for the empty space above the first FNB Button
                firstEmptySpace.setTextSize(2);
                linearlayout.addView(firstEmptySpace);
            }

            FNBButton fnbButton = new FNBButton(this);
            TextView emptySpace = new TextView(this); // for the empty space between each FNBButton instance

            if (i == 1){
                fnbButton.setFNBEstablishmentName("D'Star Bistro");
                fnbButton.setOpeningHours("10am to 10pm");
            }

            if (i == 3){
                fnbButton.setFNBEstablishmentName("GomGom");
                fnbButton.setCapacity("200%");
            }

            emptySpace.setTextSize(5);
            linearlayout.addView(fnbButton);
            linearlayout.addView(emptySpace);

            if (i == n){
                TextView lastEmptySpace = new TextView(this); // for the empty space below the last FNB Button
                lastEmptySpace.setTextSize(2);
                linearlayout.addView(lastEmptySpace);
            }
        }

        /* Testing for CrowdLevel and WeeklyTracker
           Attributes of Crowdlevel and WeeklyTracker must be set before they can be get
           Attributes also can only be set in onDataChange as values are retrieved from Firebase

        WeeklyTracker canteenTracker = new WeeklyTracker();
        CrowdLevel canteenCrowd = new CrowdLevel();
        FNBEstablishment canteen = new FNBEstablishment(10, false, "Canteen", "00:00:00", "23:59:59", "place");
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("people_count");
        db.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                canteenTracker.setWeeklyTrackerTable(snapshot, canteen);
                System.out.println(canteenTracker.getWeeklyTrackerTable());
                canteenCrowd.setCurrentCapacity(snapshot, canteen);
                System.out.println(canteenCrowd.getCurrentCapacity());
                canteenCrowd.setCrowdPercentage(canteen);
                System.out.println(canteenCrowd.getCrowdPercentage());
                System.out.println(canteen.maxCapacity);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


    }

    // a bunch of helper methods below

    // TODO
    // helper method to go to OthersInfoPage upon clicking the FNBButton
    public void setGoToOthersInfoPage(FNBButton fnbButton){
        fnbButton.getFnbEstablishmentName().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToOthersInfoPage(view);
            }
        });

        fnbButton.getWaitingTime().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToOthersInfoPage(view);
            }
        });

        fnbButton.getCapacity().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToOthersInfoPage(view);
            }
        });

        fnbButton.getOpeningHours().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToOthersInfoPage(view);
            }
        });

        fnbButton.getFnbPhoto().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToOthersInfoPage(view);
            }
        });

        fnbButton.getDotText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToOthersInfoPage(view);
            }
        });

        fnbButton.getOpeningHoursText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToOthersInfoPage(view);
            }
        });

        fnbButton.getHistoricalTrendPhoto().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
                //goToHistoricTrendsPage(view);
            }
        });

        fnbButton.getHistoryText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
                //goToHistoricTrendsPage(view);
            }
        });

        fnbButton.getWaitingTimePhoto().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToOthersInfoPage(view);
            }
        });

        fnbButton.getFnbButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToOthersInfoPage(view);
            }
        });
    }

    // TODO
//    public void goToOthersInfoPage(View view){
//        Intent intent = new Intent(HomePage.this, OthersInfoPage.class);
//        startActivity(intent);

    // TODO
    // helper method to go to CanteenPage upon clicking the FNBButton
    public void setGoToCanteenPage(FNBButton fnbButton){
        fnbButton.getFnbEstablishmentName().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToCanteenPage(view);
            }
        });

        fnbButton.getWaitingTime().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToCanteenPage(view);
            }
        });

        fnbButton.getCapacity().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToCanteenPage(view);
            }
        });

        fnbButton.getOpeningHours().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToCanteenPage(view);
            }
        });

        fnbButton.getFnbPhoto().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToCanteenPage(view);
            }
        });

        fnbButton.getDotText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToCanteenPage(view);
            }
        });

        fnbButton.getOpeningHoursText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToCanteenPage(view);
            }
        });

        fnbButton.getHistoricalTrendPhoto().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
                //goToHistoricTrendsPage(view);
            }
        });

        fnbButton.getHistoryText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
                //goToHistoricTrendsPage(view);
            }
        });

        fnbButton.getWaitingTimePhoto().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToCanteenPage(view);
            }
        });

        fnbButton.getFnbButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToCanteenPage(view);
            }
        });
    }

    // TODO
//    public void goToCanteenPage(View view){
//        Intent intent = new Intent(HomePage.this, CanteenPage.class);
//        startActivity(intent);
}