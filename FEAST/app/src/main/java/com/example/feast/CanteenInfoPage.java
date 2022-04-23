package com.example.feast;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class CanteenInfoPage extends AppCompatActivity {
    @Override
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canteen_info_page);

        getSupportActionBar().hide();
        Intent intent = getIntent();    //Init intent

        // get values from intent
        String canteenStallName = intent.getStringExtra("canteenStallName");
        String waitingTime = intent.getStringExtra("waitingTime");
        String capacity = intent.getStringExtra("capacity");
        String openHour = intent.getStringExtra("openHour");
        String closeHour = intent.getStringExtra("closeHour");
        String openMin = intent.getStringExtra("openMin");
        String closeMin = intent.getStringExtra("closeMin");
        String isOpen = intent.getStringExtra("isOpen");

        // get the widgets
        TextView canteenStallNameTextView = findViewById(R.id.canteenStallName);
        TextView waitingTimeTextView = findViewById(R.id.waitingTime);
        TextView capacityTextView = findViewById(R.id.capacity);
        TextView openHourTextView = findViewById(R.id.openHour);
        TextView closeHourTextView = findViewById(R.id.closeHour);
        TextView openMinTextView = findViewById(R.id.openMin);
        TextView closeMinTextView = findViewById(R.id.closeMin);
        TextView isOpenTextView = findViewById(R.id.status_var_Text);

        canteenStallNameTextView.setText("Canteen: " + canteenStallName);
        isOpenTextView.setText(isOpen);

        // sets the colour of the isOpenTextView
        if (isOpen.contains("Open")){
            isOpenTextView.setTextColor(getResources().getColor(R.color.green, null));
        }

        else if (isOpen.contains("Closed")){
            isOpenTextView.setTextColor(getResources().getColor(R.color.red, null));
        }

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

        ImageView canteenStallImage = findViewById(R.id.canteenStallImage);

        // sets the canteenStallImage to that representing the specific canteen stall based on the stallName passed on by the intent
        if (canteenStallName.equals("Indian Food")){
            canteenStallImage.setImageResource(R.drawable.indian_food);
        }

        else if (canteenStallName.equals("Western Food")){
            canteenStallImage.setImageResource(R.drawable.western_food);
        }

        else if (canteenStallName.equals("Healthy Soup")){
            canteenStallImage.setImageResource(R.drawable.healthy_soup);
        }

        else if (canteenStallName.equals("Japanese & Korean Food")){
            canteenStallImage.setImageResource(R.drawable.japanese_and_korean_food);
        }

        else if (canteenStallName.equals("Economic Rice")){
            canteenStallImage.setImageResource(R.drawable.economic_rice);
        }

        else if (canteenStallName.equals("Drinks & Snacks")){
            canteenStallImage.setImageResource(R.drawable.drinks_and_snacks);
        }

        // TODO
        String menu = intent.getStringExtra("menu");    //get intent
        TextView mInfoMenu = findViewById(R.id.infoMenu);   //Init menu
        mInfoMenu.setText(menu);

        ImageButton mInfoBackButton = findViewById(R.id.infoBackButton);
        mInfoBackButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CanteenInfoPage.this, CanteenPage. class);
                intent.putExtra("isOpen", isOpen);
                intent.putExtra("waitingTime", waitingTime);
                intent.putExtra("capacity", capacity);
                intent.putExtra("openHour", openHour);
                intent.putExtra("openMin", openMin);
                intent.putExtra("closeHour", closeHour);
                intent.putExtra("closeMin", closeMin);
                startActivity(intent);
            }
        });
    }
}