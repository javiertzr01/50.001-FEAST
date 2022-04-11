package com.example.feast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class HomePage extends AppCompatActivity {
    LinearLayout linearlayout; // declaration of the LinearLayout of activity_home_page.xml
    List<FNBEstablishment> fnbList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ArrayList<FNBButton> fnbButtonArrayList = new ArrayList<>();

        linearlayout = findViewById(R.id.homePageLayout); // initialises the LinearLayout into linearlayout

        //singleton
        CreateEstablishments.getInstance();
        fnbList = CreateEstablishments.getList();

        //TODO: choose sorting method
        //get the comparator
        Comparator<FNBEstablishment> chosenComparator = new IsFavoriteComparator();

        Collections.sort(fnbList, chosenComparator); // order fnb establishments based on isFavorite, then chosen sorting method

        for (FNBEstablishment est : fnbList) {
            System.out.println(est.name + est.crowdLevel.currentCapacity);
            FNBButton fnbButton = new FNBButton(this);
            TextView emptySpace = new TextView(this); // for the empty space between each FNBButton instance
            fnbButton.setFNBEstablishmentName(est.name);
            fnbButton.setIsOpen(est.isOpen());
            fnbButton.setCapacity(est.crowdLevel.getCurrentCrowdLevelString(), est.crowdLevel.crowdPercentage); // changed - Fuo En
//            fnbButton.setFNBPhoto(); - this might not be needed at all - Fuo En
            fnbButton.setWaitingTime(String.valueOf(est.crowdLevel.currentCrowdLevel)); // need to change this - Fuo En

            //TODO
//            fnbButton.setIsFavourite();
//            fnbButton.setIsHalal();
            fnbButton.setIsFavourite(est.getIsFavorite()); // changed - Fuo En
//            fnbButton.setIsOpen();

            emptySpace.setTextSize(5);
            linearlayout.addView(fnbButton);
            linearlayout.addView(emptySpace);

            //add to list
            fnbButtonArrayList.add(fnbButton);

            // for the intent
            if (est.name.equals("Canteen")) { // different intent for canteen button
                setGoToCanteenPage(fnbButton, est);
            } else {
                setGoToOthersInfoPage(fnbButton, est);
            }



        }

        TextView lastEmptySpace = new TextView(this); // for the empty space below the last FNB Button
        lastEmptySpace.setTextSize(2);
        linearlayout.addView(lastEmptySpace);

        final DatabaseReference db = FirebaseDatabase.getInstance().getReference("people_count");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(int i = 0; i < fnbButtonArrayList.toArray().length; i++)
                {
                    try {
                        FNBEstablishment fnb = fnbList.get(i);
                        FNBButton fnbButton = fnbButtonArrayList.get(i);
                        fnb.crowdLevel.setCurrentCapacity(snapshot, fnb);
                        fnb.crowdLevel.setCrowdPercentage(fnb);
                        fnb.crowdLevel.setWaitingTime();
                        System.out.println(fnb.name + fnb.crowdLevel.getCurrentCapacity());
                        fnbButton.setCapacity(fnb.crowdLevel.getCurrentCrowdLevelString(), fnb.crowdLevel.getCrowdPercentage());
                        fnbButton.setWaitingTime(String.valueOf(fnb.crowdLevel.getWaitingTime()));
                    }
                    catch (NullPointerException e)
                    {

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("HomePage", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("HomePage", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("HomePage", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("HomePage", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("HomePage", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("HomePage", "onRestart");
    }

    // a bunch of helper methods below

    // TODO
    // helper method to go to OthersInfoPage upon clicking the FNBButton
    public void setGoToOthersInfoPage(FNBButton fnbButton, FNBEstablishment est){
        fnbButton.getFnbEstablishmentName().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToOthersInfoPage(view, fnbButton, est);
            }
        });

        fnbButton.getWaitingTime().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToOthersInfoPage(view, fnbButton, est);
            }
        });

        fnbButton.getCapacity().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToOthersInfoPage(view, fnbButton, est);
            }
        });

        fnbButton.getIsOpen().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToOthersInfoPage(view, fnbButton, est);
            }
        });

        fnbButton.getFnbPhoto().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToOthersInfoPage(view, fnbButton, est);
            }
        });

        fnbButton.getDotText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToOthersInfoPage(view, fnbButton, est);
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
                goToOthersInfoPage(view, fnbButton, est);
            }
        });

        fnbButton.getFnbButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToOthersInfoPage(view, fnbButton, est);
            }
        });
    }

    // TODO
    public void goToOthersInfoPage(View view, FNBButton fnbButton, FNBEstablishment est) {
        Intent intent = new Intent(HomePage.this, OthersInfoPage.class);
        intent.putExtra("fnbEstablishmentName", fnbButton.getFnbEstablishmentNameString());
        intent.putExtra("waitingTime", fnbButton.getWaitingTimeString());
        intent.putExtra("capacity", fnbButton.getCapacityString());
        intent.putExtra("isOpen", fnbButton.getIsOpenString());
        intent.putExtra("isFavourite", fnbButton.getIsFavourite());
        intent.putExtra("isHalal", est.getIsHalal());
        intent.putExtra("openHour", est.openHour);
        intent.putExtra("closeHour", est.closeHour);
        intent.putExtra("openMin", est.openMin);
        intent.putExtra("closeMin", est.closeMin);
        intent.putExtra("openSec", est.openSec);
        intent.putExtra("closeSec", est.closeSec);
        intent.putExtra("description", est.description);
        // put the menu and prices into the intent
        // put the waiting time into the intent
        startActivity(intent);
    }

    // TODO
    // helper method to go to CanteenPage upon clicking the FNBButton
    public void setGoToCanteenPage(FNBButton fnbButton, FNBEstablishment est){
        fnbButton.getFnbEstablishmentName().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCanteenPage(view, fnbButton, est);
            }
        });

        fnbButton.getWaitingTime().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCanteenPage(view, fnbButton, est);
            }
        });

        fnbButton.getCapacity().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCanteenPage(view, fnbButton, est);
            }
        });

        fnbButton.getIsOpen().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCanteenPage(view, fnbButton, est);
            }
        });

        fnbButton.getFnbPhoto().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCanteenPage(view, fnbButton, est);
            }
        });

        fnbButton.getDotText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCanteenPage(view, fnbButton, est);
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
                goToCanteenPage(view, fnbButton, est);
            }
        });

        fnbButton.getFnbButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCanteenPage(view, fnbButton, est);
            }
        });
    }

    // TODO
    public void goToCanteenPage(View view, FNBButton fnbButton, FNBEstablishment est) {
        Intent intent = new Intent(HomePage.this, CanteenPage.class);
        intent.putExtra("waitingTime", fnbButton.getWaitingTimeString());
        intent.putExtra("capacity", fnbButton.getCapacityString());
        intent.putExtra("isOpen", fnbButton.getIsOpenString());
        intent.putExtra("isFavourite", fnbButton.getIsFavourite());
        intent.putExtra("isHalal", est.getIsHalal());
        intent.putExtra("openHour", est.openHour);
        intent.putExtra("closeHour", est.closeHour);
        intent.putExtra("openMin", est.openMin);
        intent.putExtra("closeMin", est.closeMin);
        intent.putExtra("openSec", est.openSec);
        intent.putExtra("closeSec", est.closeSec);
        intent.putExtra("description", est.description);
        // put the menu and prices into the intent
        // put the waiting time into the intent
        startActivity(intent);
    }
}
