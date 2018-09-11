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
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseExpenceNameClass;
import com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectHome.ProjectDetails;
import com.yogeshborhade.shaktidevelopers.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddExpence extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button backButton, submitButton;
    EditText editTextExpenceName, editTextExpencePrice, editTextPaymentModeNumnber, editTextexpenceDetails;


    TextView textViewExpenceDaye;
    ImageView imageViewDatePicker;
    TextInputLayout textInputEditText;

    String ProjectID, expenceID, expenceName, expencePrice, expenceDate, expenceDescription, expencePaymentMode, expencePaaymentNumber = "0";

    DatabaseHelper databaseHelper;


    private static final String TAG = "AddExpence";


    Spinner spinner, ExpenceNameSpinner;


    //Expence Spinner
    private ArrayList<DataBaseExpenceNameClass> dataBaseExpenceNameClassArrayList = new ArrayList<>();
    AdapterExpenceName adapterExpenceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expence);


        databaseHelper = new DatabaseHelper(this);
        getXMLContent();
        textInputEditText.setVisibility(View.GONE);
        editTextPaymentModeNumnber.setText("0");

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

                        textViewExpenceDaye.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();
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


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddExpence();
            }
        });

        textViewExpenceDaye.setText(getDate());


        // Expence
        loadSpinnerData();

        ExpenceNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                DataBaseExpenceNameClass databaseProjectClass = dataBaseExpenceNameClassArrayList.get(position);
                String ExpenceID = String.valueOf(databaseProjectClass.getExpenceID());
                String ExpenceName = String.valueOf(databaseProjectClass.getExpenceName());
                editTextExpenceName.setText(ExpenceName);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
                editTextExpenceName.setText("Please Enter Expence");
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
        submitButton = (Button) findViewById(R.id.mSubmit);



        editTextExpenceName = (EditText) findViewById(R.id.expence_name);
        editTextExpencePrice = (EditText) findViewById(R.id.expence_Amount);
        textViewExpenceDaye = (TextView) findViewById(R.id.mtextdate);
        imageViewDatePicker = (ImageView) findViewById(R.id.mdatePicker);
        editTextexpenceDetails = (EditText) findViewById(R.id.expence_desc);

        spinner = (Spinner) findViewById(R.id.spinner);
        ExpenceNameSpinner = (Spinner) findViewById(R.id.spinnerExpenceName);
        editTextPaymentModeNumnber = (EditText) findViewById(R.id.expence_number);
        textInputEditText = (TextInputLayout) findViewById(R.id.input_expence_number);

    }

    public void AddExpence() {
        expencePaaymentNumber = "00";
        ProjectID = ProjectDetails.ProjectID;
        expenceID = "We Cannot Insert Here";
        expenceName = editTextExpenceName.getText().toString();
        expencePrice = editTextExpencePrice.getText().toString();
        expenceDate = textViewExpenceDaye.getText().toString();
        expenceDescription = editTextexpenceDetails.getText().toString();
        expencePaaymentNumber = editTextPaymentModeNumnber.getText().toString();

        if (expenceName.equals("") || expenceName.length() < 4) {
            editTextExpenceName.setError("Please insert Correct Expence Name , character must be greater than 3");
        } else if (expencePrice.equals("")) {
            editTextExpencePrice.setError("Please insert Correct Expence Price");
        } else {


            Log.e("Payment Mode", expencePaymentMode);
            Log.e("Payment Number", expencePaaymentNumber);

            long id = databaseHelper.ExpenceInsert(ProjectID, expenceName, expencePrice, expenceDate, expencePaymentMode, expencePaaymentNumber, expenceDescription);
            if (id == 0) {
               // Toast.makeText(AddExpence.this, "Not Inserted", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(AddExpence.this, "Inserted Row " + id, Toast.LENGTH_SHORT).show();
            }

            int checkAvailibility = checkAvailable(expenceName);

            if (checkAvailibility == 0) {
              //  Toast.makeText(this, " Update Expence Name", Toast.LENGTH_SHORT).show();
                long ids = databaseHelper.ExpenceNameInsert(expenceName);
                if (ids == 0) {
                } else {

                }
            } else {
               // Toast.makeText(this, "Not Update Expence Name", Toast.LENGTH_SHORT).show();

            }


            finish();
        }


    }

    public static String getDate() {

        long date = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("d-M-yyyy");
        String dateString = sdf.format(date);

        return dateString;
    }

    private void loadSpinnerData() {
        dataBaseExpenceNameClassArrayList.clear();
        // database handler
        // Spinner Drop down elements
        dataBaseExpenceNameClassArrayList.addAll(databaseHelper.ExpenceNameGetAll());

        adapterExpenceName = new AdapterExpenceName(AddExpence.this, dataBaseExpenceNameClassArrayList);

        // Set adapter to customerSpinner
        ExpenceNameSpinner.setAdapter(adapterExpenceName);
        adapterExpenceName.notifyDataSetChanged();


        for (int i = 0; i < dataBaseExpenceNameClassArrayList.size(); i++) {
            DataBaseExpenceNameClass dataBaseExpenceNameClass = dataBaseExpenceNameClassArrayList.get(i);
            String ExpenceID = String.valueOf(dataBaseExpenceNameClass.getExpenceID());
            String ExpenceName = dataBaseExpenceNameClass.getExpenceName();
            Log.e("Expence ID", ExpenceID);
            Log.e("Expence NaMe ", ExpenceName);
        }
    }

    private int checkAvailable(String string) {

//Pseudo code because I dont remember the API

        int index = 0;

        for (int i = 0; i < dataBaseExpenceNameClassArrayList.size(); i++) {

            DataBaseExpenceNameClass dataBaseExpenceNameClass = dataBaseExpenceNameClassArrayList.get(i);
            String checkString = dataBaseExpenceNameClass.getExpenceName();

            if (checkString.equalsIgnoreCase(string)) {
                index = 1;
            } else {
            }

        }

        return index;

    }

}

