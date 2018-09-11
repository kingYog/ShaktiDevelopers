package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectFarmer;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yogeshborhade.shaktidevelopers.Database.Constants;
import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectHome.ProjectDetails;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.Calendar;

public class AddFarmer extends AppCompatActivity {

    Button backButton, submitButton;
    EditText editTextFarmerName, editTextFarmerMobileNumber, editTextFarmerAge, editTextFarmerCurrentAdress,
            editTextFarmerPanCard, editTextFarmerAdharCard;

    TextView textViewDateOfBirth;
    ImageView imageViewDatePicker;

    String ProjectID, farmerID, farmerName, farmerMobileNumber, farmerAddress,
            farmerDateOfBirth, farmerAge, farmerPanCardNumber, farmerAdharCardNumber, farmerBuyingPlotID,
            farmerPlotEastSize, farmerPlotWestSize, farmerPlotNorthSize, farmerPlotSouthSize,
            farmerTotalBuyingArea, farmerRatePerSqureFoot, farmerTotalAmountToBePaid, farmerAmountRescieved,
            farmerAmountResCievedDate, farmerPendingAmount, farmerPendingAmountCommitmentDate, farmerOtherCharges, farmerNotificationrRemark,
            farmerNotificationrRemarkDate, farmerStatus, farmerPanCardImage;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_farmer);
        databaseHelper = new DatabaseHelper(this);
        getXMLContent();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imageViewDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int yy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH);
                int dd = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date = String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear + 1)
                                + "-" + String.valueOf(year);
                        textViewDateOfBirth.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FarmerInsert();
            }
        });
    }


    public void getXMLContent() {
        backButton = (Button) findViewById(R.id.mbackButton);
        submitButton = (Button) findViewById(R.id.mSubmit);

        editTextFarmerName = (EditText) findViewById(R.id.medtFarmerName);
        editTextFarmerMobileNumber = (EditText) findViewById(R.id.medtFarmerMobileNumber);
        editTextFarmerCurrentAdress = (EditText) findViewById(R.id.medtFarmerAdress);
        editTextFarmerPanCard = (EditText) findViewById(R.id.medtFarmerPanCard);
        editTextFarmerAdharCard = (EditText) findViewById(R.id.medtFarmerAdharCard);
        editTextFarmerAge = (EditText) findViewById(R.id.medtFarmerAge);

        textViewDateOfBirth = (TextView) findViewById(R.id.mtextFarmerDobDatePicker);
        imageViewDatePicker = (ImageView) findViewById(R.id.mimgFarmerDobDatePicker);
        textViewDateOfBirth.setText(Constants.getDate());


    }

    public void FarmerInsert() {
        ProjectID = ProjectDetails.ProjectID;
        farmerID = "We Cannot Insert Here";
        farmerName = editTextFarmerName.getText().toString();
        farmerMobileNumber = editTextFarmerMobileNumber.getText().toString();
        farmerAddress = editTextFarmerCurrentAdress.getText().toString();
        farmerDateOfBirth = textViewDateOfBirth.getText().toString();
        farmerPanCardNumber = editTextFarmerPanCard.getText().toString();
        farmerAdharCardNumber = editTextFarmerAdharCard.getText().toString();
        farmerAge = editTextFarmerAge.getText().toString();
        farmerBuyingPlotID = "0";
        farmerPlotEastSize = "0";
        farmerPlotWestSize = "0";
        farmerPlotNorthSize = "0";
        farmerPlotSouthSize = "0";
        farmerTotalBuyingArea = "0";
        farmerRatePerSqureFoot = "0";
        farmerTotalAmountToBePaid = "0";
        farmerAmountRescieved = "0";
        farmerAmountResCievedDate = "0";
        farmerPendingAmount = "0";
        farmerPendingAmountCommitmentDate = "0";
        farmerOtherCharges = "NA";
        farmerNotificationrRemark = "NA";
        farmerNotificationrRemarkDate = "NA";
        farmerStatus = "NA";
        farmerPanCardImage = "NA";

        if (farmerName.equals("") || farmerName.length() < 4) {
            editTextFarmerName.setError("Please Enter Correct Name");
        } else if (farmerMobileNumber.equals("") || farmerMobileNumber.length() < 10 || farmerMobileNumber.startsWith("0")) {
            editTextFarmerMobileNumber.setError("Plese Enter Correct Mobile number");
        } else {

            long id = databaseHelper.FarmerInsert(ProjectID, farmerName, farmerMobileNumber, farmerAddress,
                    farmerDateOfBirth,farmerAge, farmerPanCardNumber, farmerAdharCardNumber, farmerBuyingPlotID,
                    farmerPlotEastSize, farmerPlotWestSize, farmerPlotNorthSize, farmerPlotSouthSize,
                    farmerTotalBuyingArea, farmerRatePerSqureFoot, farmerTotalAmountToBePaid, farmerAmountRescieved,
                    farmerAmountResCievedDate, farmerPendingAmount, farmerPendingAmountCommitmentDate, farmerOtherCharges, farmerNotificationrRemark,
                    farmerNotificationrRemarkDate, farmerStatus, farmerPanCardImage);

            if (id == 0) {

               // Toast.makeText(AddFarmer.this, "Not Inserted", Toast.LENGTH_SHORT).show();
            } else {

              //  Toast.makeText(AddFarmer.this, "Inserted Row " + id, Toast.LENGTH_SHORT).show();

            }
            finish();
        }
    }
}
