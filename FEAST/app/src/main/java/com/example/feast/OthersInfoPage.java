package com.example.feast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Collections;
import java.util.Set;

public class OthersInfoPage extends AppCompatActivity {

    private final String sharedPrefFile = "com.example.android.mainsharedprefs";
    private SharedPreferences mPreferences;
    Set<String> favList;

    @Override
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_info_page);

        getSupportActionBar().hide();
        Intent intent = getIntent();    //Init intent

        // get values from intent
        String restaurantName = intent.getStringExtra("fnbEstablishmentName");
        String waitingTime = intent.getStringExtra("waitingTime");
        String capacity = intent.getStringExtra("capacity");
        boolean isHalal = intent.getBooleanExtra("isHalal", false);
        String openHour = intent.getStringExtra("openHour");
        String closeHour = intent.getStringExtra("closeHour");
        String openMin = intent.getStringExtra("openMin");
        String closeMin = intent.getStringExtra("closeMin");
        String isOpen = intent.getStringExtra("isOpen");

        // get the widgets
        TextView restaurantNameTextView = findViewById(R.id.restaurantName);
        TextView capacityTextView = findViewById(R.id.capacity);
        TextView waitingTimeTextView = findViewById(R.id.waitingTime);
        TextView openHourTextView = findViewById(R.id.openHour);
        TextView closeHourTextView = findViewById(R.id.closeHour);
        TextView openMinTextView = findViewById(R.id.openMin);
        TextView closeMinTextView = findViewById(R.id.closeMin);
        TextView halalCertificationTextView = findViewById(R.id.halalCertification);
        TextView isOpenTextView = findViewById(R.id.status_var_Text);

        restaurantNameTextView.setText(restaurantName);
        isOpenTextView.setText(isOpen);

        // sets the colour of the isOpenTextView
        if (isOpen.contains("Open")){
            isOpenTextView.setTextColor(getResources().getColor(R.color.green, null));
        }

        else if (isOpen.contains("Closed")){
            isOpenTextView.setTextColor(getResources().getColor(R.color.red, null));
        }

        capacityTextView.setText(capacity);

        // sets the colour of the capacityTextView
        if (capacity.contains("Not Crowded")){
            capacityTextView.setTextColor(getResources().getColor(R.color.green, null));
        }

        else if (capacity.contains("Crowded")){
            capacityTextView.setTextColor(getResources().getColor(R.color.orange, null));
        }

        else if (capacity.contains("Very Crowded")){
            capacityTextView.setTextColor(getResources().getColor(R.color.dark_orange, null));
        }

        else if (capacity.contains("Full")){
            capacityTextView.setTextColor(getResources().getColor(R.color.red, null));
        }

        waitingTimeTextView.setText(waitingTime);
        openHourTextView.setText(openHour);
        closeHourTextView.setText(closeHour);
        openMinTextView.setText(openMin);
        closeMinTextView.setText(closeMin);

        if (isHalal) {
            halalCertificationTextView.setText("Halal");
        }

        else{
            halalCertificationTextView.setText("Non-Halal");
        }

        ImageView restaurantImage = findViewById(R.id.restaurantImage);    //Init restaurant image
        if (restaurantName.equals("Canteen")) {
            restaurantImage.setImageResource(R.drawable.canteen);
        }

        else if (restaurantName.equals("D'Star Bistro")) {
            restaurantImage.setImageResource(R.drawable.d_star_bistro);
        }

        else if (restaurantName.equals("Crooked Cooks")) {
            restaurantImage.setImageResource(R.drawable.crooked_cooks);

        }
        else if (restaurantName.equals("Gomgom")) {
            restaurantImage.setImageResource(R.drawable.gomgom);
        }

        else if (restaurantName.equals("Simon's")) {
            restaurantImage.setImageResource(R.drawable.simon);
        }

        // favourites button
        mPreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);
        favList = mPreferences.getStringSet("favourites", Collections.emptySet());
        Switch favouriteSwitch = findViewById(R.id.favouriteSwitch); //init Switch widget
        favouriteSwitch.setChecked(favList.contains(restaurantName)); //set current value based on saved preferences
        favouriteSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (favouriteSwitch.isChecked()){
                    favList.add(restaurantName); //add favourite
                }

                else{
                    favList.remove(restaurantName); //remove favourite
                }
            }
        });

        String menu = intent.getStringExtra("menu");    //get intent
        TextView mInfoMenu = findViewById(R.id.infoMenu);   //Init menu
        mInfoMenu.setText(menu);

        ImageButton mInfoBackButton = findViewById(R.id.infoBackButton);
        mInfoBackButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HomePage.class));     //Send back to main page
            }
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
}