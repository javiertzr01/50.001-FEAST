package com.example.feast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@RequiresApi(api = Build.VERSION_CODES.O)
public class HomePage extends AppCompatActivity {
    LinearLayout linearlayout; // declaration of the LinearLayout of activity_home_page.xml
    List<FNBEstablishment> fnbList;
    private final String sharedPrefFile = "com.example.android.mainsharedprefs";
    private SharedPreferences mPreferences;
    Set<String> favList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        getSupportActionBar().hide(); // hides the action bar, to display the toolbar (in activity_home_page.xml instead)

        ArrayList<FNBButton> fnbButtonArrayList = new ArrayList<>();
        linearlayout = findViewById(R.id.homePageLayout); // initialises the LinearLayout into linearlayout

        // data persistence
        mPreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);
        favList = mPreferences.getStringSet("favourites", Collections.emptySet());

        //singleton
        CreateEstablishments.getInstance();
        CreateEstablishments.setFavourites(favList);
        fnbList = CreateEstablishments.getList();

        // sort by
        Comparator<FNBEstablishment> chosenComparator = new IsFavoriteComparator();
        Collections.sort(fnbList, chosenComparator); // order fnb establishments based on isFavorite, then the chosen sorting method

        // creating fnbButton
        for (FNBEstablishment est : fnbList) {
            FNBButton fnbButton = new FNBButton(this);
            TextView emptySpace = new TextView(this); // for the empty space between each FNBButton instance
            fnbButton.setFNBEstablishmentName(est.name);
            fnbButton.setIsOpen(est.isOpen());
            fnbButton.setCapacity(est.crowdLevel.getCurrentCrowdLevelString(), est.crowdLevel.crowdPercentage);
            fnbButton.setWaitingTime(String.valueOf(est.crowdLevel.currentCrowdLevel));
            fnbButton.setIsFavourite(est.getIsFavorite());

            emptySpace.setTextSize(5);
            linearlayout.addView(fnbButton);
            linearlayout.addView(emptySpace);

            // add to list of fnbButtons
            fnbButtonArrayList.add(fnbButton);

            // for the intent
            if (est.name.equals("Canteen")) { // canteen button will go to CanteenPage
                setGoToCanteenPage(fnbButton, est);
            }

            else { // all the other non-canteen buttons will go to OthersInfoPage
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
                for(int i = 0; i < fnbButtonArrayList.toArray().length; i++) {
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

                    catch (NullPointerException e) {}
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferenceEditor = mPreferences.edit();
        preferenceEditor.clear();
        preferenceEditor.putStringSet("favourites",favList);
        preferenceEditor.apply();
    }

    // a bunch of helper methods below, for the intents
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

    public void goToOthersInfoPage(View view, FNBButton fnbButton, FNBEstablishment est) {
        Intent intent = new Intent(HomePage.this, OthersInfoPage.class);
        intent.putExtra("fnbEstablishmentName", fnbButton.getFnbEstablishmentName().getText().toString());
        intent.putExtra("waitingTime", fnbButton.getWaitingTime().getText().toString());
        intent.putExtra("capacity", fnbButton.getCapacity().getText().toString());
        intent.putExtra("isOpen", fnbButton.getIsOpen().getText().toString());
        intent.putExtra("isFavourite", fnbButton.getIsFavourite());
        intent.putExtra("isHalal", est.getIsHalal());
        intent.putExtra("openHour", est.openHour);
        intent.putExtra("closeHour", est.closeHour);
        intent.putExtra("openMin", est.openMin);
        intent.putExtra("closeMin", est.closeMin);
        intent.putExtra("openSec", est.openSec);
        intent.putExtra("closeSec", est.closeSec);
        intent.putExtra("description", est.description);
        // TODO put the menu and prices into the intent
        startActivity(intent);
    }

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

    public void goToCanteenPage(View view, FNBButton fnbButton, FNBEstablishment est) {
        Intent intent = new Intent(HomePage.this, CanteenPage.class);
        intent.putExtra("waitingTime", fnbButton.getWaitingTime().getText().toString());
        intent.putExtra("capacity", fnbButton.getCapacity().getText().toString());
        intent.putExtra("isOpen", fnbButton.getIsOpen().getText().toString());
        intent.putExtra("isFavourite", fnbButton.getIsFavourite());
        intent.putExtra("isHalal", est.getIsHalal());
        intent.putExtra("openHour", est.openHour);
        intent.putExtra("closeHour", est.closeHour);
        intent.putExtra("openMin", est.openMin);
        intent.putExtra("closeMin", est.closeMin);
        intent.putExtra("openSec", est.openSec);
        intent.putExtra("closeSec", est.closeSec);
        intent.putExtra("description", est.description);
        // TODO put the menu and prices into the intent
        startActivity(intent);
    }
}
