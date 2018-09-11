package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectCustomer;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
/*
import android.widget.Toast;
*/

import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectHome.ProjectDetails;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.Calendar;

public class AddCustomer extends AppCompatActivity {
    Button backButton, submitButton;
    EditText editTextCustomerName, editTextCustomerMobileNumber, editTextCustomerAge, editTextCustomerCurrentAdress, editTextCustomerPanCard, editTextCustomerAdharCard;

    TextView textViewDateOfBirth;
    ImageView imageViewDatePicker;

    String ProjectID, customerID, customerName, customerMobileNumber, customerAddress,
            customerDateOfBirth, customerAge, customerPanCardNumber, customerPanCardImage, customerAdharCardNumber, customerStatus;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

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
                        String date = String.valueOf(year) + "-" + String.valueOf(monthOfYear + 1)
                                + "-" + String.valueOf(dayOfMonth);

                        textViewDateOfBirth.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCustomer();
            }
        });

    }

    public void getXMLContent() {
        backButton = (Button) findViewById(R.id.mbackButton);
        submitButton = (Button) findViewById(R.id.mSubmit);
        editTextCustomerName = (EditText) findViewById(R.id.medtCustomerName);
        editTextCustomerMobileNumber = (EditText) findViewById(R.id.medtCustomerMobileNumber);
        editTextCustomerCurrentAdress = (EditText) findViewById(R.id.medtCustomerAdress);
        editTextCustomerAge = (EditText) findViewById(R.id.medtCustomerAge);
        editTextCustomerPanCard = (EditText) findViewById(R.id.medtCustomerPanCard);
        editTextCustomerAdharCard = (EditText) findViewById(R.id.medtCustomerAdharCard);
        textViewDateOfBirth = (TextView) findViewById(R.id.mtextCustomerDobDatePicker);
        imageViewDatePicker = (ImageView) findViewById(R.id.mimgCustomerDobDatePicker);


    }

    public void AddCustomer() {
        ProjectID = ProjectDetails.ProjectID;
        customerID = "We Cannot Insert Here";
        customerName = editTextCustomerName.getText().toString();
        customerMobileNumber = editTextCustomerMobileNumber.getText().toString();
        customerAddress = editTextCustomerCurrentAdress.getText().toString();
        customerDateOfBirth = textViewDateOfBirth.getText().toString();
        customerPanCardNumber = editTextCustomerPanCard.getText().toString();
        customerAdharCardNumber = editTextCustomerAdharCard.getText().toString();
        customerAge = editTextCustomerAge.getText().toString();
        customerStatus = "NA";
        customerPanCardImage = "NA";

        if (customerName.equals("") || customerName.length() < 4) {
            editTextCustomerName.setError("Please enter Correct Customer Name");
        } else if (customerMobileNumber.equals("") || customerMobileNumber.length() < 10 || customerMobileNumber.startsWith("0")) {
            editTextCustomerMobileNumber.setError("Please enter Correct Mobile Number");
        } else {
            long id = databaseHelper.CustomerInsert(ProjectID, customerName, customerMobileNumber, customerAddress,
                    customerDateOfBirth, customerAge, customerPanCardNumber, customerPanCardImage, customerAdharCardNumber, customerStatus);

            if (id == 0) {
                //       Toast.makeText(AddCustomer.this, "Not Inserted", Toast.LENGTH_SHORT).show();
            } else {
//                Toast.makeText(AddCustomer.this, "Inserted Row " + id, Toast.LENGTH_SHORT).show();

            }
            finish();
        }
    }
}
