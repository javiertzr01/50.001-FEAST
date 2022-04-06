package com.example.feast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class HomePage extends AppCompatActivity {
    LinearLayout linearlayout; // declaration of the LinearLayout of activity_home_page.xml

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        linearlayout = findViewById(R.id.homePageLayout); // initialises the LinearLayout into linearlayout

        //singleton
        CreateEstablishments.getInstance();
        List<FNBEstablishment> fnbList = CreateEstablishments.getList();

        //TODO: choose sorting method
        //get the comparator
        Comparator<FNBEstablishment> chosenComparator = new IsFavoriteComparator();

        Collections.sort(fnbList, chosenComparator); // order fnb establishments based on isFavorite, then chosen sorting method
        LocalDate localDate = LocalDate.now();
        String time = String.valueOf(localDate);
        Toast.makeText(getApplicationContext(), time, Toast.LENGTH_LONG).show();

        for (FNBEstablishment est : fnbList) {
            FNBButton fnbButton = new FNBButton(this);
            TextView emptySpace = new TextView(this); // for the empty space between each FNBButton instance
            fnbButton.setFNBEstablishmentName(est.name);
            fnbButton.setIsOpen(est.isOpen());
            fnbButton.setCapacity(est.crowdLevel.getCurrentCrowdLevelString() , est.crowdLevel.crowdPercentage); // changed - Fuo En
//            fnbButton.setFNBPhoto(); - this might not be needed at all - Fuo En
            fnbButton.setWaitingTime(String.valueOf(est.crowdLevel.currentCrowdLevel)); // need to change this - Fuo En

            //TODO
//            fnbButton.setIsFavourite();
//            fnbButton.setIsHalal();
            fnbButton.setIsFavourite(est.isFavorite); // changed - Fuo En
//            fnbButton.setIsOpen();

            // for the intent, on hold first
//            setGoToOthersInfoPage(fnbButton, est);

            emptySpace.setTextSize(5);
            linearlayout.addView(fnbButton);
            linearlayout.addView(emptySpace);

        }

        TextView lastEmptySpace = new TextView(this); // for the empty space below the last FNB Button
        lastEmptySpace.setTextSize(2);
        linearlayout.addView(lastEmptySpace);

        // old instantiation
//        for (int i = 0; i <= 5; i++){
//            if (i == 0){
//                TextView firstEmptySpace = new TextView(this); // for the empty space above the first FNB Button
//                firstEmptySpace.setTextSize(2);
//                linearlayout.addView(firstEmptySpace);
//            }
//
//            FNBButton fnbButton = new FNBButton(this);
//            TextView emptySpace = new TextView(this); // for the empty space between each FNBButton instance
//
//            if (i == 1){
//                fnbButton.setFNBEstablishmentName("D'Star Bistro");
//                fnbButton.setOpeningHours("10am to 10pm");
//            }
//
//            if (i == 3){
//                fnbButton.setFNBEstablishmentName("GomGom");
//                fnbButton.setCapacity("200%");
//            }
//
//            emptySpace.setTextSize(5);
//            linearlayout.addView(fnbButton);
//            linearlayout.addView(emptySpace);
//
//            if (i == 5){
//                TextView lastEmptySpace = new TextView(this); // for the empty space below the last FNB Button
//                lastEmptySpace.setTextSize(2);
//                linearlayout.addView(lastEmptySpace);
//            }
//        }

        /* Testing for CrowdLevel and WeeklyTracker attribute of FNBEstablishment
           Attributes of Crowdlevel and WeeklyTracker must be set before they can be get
           Attributes also can only be set in onDataChange as values are retrieved from Firebase

        FNBEstablishment canteen = new FNBEstablishment(5, false, "Canteen", "00:00:00", "23:59:59", "place");
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("people_count");
        db.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                canteen.weeklyTracker.setWeeklyTrackerTable(snapshot, canteen);
                System.out.println(canteen.weeklyTracker.getWeeklyTrackerTable());

                canteen.crowdLevel.setCurrentCapacity(snapshot, canteen);
                System.out.println(canteen.crowdLevel.getCurrentCapacity());

                canteen.crowdLevel.setCrowdPercentage(canteen);
                System.out.println(canteen.crowdLevel.getCrowdPercentage());
                System.out.println(canteen.crowdLevel.getCurrentCrowdLevelString());

                System.out.println(canteen.maxCapacity);

                canteen.crowdLevel.setWaitingTime();
                System.out.println(canteen.crowdLevel.getWaitingTime());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


    }

    // a bunch of helper methods below

    // TODO
    // helper method to go to OthersInfoPage upon clicking the FNBButton
    public void setGoToOthersInfoPage(FNBButton fnbButton, FNBEstablishment est){
        fnbButton.getFnbEstablishmentName().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToOthersInfoPage(view, fnbButton, est);
            }
        });

        fnbButton.getWaitingTime().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToOthersInfoPage(view, fnbButton, est);
            }
        });

        fnbButton.getCapacity().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToOthersInfoPage(view, fnbButton, est);
            }
        });

        fnbButton.getIsOpen().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToOthersInfoPage(view, fnbButton, est);
            }
        });

        fnbButton.getFnbPhoto().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToOthersInfoPage(view, fnbButton, est);
            }
        });

        fnbButton.getDotText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToOthersInfoPage(view, fnbButton, est);
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
//                goToOthersInfoPage(view, fnbButton, est);
            }
        });

        fnbButton.getFnbButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goToOthersInfoPage(view, fnbButton, est);
            }
        });
    }

    // TODO
//    public void goToOthersInfoPage(View view, FNBButton fnbButton, FNBEstablishment est) {
//        Intent intent = new Intent(HomePage.this, OthersInfoPage.class);
//        intent.putExtra("fnbEstablishmentName", fnbButton.getFnbEstablishmentNameString());
//        intent.putExtra("waitingTime", fnbButton.getWaitingTimeString());
//        intent.putExtra("capacity", fnbButton.getCapacityString());
//        intent.putExtra("isOpen", fnbButton.getIsOpenString());
//        intent.putExtra("isFavourite", fnbButton.getIsFavourite());
//        intent.putExtra("isHalal", est.isHalal);
//        intent.putExtra("openHour", est.openHour);
//        intent.putExtra("closeHour", est.closeHour);
//        intent.putExtra("openMin", est.openMin);
//        intent.putExtra("closeMin", est.closeMin);
//        intent.putExtra("openSec", est.openSec);
//        intent.putExtra("closeSec", est.closeSec);
//        intent.putExtra("description", est.description);
//        // put the menu and prices into the intent
//        // put the waiting time into the intent
//        startActivity(intent);
//    }

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

        fnbButton.getIsOpen().setOnClickListener(new View.OnClickListener() {
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