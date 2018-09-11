package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectFarmer;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.yogeshborhade.shaktidevelopers.Database.Constants;
import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseFarmerClass;
import com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectHome.ProjectDetails;
import com.yogeshborhade.shaktidevelopers.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.yogeshborhade.shaktidevelopers.Database.Constants.numberWithoutExponential;

public class Update_Farmer extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button backButton, updateButton, deleteButton, AddPanCardButton;
    EditText editTextFarmerName, editTextFarmerMobileNumber, editTextFarmerAge, editTextFarmerCurrentAdress, editTextFarmerPanCard, editTextFarmerAdharCard,
            editTextFarmerBuyingPlotID, editTextFarmerPlotEastSize, editTextFarmerPlotWestSize, editTextFarmerPlotNorthSize, editTextFarmerSouthSize,
            editTextFarmerTotalBuyingArea, editTextFarmerRateperSqurefoot, editTextFarmerTotalAmountTobePaid, editTextFarmerAmountRescieved, editTextFarmerPendingAmount,
            editTextFarmerOthercharges, editTextFarmerNotificationRemark, editTextFarmerStatus, editTextPaymentModeNumnber;

    TextInputLayout textInputEditText;
    TextView textViewProjectID, textViewFarmerID, textViewFarmerDateOfBirth, textViewAmountRescievedDate, textViewPendingAmountCommitmentDate, textViewNotificationRemarkDare;
    ImageView imageViewDOBDatePicker, imageViewAmountRescievedDatePicker, imageViewPendingAmountCommitmentDate, FarmerNotificationDate;

    String ProjectID, farmertID, farmertName, farmertMobileNumber, farmertAddress,
            farmertDateOfBirth, farmerAge, farmertPanCardNumber, farmertAdharCardNumber, farmertBuyingPlotID,
            farmertPlotEastSize, farmertPlotWestSize, farmertPlotNorthSize, farmertPlotSouthSize,
            farmertTotalBuyingArea, farmertRatePerSqureFoot, farmertTotalAmountToBePaid, farmertAmountRescieved,
            farmertAmountResCievedDate, farmertPendingAmount, farmertPendingAmountCommitmentDate, farmertOtherCharges, farmertNotificationrRemark,
            farmertNotificationrRemarkDate, farmertStatus, farmerPaymentMode, farmerPaymentNumber = "0", farmerPanCardImage;

    DatabaseHelper databaseHelper;
    private List<DataBaseFarmerClass> dataBaseFarmerClassList = new ArrayList<>();

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_farmer);

        getXMLContent();


        Bundle bundle = getIntent().getExtras();

        ProjectID = bundle.getString("projectID", "0");
        farmertID = bundle.getString("farmertID", "0");
        Log.e(ProjectID, farmertID);

        databaseHelper = new DatabaseHelper(this);
        getSingleFarmer(Integer.parseInt(farmertID), ProjectID);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imageViewDOBDatePicker.setOnClickListener(new View.OnClickListener() {
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
                        textViewFarmerDateOfBirth.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });


        imageViewAmountRescievedDatePicker.setOnClickListener(new View.OnClickListener() {
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

                        textViewAmountRescievedDate.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });


        imageViewPendingAmountCommitmentDate.setOnClickListener(new View.OnClickListener() {
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

                        textViewPendingAmountCommitmentDate.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });


        FarmerNotificationDate.setOnClickListener(new View.OnClickListener() {
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

                        textViewNotificationRemarkDare.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });

        // Calculation For Rate Per Squre Foot
        editTextFarmerRateperSqurefoot.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (s.length() != 0) {
                    editTextFarmerTotalAmountTobePaid.setText(String.valueOf(numberWithoutExponential.format(Double.valueOf(editTextFarmerRateperSqurefoot.getText().toString()) * Double.valueOf(editTextFarmerTotalBuyingArea.getText().toString()))));
                } else {
                    editTextFarmerTotalAmountTobePaid.setText("0");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        // Amount Rescieved
        editTextFarmerAmountRescieved.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() != 0) {
                    editTextFarmerPendingAmount.setText(
                            String.valueOf(numberWithoutExponential.format(Double.valueOf(editTextFarmerTotalAmountTobePaid.getText().toString()) - Double.valueOf(editTextFarmerAmountRescieved.getText().toString()))));
                } else {
                    editTextFarmerAmountRescieved.setText("0");
                }


            }
        });


        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add(Constants.TRANSACTION_TYPE_CASH);
        categories.add(Constants.TRANSACTION_TYPE_CHECK);
        categories.add(Constants.TRANSACTION_TYPE_IMPS);
        categories.add(Constants.TRANSACTION_TYPE_NFT);
        categories.add(Constants.TRANSACTION_TYPE_RTGS);
        categories.add("OTHER");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateFarmer();

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteFarmer(Integer.parseInt(farmertID));

            }
        });


        AddPanCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Update_Farmer.this, FarmerPanCard.class);

                intent.putExtra("farmerID", String.valueOf(farmertID));
                intent.putExtra("farmerMob", String.valueOf(farmertMobileNumber));
                intent.putExtra("farmerPanCardImagePath", String.valueOf(farmerPanCardImage));

                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        if (position == 0) {
            textInputEditText.setVisibility(View.GONE);
            editTextPaymentModeNumnber.setText("0");
        } else {
            textInputEditText.setVisibility(View.VISIBLE);
            editTextPaymentModeNumnber.setText("0");

        }
        farmerPaymentMode = item;
        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void getXMLContent() {
        backButton = (Button) findViewById(R.id.mbackButton);
        updateButton = (Button) findViewById(R.id.mUpdate);
        deleteButton = (Button) findViewById(R.id.mDelete);
        AddPanCardButton = (Button) findViewById(R.id.mAddPanCard);


        editTextFarmerName = (EditText) findViewById(R.id.medtFarmerName);
        editTextFarmerMobileNumber = (EditText) findViewById(R.id.medtFarmerMobileNumber);
        editTextFarmerCurrentAdress = (EditText) findViewById(R.id.medtFarmerAdress);
        editTextFarmerAge = (EditText) findViewById(R.id.medtFarmerAge);
        editTextFarmerPanCard = (EditText) findViewById(R.id.medtFarmerPanCard);
        editTextFarmerAdharCard = (EditText) findViewById(R.id.medtFarmerAdharCard);
        editTextFarmerBuyingPlotID = (EditText) findViewById(R.id.medtFarmerBuyingPlotID);
        editTextFarmerPlotEastSize = (EditText) findViewById(R.id.medtFarmerEestSize);
        editTextFarmerPlotWestSize = (EditText) findViewById(R.id.medtFarmerWestSize);
        editTextFarmerPlotNorthSize = (EditText) findViewById(R.id.medtFarmerNorthSize);
        editTextFarmerSouthSize = (EditText) findViewById(R.id.medtFarmerSouthSize);
        editTextFarmerTotalBuyingArea = (EditText) findViewById(R.id.medtFarmerTotalBuyingArea);
        editTextFarmerRateperSqurefoot = (EditText) findViewById(R.id.medtFarmerRatePerSqurefoot);
        editTextFarmerTotalAmountTobePaid = (EditText) findViewById(R.id.medtFarmerTotalAmountToBePaid);
        editTextFarmerAmountRescieved = (EditText) findViewById(R.id.medtFarmerAmoutRescieved);
        editTextFarmerPendingAmount = (EditText) findViewById(R.id.medtFarmerPendingAmount);
        editTextFarmerOthercharges = (EditText) findViewById(R.id.medtFarmerOtherCharges);
        editTextFarmerNotificationRemark = (EditText) findViewById(R.id.medtFarmerNotificationRemark);
        editTextFarmerStatus = (EditText) findViewById(R.id.medtFarmerStatus);


        textViewProjectID = (TextView) findViewById(R.id.mtextProjectId);
        textViewFarmerID = (TextView) findViewById(R.id.mtextFarmerID);
        textViewFarmerDateOfBirth = (TextView) findViewById(R.id.mtextFarmerDobDatePicker);
        textViewAmountRescievedDate = (TextView) findViewById(R.id.mtextFarmerFarmerAmountRescievd);
        textViewPendingAmountCommitmentDate = (TextView) findViewById(R.id.mtextFarmerFarmerPendingAmount);
        textViewNotificationRemarkDare = (TextView) findViewById(R.id.mtextFarmerFarmerRemarkDatePicker);


        imageViewDOBDatePicker = (ImageView) findViewById(R.id.mimgFarmerDobDatePicker);
        imageViewAmountRescievedDatePicker = (ImageView) findViewById(R.id.mimgFarmerAmountRescievdDatePicker);
        imageViewPendingAmountCommitmentDate = (ImageView) findViewById(R.id.mimgFarmerPendingAmountDatePicker);
        FarmerNotificationDate = (ImageView) findViewById(R.id.mimgFarmerNotificationRemarkDatePicker);

        spinner = (Spinner) findViewById(R.id.spinner);
        editTextPaymentModeNumnber = (EditText) findViewById(R.id.expence_number);
        textInputEditText = (TextInputLayout) findViewById(R.id.input_expence_number);


        textViewFarmerDateOfBirth.setText(getDate());
        textViewAmountRescievedDate.setText(getDate());
        textViewPendingAmountCommitmentDate.setText(getDate());
        textViewNotificationRemarkDare.setText(getDate());
    }

    public void getSingleFarmer(int FarmerID, String projectID) {

        dataBaseFarmerClassList.addAll(databaseHelper.FarmerGetSingle(FarmerID, projectID));

        for (int i = 0; i <= dataBaseFarmerClassList.size() - 1; i++) {

            final DataBaseFarmerClass dataBaseFarmerClass = dataBaseFarmerClassList.get(i);
            ProjectID = dataBaseFarmerClass.getProjectID();
            farmertID = String.valueOf(dataBaseFarmerClass.getFarmerID());
            farmertName = dataBaseFarmerClass.getFarmerName();
            farmertMobileNumber = dataBaseFarmerClass.getFarmerMobileNumber();
            farmertAddress = dataBaseFarmerClass.getFarmerAddress();
            farmertDateOfBirth = dataBaseFarmerClass.getFarmerDateOfBirth();
            farmertPanCardNumber = dataBaseFarmerClass.getFarmerPanCardNumber();
            farmertAdharCardNumber = dataBaseFarmerClass.getFarmerAdharCardNumber();
            farmertBuyingPlotID = dataBaseFarmerClass.getFarmerSellingPlotID();
            farmertPlotEastSize = dataBaseFarmerClass.getFarmerPlotEastSize();
            farmertPlotWestSize = dataBaseFarmerClass.getFarmerPlotWestSize();
            farmertPlotNorthSize = dataBaseFarmerClass.getFarmerPlotNorthSize();
            farmertPlotSouthSize = dataBaseFarmerClass.getFarmerPlotSouthSize();
            farmertTotalBuyingArea = dataBaseFarmerClass.getFarmerTotalSellingArea();
            farmertRatePerSqureFoot = dataBaseFarmerClass.getFarmerRatePerSqureFoot();
            farmertTotalAmountToBePaid = dataBaseFarmerClass.getFarmerTotalAmountToBePaid();
            farmertAmountRescieved = dataBaseFarmerClass.getFarmerAmountgiven();
            farmertAmountResCievedDate = dataBaseFarmerClass.getFarmerAmountgivenDate();
            farmertPendingAmount = dataBaseFarmerClass.getFarmerPendingAmount();
            farmertPendingAmountCommitmentDate = dataBaseFarmerClass.getFarmerPendingAmountCommitmentDate();
            farmertOtherCharges = dataBaseFarmerClass.getFarmerOtherCharges();
            farmertNotificationrRemark = dataBaseFarmerClass.getFarmerNotificationrRemark();
            farmertNotificationrRemarkDate = dataBaseFarmerClass.getFarmerNotificationrRemarkDate();
            farmertStatus = dataBaseFarmerClass.getFarmerStatus();
            farmerPanCardImage = dataBaseFarmerClass.getFarmerPanCardImage();


            farmerPaymentNumber = dataBaseFarmerClass.getFarmer_payment_number();
            farmerPaymentMode = dataBaseFarmerClass.getFarmer_payment_type();

            farmerAge = dataBaseFarmerClass.getFarmerAge();

            Log.e("farmaer Payment Mode", farmerPaymentMode);
            Log.e("farmaer Payment Mode", farmerPaymentNumber);


            textViewProjectID.setText("Project ID " + (ProjectID));
            textViewFarmerID.setText("Farmer ID " + farmertID);
            textViewFarmerDateOfBirth.setText(farmertDateOfBirth);
            textViewAmountRescievedDate.setText(farmertAmountResCievedDate);
            textViewPendingAmountCommitmentDate.setText(farmertPendingAmountCommitmentDate);

            textViewNotificationRemarkDare.setText(farmertNotificationrRemarkDate);

            editTextFarmerName.setText(farmertName);
            editTextFarmerMobileNumber.setText(farmertMobileNumber);
            editTextFarmerCurrentAdress.setText(farmertAddress);
            editTextFarmerPanCard.setText(farmertPanCardNumber);
            editTextFarmerAdharCard.setText(farmertAdharCardNumber);
            editTextFarmerBuyingPlotID.setText(farmertBuyingPlotID);
            editTextFarmerPlotEastSize.setText(farmertPlotEastSize);
            editTextFarmerPlotWestSize.setText(farmertPlotWestSize);
            editTextFarmerPlotNorthSize.setText(farmertPlotNorthSize);
            editTextFarmerSouthSize.setText(farmertPlotSouthSize);
            editTextFarmerTotalBuyingArea.setText(farmertTotalBuyingArea);
            editTextFarmerRateperSqurefoot.setText(farmertRatePerSqureFoot);
            editTextFarmerTotalAmountTobePaid.setText(farmertTotalAmountToBePaid);
            editTextFarmerAmountRescieved.setText(farmertAmountRescieved);
            editTextFarmerPendingAmount.setText(farmertPendingAmount);
            editTextFarmerOthercharges.setText(farmertOtherCharges);
            editTextFarmerNotificationRemark.setText(farmertNotificationrRemark);
            editTextFarmerStatus.setText(farmertStatus);
            editTextFarmerAge.setText(farmerAge);


            if (Double.valueOf(farmertAmountRescieved) <= 0) {
                // Toast.makeText(this, "Fresh Can Update Evrything", Toast.LENGTH_SHORT).show();
            } else {
                // Toast.makeText(this, "Not Fresh", Toast.LENGTH_SHORT).show();
                editTextFarmerTotalBuyingArea.setEnabled(false);
                editTextFarmerRateperSqurefoot.setEnabled(false);
                editTextFarmerTotalAmountTobePaid.setEnabled(false);
                editTextFarmerAmountRescieved.setEnabled(false);
                editTextFarmerPendingAmount.setEnabled(false);
                editTextPaymentModeNumnber.setEnabled(false);
                spinner.setEnabled(false);
            }
        }
    }

    public void updateFarmer() {
        ProjectID = ProjectDetails.ProjectID;
        farmertName = editTextFarmerName.getText().toString();
        farmertMobileNumber = editTextFarmerMobileNumber.getText().toString();
        farmertAddress = editTextFarmerCurrentAdress.getText().toString();
        farmertDateOfBirth = textViewFarmerDateOfBirth.getText().toString();
        farmertPanCardNumber = editTextFarmerPanCard.getText().toString();
        farmertAdharCardNumber = editTextFarmerAdharCard.getText().toString();

        farmertBuyingPlotID = editTextFarmerBuyingPlotID.getText().toString();
        farmertPlotEastSize = editTextFarmerPlotEastSize.getText().toString();
        farmertPlotWestSize = editTextFarmerPlotWestSize.getText().toString();
        farmertPlotNorthSize = editTextFarmerPlotNorthSize.getText().toString();
        farmertPlotSouthSize = editTextFarmerSouthSize.getText().toString();
        farmertTotalBuyingArea = editTextFarmerTotalBuyingArea.getText().toString();
        farmertRatePerSqureFoot = editTextFarmerRateperSqurefoot.getText().toString();
        farmertTotalAmountToBePaid = editTextFarmerTotalAmountTobePaid.getText().toString();
        farmertAmountRescieved = editTextFarmerAmountRescieved.getText().toString();
        farmertAmountResCievedDate = textViewAmountRescievedDate.getText().toString();
        farmertPendingAmount = editTextFarmerPendingAmount.getText().toString();
        farmertPendingAmountCommitmentDate = textViewPendingAmountCommitmentDate.getText().toString();
        farmertOtherCharges = editTextFarmerOthercharges.getText().toString();
        farmertNotificationrRemark = editTextFarmerNotificationRemark.getText().toString();
        farmertNotificationrRemarkDate = textViewNotificationRemarkDare.getText().toString();
        farmertStatus = editTextFarmerStatus.getText().toString();
        farmerAge = editTextFarmerAge.getText().toString();

        if (farmertNotificationrRemarkDate.equals("") || farmertNotificationrRemarkDate.equals("NA") || farmertNotificationrRemarkDate.equals("na")) {
            farmertNotificationrRemarkDate = getDate();
        } else {
            farmertNotificationrRemarkDate = textViewNotificationRemarkDare.getText().toString();

        }

        farmerPaymentNumber = editTextPaymentModeNumnber.getText().toString();
        Log.e("farmaer Payment Mode", farmerPaymentMode);
        Log.e("farmaer Payment Mode", farmerPaymentNumber);

        long id = databaseHelper.FarmerUpdate(ProjectID, farmertID
                , farmertName, farmertMobileNumber, farmertAddress,
                farmertDateOfBirth, farmerAge, farmertPanCardNumber, farmertAdharCardNumber, farmertBuyingPlotID,
                farmertPlotEastSize, farmertPlotWestSize, farmertPlotNorthSize, farmertPlotSouthSize,
                farmertTotalBuyingArea, farmertRatePerSqureFoot, farmertTotalAmountToBePaid, farmertAmountRescieved,
                farmertAmountResCievedDate, farmertPendingAmount, farmertPendingAmountCommitmentDate, farmertOtherCharges, farmertNotificationrRemark,
                farmertNotificationrRemarkDate, farmertStatus, farmerPaymentMode, farmerPaymentNumber);

        if (id == 0) {
            //   Toast.makeText(Update_Farmer.this, "There is Some Errors", Toast.LENGTH_SHORT).show();

        } else {

            long insertTransaction = databaseHelper.TransactionInsert(ProjectID, farmertBuyingPlotID, "NA", farmertID, farmertAmountRescieved,
                    Constants.TRASACTION_DONE_BY_OWENER, Constants.TRASACTION_MONEY_GIVE, farmertAmountResCievedDate, Constants.TRANSACTION_TYPE_CASH, "NA", farmertNotificationrRemark);



      /*      long notificationInsert = databaseHelper.NotificationInsert(ProjectID, farmertNotificationrRemarkDate,
                    "NA", "Notification For Customer",
                    farmertNotificationrRemark, Constants.FARMER, farmertID, Constants.UNREAD);


            if (notificationInsert == 0) {
                // Toast.makeText(Update_Farmer.this, "Notification Not Added", Toast.LENGTH_SHORT).show();

            } else {
                // Toast.makeText(Update_Farmer.this, "Notification Added" + id, Toast.LENGTH_SHORT).show();

            }
            */

            if (insertTransaction == 0) {
                // Toast.makeText(Update_Farmer.this, "Transaction Not Inserted", Toast.LENGTH_SHORT).show();
            } else {
                // Toast.makeText(Update_Farmer.this, "Transaction Added" + id, Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }

    public void deleteFarmer(int FarmerID) {
        long id = databaseHelper.FarmerDelete(FarmerID);

        if (id == 0) {
            //   Toast.makeText(Update_Farmer.this, "Not Inserted", Toast.LENGTH_SHORT).show();
        } else {
            //  Toast.makeText(Update_Farmer.this, "Row  Updated" + id, Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    public static String getDate() {

        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATEFORMATE);
        String dateString = sdf.format(date);

        return dateString;
    }

}

