package com.example.feast;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class FNBButton extends ConstraintLayout{
    // all the variable data fields/images will be here as attributes of the FNBButton class
    private TextView fnbEstablishmentName; // for the name of the F&B establishment (e.g. Canteen)
    private String fnbEstablishmentNameString; // for the Parcelable
    private TextView waitingTime; // for the estimated waiting time at the F&B establishment given current crowd levels
    private String waitingTimeString; // for the Parcelable
    private TextView capacity; // for the current capacity of the F&B establishment, consisting of a word description and a percentage (e.g. Full 100%)
    private String capacityString; // for the Parcelable
    private TextView isOpen; // to indicate whether the F&B establishment is currently open or closed
    private String isOpenString; // for the Parcelable
    private ImageView fnbPhoto; // for a picture of the F&B establishment, to grab the image from the database or from some website? Backend team to clarify
    private TextView dotText; // constant
    private ImageView historicalTrendPhoto; // constant
    private TextView historyText; // constant
    private ImageView waitingTimePhoto; // constant
    private Button favouriteIndicator; // to indicate whether the F&B establishment is a user's favourite
    private boolean isFavourite; // whether this particular F&B establishment is in the favourites or not
    private ConstraintLayout fnbButton; // constant
    private Context context;

    public FNBButton(Context context){ // constructor for FNBButton class
        super(context);
        this.context = context;
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fnb_button, null); // custom layout.xml file for the FNBButton
        this.fnbEstablishmentName = view.findViewById(R.id.fnbEstablishmentName);
        this.waitingTime = view.findViewById(R.id.waitingTime);
        this.capacity = view.findViewById(R.id.capacity);
        this.isOpen = view.findViewById(R.id.isOpen);
        this.fnbPhoto = view.findViewById(R.id.fnbPhoto);
        this.dotText = view.findViewById(R.id.dotText);
        this.historicalTrendPhoto = view.findViewById(R.id.historicalTrendPhoto);
        this.historyText = view.findViewById(R.id.historyText);
        this.waitingTimePhoto = view.findViewById(R.id.waitingTimePhoto);
        this.favouriteIndicator = view.findViewById(R.id.favouriteIndicator);
        this.fnbButton = view.findViewById(R.id.fnbButton);
        this.addView(view);
    }

    public TextView getFnbEstablishmentName() {
        return fnbEstablishmentName;
    }

    public TextView getWaitingTime() {
        return waitingTime;
    }

    public TextView getCapacity() {
        return capacity;
    }

    public TextView getIsOpen() {
        return isOpen;
    }

    public ImageView getFnbPhoto() {
        return fnbPhoto;
    }

    public TextView getDotText() {
        return dotText;
    }

    public ImageView getHistoricalTrendPhoto() {
        return historicalTrendPhoto;
    }

    public TextView getHistoryText() {
        return historyText;
    }

    public ImageView getWaitingTimePhoto() {
        return waitingTimePhoto;
    }

    public ConstraintLayout getFnbButton() {
        return fnbButton;
    }

    public String getFnbEstablishmentNameString() {
        return fnbEstablishmentNameString;
    }

    public String getWaitingTimeString() {
        return waitingTimeString;
    }

    public String getCapacityString() {
        return capacityString;
    }

    public String getIsOpenString() {
        return isOpenString;
    }

    public boolean getIsFavourite() {
        return isFavourite;
    }

    public void setFNBEstablishmentName(String name){
        this.fnbEstablishmentName.setText(name);
        this.fnbEstablishmentNameString = name;

        // this function will simultaneously set the FNBPhoto since it is dependant on the name of the FNB Establishment
        switch(name){
            case "Canteen":
                this.fnbPhoto.setImageResource(R.drawable.canteen);
                break;

            case "D'Star Bistro":
                this.fnbPhoto.setImageResource(R.drawable.d_star_bistro);
                break;

            case "Crooked Cooks":
                this.fnbPhoto.setImageResource(R.drawable.crooked_cooks);
                break;

            case "Gomgom":
                this.fnbPhoto.setImageResource(R.drawable.gomgom);
                break;

            case "Simon's":
                this.fnbPhoto.setImageResource(R.drawable.simon);
                break;
        }
    }

    public void setWaitingTime(String waitingTime){
        this.waitingTime.setText(waitingTime);
        this.waitingTimeString = waitingTime;
    }

    public void setCapacity(String crowdLevel, double crowdPercentage) {
        switch(crowdLevel){
            case "Not Crowded":
                this.capacity.setTextColor(Color.parseColor("#008000")); // sets the color of the capacity TextView to Green (#008000)
                break;

            case "Crowded":
                this.capacity.setTextColor(Color.parseColor("#FFA500")); // sets the color of the capacity TextView to Orange (#FFA500)
                break;

            case "Very Crowded":
                this.capacity.setTextColor(Color.parseColor("#FF8C00")); // sets the color of the capacity TextView to Dark Orange (#FF8C00)
                break;

            case "Full":
                this.capacity.setTextColor(Color.parseColor("#FF0000")); // sets the color of the capacity TextView to Red (#FF0000)
                break;
        }

        String tempCapacity = crowdLevel;
        double rawCrowdPercentage = crowdPercentage * 100;
        int simplifiedCrowdPercentage = (int) rawCrowdPercentage;
        tempCapacity += " " + String.valueOf(simplifiedCrowdPercentage) + "%"; // sets the percentage of the crowd level
        this.capacity.setText(tempCapacity);
        this.capacityString = tempCapacity;
    }

    public void setIsOpen(String openStatus){
        if (openStatus.equals("Closed")){
            this.isOpen.setTextColor(Color.parseColor("#FF0000")); // if the fnb establishment is closed, change the text colour to Red (#FF0000)
        }

        else{
            this.isOpen.setTextColor(Color.parseColor("#008000")); // if the fnb establishment is open, change the text colour to Green (#008000)
        }

        this.isOpen.setText(openStatus);
        this.isOpenString = openStatus;
    }

    public void setIsFavourite(boolean favouriteOrNot){
        if (favouriteOrNot){ // if the input value is true, meaning that this particular F&B establishment is in the user's favourites list
            this.favouriteIndicator.setBackgroundColor(Color.parseColor("#FFD700")); // sets it to gold colour (#FFD700)
        }

        else{
            this.favouriteIndicator.setBackgroundColor(Color.parseColor("#FFFFFF")); // sets it to white colour (#FFFFFF)
        }

        this.isFavourite = favouriteOrNot;
    }



    // want to delete? - Javier
    // to make the FNBButton object be able to be passed into the implicit intents
    // ignore for now, can leave this as a legacy or something lol - Fuo En
//    @RequiresApi(api = Build.VERSION_CODES.Q)
//    @Override
//    public void writeToParcel(Parcel out, int flags){
//        out.writeString(this.fnbEstablishmentName.getText().toString());
//        out.writeString(this.waitingTime.getText().toString());
//        out.writeString(this.capacity.getText().toString());
//        out.writeString(this.isOpen.getText().toString());
//        out.writeBoolean(this.isFavourite);
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.Q)
//    private void readFromParcel(Parcel in){
//        this.fnbEstablishmentNameString = in.readString();
//        this.waitingTimeString = in.readString();
//        this.capacityString = in.readString();
//        this.isOpenString = in.readString();
//        this.isFavourite = in.readBoolean();
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.Q)
//    public FNBButton(Parcel in, Context context){
//        super(context);
//        readFromParcel(in);
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
//        Context context = this.context;
//
//        @RequiresApi(api = Build.VERSION_CODES.Q)
//        public FNBButton createFromParcel(Parcel in){
//            return new FNBButton(in, this.context);
//        }
//
//        public FNBButton[] newArray(int size){
//            return new FNBButton[size];
//        }
//    };
}
