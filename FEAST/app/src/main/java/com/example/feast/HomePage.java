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
public class HomePage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    LinearLayout linearlayout; // declaration of the LinearLayout of activity_home_page.xml
    List<FNBEstablishment> fnbList;
    private final String sharedPrefFile = "com.example.android.mainsharedprefs";
    private SharedPreferences mPreferences;
    Set<String> favList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // sets up the sorting selector dropdown (spinner) to be placed on the action bar/tool bar itself
        getSupportActionBar().hide();
        Spinner sorting_selector = findViewById(R.id.sorting_selector);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sorting_selector_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sorting_selector.setAdapter(adapter);
        sorting_selector.setOnItemSelectedListener(this);
        // the method/code/logic to implement what happens when an item from the spinner is selected is below, onItemSelected

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
        Collections.sort(fnbList, chosenComparator); // order fnb establishments based on isFavorite, then chosen sorting method

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
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    // TODO
    // for the sorting selector drop down (spinner)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        String sortingMethod = parent.getItemAtPosition(position).toString();

        if (sortingMethod.equals("Sort by Favourites")){
            Comparator<FNBEstablishment> chosenComparator = new IsFavoriteComparator();
            Collections.sort(fnbList, chosenComparator);
            // TODO
            // Refresh the fnbButtons if current sorting method != "Sort by Favourites"
        }

        else if (sortingMethod.equals("Sort by Capacity")){
            Comparator<FNBEstablishment> chosenComparator = new CrowdLevelComparator();
            Collections.sort(fnbList, chosenComparator);
            // TODO
            // Refresh the fnbButtons if current sorting method != "Sort by Capacity"
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent){
        // TODO
        // probably need shared preference to see what was the last chosen sorting method from the previous time when the user was in the app
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferenceEditor = mPreferences.edit();
        preferenceEditor.clear();
        preferenceEditor.putStringSet("favourites",favList);
        preferenceEditor.apply();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
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
                // goToHistoricTrendsPage(view);
            }
        });

        fnbButton.getHistoryText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
                // goToHistoricTrendsPage(view);
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
        startActivity(intent);
    }
}
