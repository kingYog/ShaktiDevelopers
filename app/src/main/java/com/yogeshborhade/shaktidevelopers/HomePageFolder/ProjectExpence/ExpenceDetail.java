package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectExpence;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.Toast;

import com.yogeshborhade.shaktidevelopers.Database.Constants;
import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseExpenceClas;
import com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectHome.ProjectDetails;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ExpenceDetail extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button backButton, updateButton, deleteButton;
    EditText editTextExpenceName, editTextExpencePrice, editTextPaymentModeNumnber, editTextexpenceDetails;

    TextView textViewExpenceDaye;
    ImageView imageViewDatePicker;
    TextInputLayout textInputEditText;

    String ProjectID, expenceID, expenceName, expencePrice, expenceDate, expenceDescription, expencePaymentMode, expencePaaymentNumber;


    DatabaseHelper databaseHelper;
    private List<DataBaseExpenceClas> dataBaseExpenceClasList = new ArrayList<>();

    List<String> categories = new ArrayList<String>();
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expence_detail);

        getXMLContent();
        Bundle bundle = getIntent().getExtras();
        ProjectID = bundle.getString("projectID", "0");
        expenceID = bundle.getString("expenceID", "0");

        Log.e("Expence ID", expenceID);
        Log.e("ProjectID ID", ProjectID);
        databaseHelper = new DatabaseHelper(this);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateExpence();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteExpence(Integer.parseInt(expenceID));
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

                        textViewExpenceDaye.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });


        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        categories.clear();
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

        // get Values
        getSingleExpence(Integer.parseInt(expenceID), ProjectID);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        if (position == 0) {
            textInputEditText.setVisibility(View.GONE);
            //  editTextPaymentModeNumnber.setText("0");
        } else {
            textInputEditText.setVisibility(View.VISIBLE);
            //  editTextPaymentModeNumnber.setText("0");

        }
        expencePaymentMode = item;
        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
        expencePaymentMode = Constants.TRANSACTION_TYPE_CASH;
    }


    public void getXMLContent() {
        backButton = (Button) findViewById(R.id.mbackButton);
        updateButton = (Button) findViewById(R.id.mUpdate);
        deleteButton = (Button) findViewById(R.id.mDelete);
        editTextExpenceName = (EditText) findViewById(R.id.expence_name);
        editTextExpencePrice = (EditText) findViewById(R.id.expence_Amount);
        textViewExpenceDaye = (TextView) findViewById(R.id.mtextdate);
        imageViewDatePicker = (ImageView) findViewById(R.id.mdatePicker);
        spinner = (Spinner) findViewById(R.id.spinner);
        editTextexpenceDetails = (EditText) findViewById(R.id.expence_desc);
        editTextPaymentModeNumnber = (EditText) findViewById(R.id.expence_number);
        textInputEditText = (TextInputLayout) findViewById(R.id.input_expence_number);
    }

    public void getSingleExpence(int expenceID, String projectID) {

        dataBaseExpenceClasList.addAll(databaseHelper.ExpenceGetSingle(expenceID, projectID));

        for (int i = 0; i < dataBaseExpenceClasList.size(); i++) {

            final DataBaseExpenceClas dataBaseExpenceClas = dataBaseExpenceClasList.get(i);
            String ExpenceID = String.valueOf(dataBaseExpenceClas.getExpenceID());
            expenceName = dataBaseExpenceClas.getExpenceName();
            expencePrice = dataBaseExpenceClas.getExpencePrice();
            expenceDate = dataBaseExpenceClas.getExpenceDate();
            expenceDescription = dataBaseExpenceClas.getExpenceDetails();
            expencePaymentMode = dataBaseExpenceClas.getExpencePaymentMode();
            expencePaaymentNumber = dataBaseExpenceClas.getExpencePaymentNumber();

            Log.e("Mode", dataBaseExpenceClas.getExpencePaymentMode());
            Log.e("Mode", dataBaseExpenceClas.getExpencePaymentNumber());

            editTextExpenceName.setText(expenceName);
            editTextExpencePrice.setText(expencePrice);
            editTextexpenceDetails.setText(expenceDescription);
            editTextPaymentModeNumnber.setText(expencePaaymentNumber);

            textViewExpenceDaye.setText(expenceDate);

            spinner.setSelection(categories.indexOf(expencePaymentMode));
        }
    }

    public void updateExpence() {
        ProjectID = ProjectDetails.ProjectID;
        expenceName = editTextExpenceName.getText().toString();
        expencePrice = editTextExpencePrice.getText().toString();
        expenceDate = textViewExpenceDaye.getText().toString();
        expenceDescription = "Nothing ";
        expencePaaymentNumber = editTextPaymentModeNumnber.getText().toString();


        if (expenceName.equals("") || expenceName.length() < 4) {
            editTextExpenceName.setError("Please insert Correct Expence Name , character must be greater than 3");

        } else if (expencePrice.equals("")) {
            editTextExpencePrice.setError("Please insert Correct Expence Price");
        } else {
            Log.e("Payment Mode", expencePaymentMode);
            Log.e("Payment Number", expencePaaymentNumber);
            long id = databaseHelper.ExpenceUpdate(ProjectID, expenceID, expenceName, expencePrice, expenceDate, expencePaymentMode, expencePaaymentNumber, expenceDescription);
            if (id == 0) {
               // Toast.makeText(this, "Expence Not Updated", Toast.LENGTH_SHORT).show();
            } else {
              //  Toast.makeText(this, "Expence Updated", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

    }

    public void deleteExpence(int CustomerID) {
        long id = databaseHelper.ExpeneceDelete(CustomerID);

        if (id == 0) {
           // Toast.makeText(ExpenceDetail.this, "Expence Not Deleted", Toast.LENGTH_SHORT).show();
        } else {
           // Toast.makeText(ExpenceDetail.this, "Expence Deleted" + id, Toast.LENGTH_SHORT).show();
            finish();
        }

    }


}

