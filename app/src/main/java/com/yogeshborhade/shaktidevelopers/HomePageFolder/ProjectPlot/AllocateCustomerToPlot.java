package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectPlot;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.widget.Toast;

import com.yogeshborhade.shaktidevelopers.CreatePDf;
import com.yogeshborhade.shaktidevelopers.Database.Constants;
import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.BookPlotDataBase;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseCustomerClass;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.PojoCustomerSpinner;
import com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectCustomer.AddCustomer;
import com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectHome.ProjectDetails;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AllocateCustomerToPlot extends AppCompatActivity {
    Button backButton, submitButton, addNewCustomer;


    String projectId, plotAllocatedCustomerId = "0", plotid,
            plotNumber,
            plotEastSize, plotWestSize, plotNorthsize, plotSouthSize,
            plotRegisterDate, plotTotalAreaSqureFoot,
            plotRatePerSqureFoot, plotTotalSellingPrice,
            plotStatus, plotNotificationRemark, plotNotificationDate,
            plotGivenAmount, plotGivenAmountDate, plotRamainingAmount = "00", plotRemainingAmountCommitmentDate, plotRemark, plotPaymentMode, plotPaymentModenumber = "0";


    Spinner customerSpinner;

    DatabaseHelper databaseHelper;

    public List<DataBaseCustomerClass> dataBaseCustomerClassList = new ArrayList<>();
    public ArrayList<PojoCustomerSpinner> pojoCustomerList = new ArrayList<PojoCustomerSpinner>();
    public List<BookPlotDataBase> bookPlotDataBaseList = new ArrayList<>();
    AdapterBookPlotCustomerList customerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocate_customer_to_plot);

        databaseHelper = new DatabaseHelper(this);

        getXMLContent();

        getDetailsFromIntent();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        customerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                PojoCustomerSpinner pojoCustomerSpinner = pojoCustomerList.get(position);
                plotAllocatedCustomerId = pojoCustomerSpinner.getCustomerID();
                // Toast.makeText(BookPlot.this, "" + plotAllocatedCustomerId, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        addNewCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllocateCustomerToPlot.this, AddCustomer.class);
                startActivity(intent);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookPlot();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSpinnerData();
    }

    public void getXMLContent() {
        backButton = (Button) findViewById(R.id.mbackButton);
        submitButton = (Button) findViewById(R.id.mSubmit);
        addNewCustomer = (Button) findViewById(R.id.mAddNewcustomer);

        customerSpinner = (Spinner) findViewById(R.id.mspinner);


    }

    private void loadSpinnerData() {
        dataBaseCustomerClassList.clear();
        pojoCustomerList.clear();
        // database handler
        databaseHelper = new DatabaseHelper(getApplicationContext());

        // Spinner Drop down elements
        dataBaseCustomerClassList.addAll(databaseHelper.CustomerGetAll(ProjectDetails.ProjectID));

        for (int i = 0; i < dataBaseCustomerClassList.size(); i++) {
            DataBaseCustomerClass dataBaseCustomerClass = dataBaseCustomerClassList.get(i);
            String CustomerID = String.valueOf(dataBaseCustomerClass.getCustomerID());
            String CustomerName = dataBaseCustomerClass.getCustomerName();
            Log.e("Customer ID", CustomerID);
            Log.e("Customer ID", CustomerName);

            final PojoCustomerSpinner sched = new PojoCustomerSpinner();

            /******* Firstly take data in model object ******/
            sched.setCustomerID(CustomerID);
            sched.setCustomerNamer(CustomerName);


            /******** Take Model Object in ArrayList **********/
            pojoCustomerList.add(sched);
        }

        // Creating adapter for customerSpinner
        customerAdapter = new AdapterBookPlotCustomerList(AllocateCustomerToPlot.this, pojoCustomerList);

        // Set adapter to customerSpinner
        customerSpinner.setAdapter(customerAdapter);
        customerAdapter.notifyDataSetChanged();
    }

    public void getDetailsFromIntent() {
        Bundle bundle = getIntent().getExtras();
        projectId = bundle.getString("bookprojectID", "0");
        plotid = bundle.getString("bookPlotID", "0");
        plotEastSize = bundle.getString("bookPlotEast", "0");
        plotWestSize = bundle.getString("bookPlotWest", "0");
        plotNorthsize = bundle.getString("bookPlotNorth", "0");
        plotSouthSize = bundle.getString("bookPlotSouth", "0");

        plotTotalAreaSqureFoot = bundle.getString("bookTotalArea", "0");
        plotRatePerSqureFoot = bundle.getString("bookRatePerSqureFoot", "0");
        plotTotalSellingPrice = bundle.getString("bookTotalAmount", "0");
        plotTotalSellingPrice = bundle.getString("bookTotalAmount", "0");
        plotStatus = bundle.getString("booktstatus", "0");


    }

    public void bookPlot() {
        // insert in book plot

        if (plotAllocatedCustomerId.equals("") || plotAllocatedCustomerId.equals("0")) {
            Toast.makeText(this, "Please Select Customer", Toast.LENGTH_SHORT).show();
        } else {

            Log.e("projectId", projectId);
            Log.e("plotid", plotid);
            Log.e("plotAllocatedCustomerId", plotAllocatedCustomerId);


            // BOOK Plot
            long bookPlot = databaseHelper.BookPlot(projectId, plotid, plotAllocatedCustomerId);

            if (bookPlot == 0) {
              //  Toast.makeText(AllocateCustomerToPlot.this, "Not Inserted", Toast.LENGTH_SHORT).show();
            } else {
              //  Toast.makeText(AllocateCustomerToPlot.this, "Inserted Row " + bookPlot, Toast.LENGTH_SHORT).show();
            }

            GetAllocatedCustomer();

        }


    }

    private void GetAllocatedCustomer() {
        bookPlotDataBaseList.clear();
        // database handler
        databaseHelper = new DatabaseHelper(getApplicationContext());

        // Spinner Drop down elements
        bookPlotDataBaseList.addAll(databaseHelper.BookPlotGetAllAllocatedCustomerPlotId(String.valueOf(plotid), projectId));

        for (int i = 0; i < bookPlotDataBaseList.size(); i++) {
            BookPlotDataBase bookPlotDataBase = bookPlotDataBaseList.get(i);
            String CustomerID = bookPlotDataBase.getBookCustomerID();
            String bookedId = String.valueOf(bookPlotDataBase.getBookID());
            String PlotID = String.valueOf(bookPlotDataBase.getBookID());

            Log.e("customerID", CustomerID);
            Log.e("bookedId", bookedId);
            Log.e("PlotID", PlotID);

        }

    }


}
