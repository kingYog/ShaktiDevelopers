package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectCustomer;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.BookPlotDataBase;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseCustomerClass;
import com.yogeshborhade.shaktidevelopers.HomePageFolder.PanCard.AddPanCard;
import com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectHome.ProjectDetails;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CustomerUpdate extends AppCompatActivity {
    Button backButton, updateButton, deleteButton, AddPanCardButton;
    EditText editTextCustomerName, editTextCustomerMobileNumber, editTextCustomerAge, editTextCustomerCurrentAdress, editTextCustomerPanCard, editTextCustomerAdharCard,
            editTextCustomerStatus;


    TextView textViewProjectID, textViewCustomerID, textViewCustomerDateOfBirth;
    ImageView imageViewDOBDatePicker;

    String ProjectID, customerID, customerName, customerMobileNumber, customerAddress,
            customerDateOfBirth, customerAge, customerPanCardNumber, customerPanCardImage, customerAdharCardNumber, customerStatus;


    DatabaseHelper databaseHelper;
    private List<DataBaseCustomerClass> dataBaseCustomerClassList = new ArrayList<>();


    //    Plot List
    RecyclerView recyclerView;
    AdapterCustomerPlotDetails adapterCustomerPlotDetails;
    private List<BookPlotDataBase> bookPlotDataBases = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_customer);

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        databaseHelper = new DatabaseHelper(this);


        getXMLContent();

        Bundle bundle = getIntent().getExtras();
        ProjectID = bundle.getString("projectID", "0");
        customerID = bundle.getString("customerID", "0");
        Log.e(ProjectID, customerID);


        adapterCustomerPlotDetails = new AdapterCustomerPlotDetails(bookPlotDataBases);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(CustomerUpdate.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterCustomerPlotDetails);


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
                        textViewCustomerDateOfBirth.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCustomer();
            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteCustomer(Integer.parseInt(customerID));

            }
        });

        AddPanCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerUpdate.this, AddPanCard.class);

                intent.putExtra("customerID", String.valueOf(customerID));
                intent.putExtra("customerMob", String.valueOf(customerMobileNumber));
                intent.putExtra("customerPanCardImagePath", String.valueOf(customerPanCardImage));

                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        getSingleCustomer(Integer.parseInt(customerID), ProjectID);
        getPlotDetails();
    }

    public void getXMLContent() {
        backButton = (Button) findViewById(R.id.mbackButton);
        updateButton = (Button) findViewById(R.id.mUpdate);
        deleteButton = (Button) findViewById(R.id.mDelete);
        AddPanCardButton = (Button) findViewById(R.id.mAddPanCard);
        editTextCustomerName = (EditText) findViewById(R.id.medtCustomerName);
        editTextCustomerMobileNumber = (EditText) findViewById(R.id.medtCustomerMobileNumber);
        editTextCustomerAge = (EditText) findViewById(R.id.medtCustomerAge);
        editTextCustomerCurrentAdress = (EditText) findViewById(R.id.medtCustomerAdress);
        editTextCustomerPanCard = (EditText) findViewById(R.id.medtCustomerPanCard);
        editTextCustomerAdharCard = (EditText) findViewById(R.id.medtCustomerAdharCard);
        editTextCustomerStatus = (EditText) findViewById(R.id.medtCustomerStatus);
        textViewProjectID = (TextView) findViewById(R.id.mtextProjectId);
        textViewCustomerID = (TextView) findViewById(R.id.mtextCustomerID);
        textViewCustomerDateOfBirth = (TextView) findViewById(R.id.mtextCustomerDobDatePicker);
        imageViewDOBDatePicker = (ImageView) findViewById(R.id.mimgCustomerDobDatePicker);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    public void getSingleCustomer(int CustomerID, String projectID) {
        dataBaseCustomerClassList.addAll(databaseHelper.CustomerGetSingle(CustomerID, projectID));
        for (int i = 0; i <= dataBaseCustomerClassList.size() - 1; i++) {
            final DataBaseCustomerClass dataBaseCustomerClass = dataBaseCustomerClassList.get(i);

            ProjectID = dataBaseCustomerClass.getProjectID();
            customerID = String.valueOf(dataBaseCustomerClass.getCustomerID());
            customerName = dataBaseCustomerClass.getCustomerName();
            customerMobileNumber = dataBaseCustomerClass.getCustomerMobileNumber();
            customerAddress = dataBaseCustomerClass.getCustomerAddress();
            customerDateOfBirth = dataBaseCustomerClass.getCustomerDateOfBirth();
            customerAge = dataBaseCustomerClass.getCustomerAge();
            customerPanCardNumber = dataBaseCustomerClass.getCustomerPanCardNumber();
            customerPanCardImage = dataBaseCustomerClass.getCustomerPanCardImage();
            customerAdharCardNumber = dataBaseCustomerClass.getCustomerAdharCardNumber();
            customerStatus = dataBaseCustomerClass.getCustomerStatus();

            textViewProjectID.setText("Project ID " + (ProjectID));
            textViewCustomerID.setText("Customer ID " + customerID);
            textViewCustomerDateOfBirth.setText(customerDateOfBirth);


            editTextCustomerName.setText(customerName);
            editTextCustomerMobileNumber.setText(customerMobileNumber);
            editTextCustomerCurrentAdress.setText(customerAddress);
            editTextCustomerPanCard.setText(customerPanCardNumber);
            editTextCustomerAdharCard.setText(customerAdharCardNumber);
            editTextCustomerStatus.setText(customerStatus);
            editTextCustomerAge.setText(customerAge);

        }
    }

    public void getPlotDetails() {
        bookPlotDataBases.clear();
        bookPlotDataBases.addAll(databaseHelper.BookPlotGetPlotByCustomer(customerID, ProjectID));
        adapterCustomerPlotDetails.notifyDataSetChanged();
    }

    public void updateCustomer() {

        ProjectID = ProjectDetails.ProjectID;
        customerName = editTextCustomerName.getText().toString();
        customerMobileNumber = editTextCustomerMobileNumber.getText().toString();
        customerAddress = editTextCustomerCurrentAdress.getText().toString();
        customerDateOfBirth = textViewCustomerDateOfBirth.getText().toString();
        customerPanCardNumber = editTextCustomerPanCard.getText().toString();
        customerAdharCardNumber = editTextCustomerAdharCard.getText().toString();
        customerStatus = editTextCustomerStatus.getText().toString();
        customerAge = editTextCustomerAge.getText().toString();

        long id = databaseHelper.CustomerUpdate(ProjectID, customerID
                , customerName, customerMobileNumber, customerAddress,
                customerDateOfBirth, customerAge, customerPanCardNumber, customerAdharCardNumber, customerStatus);


        if (id == 0) {
          //  Toast.makeText(CustomerUpdate.this, "There is Some Errors", Toast.LENGTH_SHORT).show();

        } else {
         //   Toast.makeText(CustomerUpdate.this, "Updated Succefully", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteCustomer(int CustomerID) {
        long id = databaseHelper.CustomerDelete(CustomerID);

        if (id == 0) {
          //  Toast.makeText(CustomerUpdate.this, "Not Inserted", Toast.LENGTH_SHORT).show();
        } else {
         //   Toast.makeText(CustomerUpdate.this, "Deleted " + id, Toast.LENGTH_SHORT).show();
        }
        finish();
    }


}
