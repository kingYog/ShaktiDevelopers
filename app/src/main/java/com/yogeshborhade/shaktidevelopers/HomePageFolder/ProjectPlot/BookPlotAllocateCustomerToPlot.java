package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectPlot;

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
import android.widget.Toast;

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

import static com.yogeshborhade.shaktidevelopers.Database.Constants.numberWithoutExponential;

public class BookPlotAllocateCustomerToPlot extends AppCompatActivity {
    Button backButton, submitButton, addNewCustomer;
    TextInputLayout textInputLayoutPaymentModeNumber;
    EditText editTextBookingAmount, editTextNotificationRemark, editTextPaymentModeNumber, editTextPlotTotalSqureFooot,
            editTextPlotPricePerSqureFoot,
            editTextTotalPlotSellingPrice;
    TextView textViewProjectId, textViewPlotId,
            textViewAmountPayingDate, textViewRegistrationDate, textViewNextCommitmentDate, textViewNotificationDate, textViewRemainingAmount;
    ImageView imageViewAmountPayingDatePicker, imageViewRegistrationDatePicker, imageViewNextCommitmentDate, imageViewNotificationDate;


    String projectId, plotAllocatedCustomerId = "0", plotid,
            plotNumber,
            plotRegisterDate, plotTotalAreaSqureFoot,
            plotRatePerSqureFoot, plotTotalSellingPrice,
            plotStatus, plotNotificationRemark, plotNotificationDate,
            plotGivenAmount, plotGivenAmountDate, plotRamainingAmount = "00", plotRemainingAmountCommitmentDate, plotRemark, plotPaymentMode="NA", plotPaymentModenumber = "0";

    public static String plotEastSize, plotWestSize, plotNorthsize, plotSouthSize;
    Spinner customerSpinner, paymentModeSpinner;

    DatabaseHelper databaseHelper;

    public List<DataBaseCustomerClass> dataBaseCustomerClassList = new ArrayList<>();
    public ArrayList<PojoCustomerSpinner> pojoCustomerList = new ArrayList<PojoCustomerSpinner>();
    public List<BookPlotDataBase> bookPlotDataBases = new ArrayList<>();
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

        imageViewAmountPayingDatePicker.setOnClickListener(new View.OnClickListener() {
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

                        textViewAmountPayingDate.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });

        imageViewRegistrationDatePicker.setOnClickListener(new View.OnClickListener() {
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

                        textViewRegistrationDate.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });

        imageViewNextCommitmentDate.setOnClickListener(new View.OnClickListener() {
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

                        textViewNextCommitmentDate.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });

        imageViewNotificationDate.setOnClickListener(new View.OnClickListener() {
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

                        textViewNotificationDate.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });

        customerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                PojoCustomerSpinner pojoCustomerSpinner = pojoCustomerList.get(position);
                plotAllocatedCustomerId = pojoCustomerSpinner.getCustomerID();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        editTextBookingAmount.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() != 0) {
                    textViewRemainingAmount.setText(String.valueOf(numberWithoutExponential.format(Double.valueOf(editTextTotalPlotSellingPrice.getText().toString()) - Double.valueOf(editTextBookingAmount.getText().toString()))));
                } else {
                    textViewRemainingAmount.setText("0");
                }


            }
        });

        editTextPlotPricePerSqureFoot.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (s.length() != 0) {
                    editTextTotalPlotSellingPrice.setText(String.valueOf(numberWithoutExponential.format(Double.valueOf(editTextPlotPricePerSqureFoot.getText().toString()) * Double.valueOf(editTextPlotTotalSqureFooot.getText().toString()))));
                } else {
                    editTextTotalPlotSellingPrice.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


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
        paymentModeSpinner.setAdapter(dataAdapter);

        paymentModeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String item = parentView.getItemAtPosition(position).toString();

                if (plotStatus.equals(Constants.PLOTBOOK)) {
                    textInputLayoutPaymentModeNumber.setVisibility(View.VISIBLE);
                } else {


                    if (position == 0) {
                        textInputLayoutPaymentModeNumber.setVisibility(View.GONE);
                        editTextPaymentModeNumber.setText("0");
                    } else {
                        textInputLayoutPaymentModeNumber.setVisibility(View.VISIBLE);
                        editTextPaymentModeNumber.setText("0");

                    }

                }
                plotPaymentMode = item;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        addNewCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookPlotAllocateCustomerToPlot.this, AddCustomer.class);
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
        paymentModeSpinner = (Spinner) findViewById(R.id.mpaymentModeSpinner);
        editTextBookingAmount = (EditText) findViewById(R.id.medtBookingAmount);
        editTextNotificationRemark = (EditText) findViewById(R.id.medtPlotNotificationRemark);


        textViewProjectId = (TextView) findViewById(R.id.mprojectID);
        textViewPlotId = (TextView) findViewById(R.id.mplotID);
        textViewNotificationDate = (TextView) findViewById(R.id.mtextCustomerCustomerRemarkDatePicker);


        textViewAmountPayingDate = (TextView) findViewById(R.id.mtextAmountPayintgPicker);
        textViewRegistrationDate = (TextView) findViewById(R.id.mtextregistrationDate);
        textViewNextCommitmentDate = (TextView) findViewById(R.id.mtextnextCommitMentDate);
        textViewRemainingAmount = (TextView) findViewById(R.id.mtextRemainingAmount);
        imageViewAmountPayingDatePicker = (ImageView) findViewById(R.id.mimgAmountPayingDatePicker);
        imageViewRegistrationDatePicker = (ImageView) findViewById(R.id.mimgRegistrationDatePicker);
        imageViewNextCommitmentDate = (ImageView) findViewById(R.id.mimgNextCommitmentDatePicker);
        imageViewNotificationDate = (ImageView) findViewById(R.id.mimgCustomerNotificationRemarkDatePicker);

        textInputLayoutPaymentModeNumber = (TextInputLayout) findViewById(R.id.input_expence_number);
        editTextPaymentModeNumber = (EditText) findViewById(R.id.expence_number);


        editTextPlotTotalSqureFooot = (EditText) findViewById(R.id.medtTotalSqureFoot);
        editTextPlotPricePerSqureFoot = (EditText) findViewById(R.id.medtPlotPricePerSqureFoot);
        editTextTotalPlotSellingPrice = (EditText) findViewById(R.id.medtPlotPriceSelligPrice);

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
        customerAdapter = new AdapterBookPlotCustomerList(BookPlotAllocateCustomerToPlot.this, pojoCustomerList);

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


        // Set To TextView

        textViewProjectId.setText(projectId);
        textViewPlotId.setText(plotid);

        editTextPlotTotalSqureFooot.setText(plotTotalAreaSqureFoot);
        editTextPlotPricePerSqureFoot.setText(plotRatePerSqureFoot);
        editTextTotalPlotSellingPrice.setText(plotTotalSellingPrice);


    }

    public void bookPlot() {
        // insert into plot
        // insert in book plot
        // insert into customer
        // insert into transaction
        // insert into notification
        if (plotAllocatedCustomerId.equals("") || plotAllocatedCustomerId.equals("0")) {
            Toast.makeText(this, "Please Select Customer", Toast.LENGTH_SHORT).show();
        } else if (editTextBookingAmount.getText().toString().equals("") || editTextBookingAmount.getText().toString().equals("0")) {
            editTextBookingAmount.setError("Please Enter Given Amount");
        } else {

            plotRatePerSqureFoot = editTextPlotPricePerSqureFoot.getText().toString();
            plotTotalAreaSqureFoot = editTextPlotTotalSqureFooot.getText().toString();
            plotTotalSellingPrice = editTextTotalPlotSellingPrice.getText().toString();

            plotRegisterDate = textViewRegistrationDate.getText().toString();
            plotStatus = Constants.PLOTBOOK;
            plotNotificationRemark = editTextNotificationRemark.getText().toString();
            plotNotificationDate = textViewNotificationDate.getText().toString();
            plotGivenAmount = editTextBookingAmount.getText().toString();
            plotGivenAmountDate = textViewAmountPayingDate.getText().toString();
            plotRamainingAmount = String.valueOf(numberWithoutExponential.format(Double.valueOf(plotTotalSellingPrice) - Double.valueOf(editTextBookingAmount.getText().toString())));
            plotRemainingAmountCommitmentDate = textViewNextCommitmentDate.getText().toString();
            plotRemark = "Plot Booking Amount";

            plotPaymentModenumber = editTextPaymentModeNumber.getText().toString();
            Log.e("projectId", projectId);
            Log.e("plotid", plotid);
            Log.e("plotAllocatedCustomerId", plotAllocatedCustomerId);
            Log.e("plotRegisterDate", plotRegisterDate);
            Log.e("plotTotalAreaSqureFoot", plotTotalAreaSqureFoot);
            Log.e("plotRatePerSqureFoot", plotRatePerSqureFoot);
            Log.e("plotTotalSellingPrice", plotTotalSellingPrice);
            Log.e("plotGivenAmount", plotGivenAmount);
            Log.e("plotGivenAmountDate", plotGivenAmountDate);
            Log.e("plotGivenAmount", plotGivenAmount);
            Log.e("plotRamainingAmount", plotRamainingAmount);
            Log.e("RemainingCommintDate", plotRemainingAmountCommitmentDate);
            Log.e("plotNotificationRemark", plotNotificationRemark);
            Log.e("plotNotificationDate", plotNotificationDate);
            Log.e("plotStatus", plotStatus);

            // BOOK Plot
            long bookPlot = databaseHelper.BookPlot(projectId, plotid, plotAllocatedCustomerId, plotRegisterDate, plotTotalAreaSqureFoot,
                    plotRatePerSqureFoot, plotTotalSellingPrice, plotGivenAmount, plotGivenAmountDate, plotRamainingAmount
                    , plotRemainingAmountCommitmentDate, "Other Charges", plotPaymentMode, plotPaymentModenumber, plotNotificationRemark, plotNotificationDate, plotStatus);

            if (bookPlot == 0) {
               // Toast.makeText(BookPlotAllocateCustomerToPlot.this, "Not Inserted", Toast.LENGTH_SHORT).show();
            } else {
               // Toast.makeText(BookPlotAllocateCustomerToPlot.this, "Inserted Row " + bookPlot, Toast.LENGTH_SHORT).show();
                // Transactions
                long insertTransaction = databaseHelper.TransactionInsert(projectId, plotid, plotAllocatedCustomerId, "NA", plotGivenAmount,
                        Constants.TRASACTION_DONE_BY_CUSTOMER, Constants.TRASACTION_MONEY_GIVE, plotGivenAmountDate,plotPaymentMode, plotPaymentModenumber, Constants.UNREAD);

                if (insertTransaction == 0) {

                 //   Toast.makeText(BookPlotAllocateCustomerToPlot.this, "Not Inserted", Toast.LENGTH_SHORT).show();

                } else {
                 //   Toast.makeText(BookPlotAllocateCustomerToPlot.this, "Inserted Transaction ", Toast.LENGTH_SHORT).show();
                }
            }


            // Update  Plot
            long id = databaseHelper.PlotBookInsert(plotid, plotAllocatedCustomerId, plotRegisterDate
                    , plotStatus, plotRemark);

            if (id == 0) {
               // Toast.makeText(BookPlotAllocateCustomerToPlot.this, "Not Inserted", Toast.LENGTH_SHORT).show();
            } else {
               // Toast.makeText(BookPlotAllocateCustomerToPlot.this, "Inserted Row ", Toast.LENGTH_SHORT).show();
            }




            // Notification
           /* long notificationInsert = databaseHelper.NotificationInsert(projectId, plotNotificationDate,
                    "NA", "Notification For Customer",
                    plotNotificationRemark, Constants.CUSTOMER, plotAllocatedCustomerId, Constants.UNREAD);

            if (notificationInsert == 0) {
               // Toast.makeText(BookPlotAllocateCustomerToPlot.this, "Not Inserted", Toast.LENGTH_SHORT).show();
            } else {
               // Toast.makeText(BookPlotAllocateCustomerToPlot.this, "Inserted Notification", Toast.LENGTH_SHORT).show();

            }*/

            finish();

        }


    }


}


