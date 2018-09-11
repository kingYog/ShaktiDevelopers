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

import com.yogeshborhade.shaktidevelopers.CreatePDf;
import com.yogeshborhade.shaktidevelopers.Database.Constants;
import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.BookPlotDataBase;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BookPlotAllocatedCustomerDetailsWithPrint extends AppCompatActivity {
    Button backButton,   viewInvoice, btnCancelBooking;
    TextInputLayout textInputLayoutPaymentModeNumber;
    EditText editTextBookingAmount, editTextNotificationRemark, editTextPaymentModeNumber;
    TextView textViewProjectId, textViewPlotId, textViewTotalArea, textViewRatePerSqurefoot, textViewTotalAmount,
            textViewAmountPayingDate, textViewRegistrationDate, textViewNextCommitmentDate, textViewNotificationDate, textViewRemainingAmount;
    ImageView imageViewAmountPayingDatePicker, imageViewRegistrationDatePicker, imageViewNextCommitmentDate, imageViewNotificationDate;


    String projectId, plotAllocatedCustomerId = "0", plotid,
            plotNumber,
            plotEastSize, plotWestSize, plotNorthsize, plotSouthSize,
            plotRegisterDate, plotTotalAreaSqureFoot,
            plotRatePerSqureFoot, plotTotalSellingPrice,
            plotStatus, plotNotificationRemark, plotNotificationDate,
            plotGivenAmount, plotGivenAmountDate, plotRamainingAmount = "00", plotRemainingAmountCommitmentDate, plotRemark, plotPaymentMode, plotPaymentModenumber = "0";

    String bookID, CustomerID;
    Spinner  paymentModeSpinner;

    DatabaseHelper databaseHelper;

    public List<BookPlotDataBase> bookPlotDataBases = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocated_customer_details_with_print);
        databaseHelper = new DatabaseHelper(this);

        getXMLContent();

        getDetailsFromIntent();

        if (plotStatus.equals(Constants.PLOTBOOK)) {
            Toast.makeText(this, "Plot Already Booked ", Toast.LENGTH_SHORT).show();
            getBookedPlotDetails();
            btnCancelBooking.setVisibility(View.VISIBLE);


        } else if (plotStatus.equals(Constants.PLOTNOTBOOK)) {

            Toast.makeText(this, "Plot Not Booked", Toast.LENGTH_SHORT).show();

            btnCancelBooking.setVisibility(View.GONE);

            editTextBookingAmount.setEnabled(true);
            editTextNotificationRemark.setEnabled(true);
            editTextPaymentModeNumber.setEnabled(true);

            imageViewAmountPayingDatePicker.setEnabled(true);
            imageViewRegistrationDatePicker.setEnabled(true);
            imageViewNextCommitmentDate.setEnabled(true);
            imageViewNotificationDate.setEnabled(true);

        } else {
            Toast.makeText(this, "Something Went Wrong Please contact Developer", Toast.LENGTH_SHORT).show();
        }


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
                    textViewRemainingAmount.setText(String.valueOf(Double.valueOf(plotTotalSellingPrice) - Double.valueOf(editTextBookingAmount.getText().toString())));
                } else {
                    textViewRemainingAmount.setText("0");
                }


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


        viewInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewInvoice();
            }
        });
        btnCancelBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookPlotCancel();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void getXMLContent() {
        backButton = (Button) findViewById(R.id.mbackButton);
        viewInvoice = (Button) findViewById(R.id.mViewInvoic);
        btnCancelBooking = (Button) findViewById(R.id.mCancelBooking);

        paymentModeSpinner = (Spinner) findViewById(R.id.mpaymentModeSpinner);
        editTextBookingAmount = (EditText) findViewById(R.id.medtBookingAmount);
        editTextNotificationRemark = (EditText) findViewById(R.id.medtPlotNotificationRemark);


        textViewProjectId = (TextView) findViewById(R.id.mprojectID);
        textViewPlotId = (TextView) findViewById(R.id.mplotID);
        textViewNotificationDate = (TextView) findViewById(R.id.mtextCustomerCustomerRemarkDatePicker);

        textViewTotalArea = (TextView) findViewById(R.id.mTotalArea);
        textViewRatePerSqurefoot = (TextView) findViewById(R.id.mRatePerSqureFoot);
        textViewTotalAmount = (TextView) findViewById(R.id.mTotalAmMount);
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


    }


    public void getDetailsFromIntent() {
        Bundle bundle = getIntent().getExtras();
        projectId = bundle.getString("bookprojectID", "0");
        bookID = bundle.getString("bookID", "0");
        plotid = bundle.getString("bookPlotID", "0");
        CustomerID = bundle.getString("customerID", "0");
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
        textViewTotalArea.setText(plotTotalAreaSqureFoot);
        textViewRatePerSqurefoot.setText(plotRatePerSqureFoot);
        textViewTotalAmount.setText(plotTotalSellingPrice);


    }


    public void ViewInvoice() {
        if (editTextBookingAmount.getText().toString().equals("") || editTextBookingAmount.getText().toString().equals("0")) {
            editTextBookingAmount.setError("Please Enter Given Amount");
        } else {

            plotTotalSellingPrice = textViewTotalAmount.getText().toString();
            plotTotalAreaSqureFoot = textViewTotalArea.getText().toString();
            plotRegisterDate = textViewRegistrationDate.getText().toString();
            plotGivenAmount = editTextBookingAmount.getText().toString();
            plotRamainingAmount = String.valueOf(Double.valueOf(plotTotalSellingPrice) - Double.valueOf(editTextBookingAmount.getText().toString()));
            plotRemainingAmountCommitmentDate = textViewNextCommitmentDate.getText().toString();

            Intent intent = new Intent(this, CreatePDf.class);
            intent.putExtra("customerID", CustomerID);
            intent.putExtra("plotID", plotid);
            intent.putExtra("projectID", projectId);
            intent.putExtra("totalArea", plotTotalAreaSqureFoot);
            intent.putExtra("totalPrice", plotTotalSellingPrice);
            intent.putExtra("givenAmount", plotGivenAmount);
            intent.putExtra("remainingAmount", plotRamainingAmount);
            intent.putExtra("registrationDate", plotRegisterDate);
            intent.putExtra("nextcommitmentDate", plotRemainingAmountCommitmentDate);


            startActivity(intent);

        }
    }

    public void getBookedPlotDetails() {

        bookPlotDataBases.clear();
        bookPlotDataBases.addAll(databaseHelper.BookPlotGetBookedSinglePlotByPlotId(plotid, projectId));

        for (int i = 0; i < bookPlotDataBases.size(); i++) {
            BookPlotDataBase bookPlotDataBase = bookPlotDataBases.get(i);

            projectId = bookPlotDataBase.getBookProjectID();
            CustomerID = bookPlotDataBase.getBookCustomerID();
            plotid = bookPlotDataBase.getBookPlotID();
            plotRegisterDate = bookPlotDataBase.getBookDate();
            plotTotalAreaSqureFoot = bookPlotDataBase.getBookTotalArea();
            plotRatePerSqureFoot = bookPlotDataBase.getBookRatePerSqureFoot();
            plotTotalSellingPrice = bookPlotDataBase.getBookTotalAmount();
            plotStatus = bookPlotDataBase.getBookStatus();
            plotNotificationRemark = bookPlotDataBase.getBook_Notification_Remark();
            plotNotificationDate = bookPlotDataBase.getBook_Notification_Remark_Date();
            plotGivenAmount = bookPlotDataBase.getBookGivenAmount();
            plotGivenAmountDate = bookPlotDataBase.getBookGivenAmountDate();
            plotRamainingAmount = bookPlotDataBase.getBookRamainingAmount();
            plotRemainingAmountCommitmentDate = bookPlotDataBase.getBookRemainingAmountCommitmentDate();
            plotPaymentMode = bookPlotDataBase.getBookPaymentMode();
            plotPaymentModenumber = bookPlotDataBase.getBookPaymentModeNNumber();

            editTextPaymentModeNumber.setText(plotPaymentModenumber);
            editTextBookingAmount.setText(plotGivenAmount);
          //  editTextNotificationRemark.setText(plotNotificationRemark);

            textViewProjectId.setText(projectId);
            textViewPlotId.setText(plotid);
            textViewTotalArea.setText(plotTotalAreaSqureFoot);
            textViewRatePerSqurefoot.setText(plotRatePerSqureFoot);
            textViewTotalAmount.setText(plotTotalSellingPrice);
            textViewAmountPayingDate.setText(plotGivenAmountDate);
            textViewRegistrationDate.setText(plotRegisterDate);
            textViewNextCommitmentDate.setText(plotRemainingAmountCommitmentDate);
           // textViewNotificationDate.setText(plotNotificationDate);
            textViewRemainingAmount.setText(plotRamainingAmount);


            editTextBookingAmount.setEnabled(false);
            editTextNotificationRemark.setEnabled(false);
            editTextPaymentModeNumber.setEnabled(false);
            editTextPaymentModeNumber.setVisibility(View.VISIBLE);

            imageViewAmountPayingDatePicker.setEnabled(false);
            imageViewRegistrationDatePicker.setEnabled(false);
            imageViewNextCommitmentDate.setEnabled(false);
            imageViewNotificationDate.setEnabled(false);


        }

    }

    public void bookPlotCancel() {
        // insert into plot
        // insert in book plot
        // insert into customer
        // insert into transaction
        // insert into notification


        plotRegisterDate = "NA";
        plotAllocatedCustomerId = "NA";
        plotStatus = Constants.PLOTNOTBOOK;
        plotRemark = "Plot Not Booked";
        plotPaymentModenumber = editTextPaymentModeNumber.getText().toString();
        Log.e("plotid", plotid);
        Log.e("plotAllocatedCustomerId", plotAllocatedCustomerId);
        Log.e("plotRegisterDate", plotRegisterDate);
        Log.e("plotStatus", plotStatus);


        // Delete Book Plot
        long bookPlot = databaseHelper.BookPlotDelete(Integer.parseInt(bookID));
        if (bookPlot == 0) {
//            Toast.makeText(BookPlotAllocatedCustomerDetailsWithPrint.this, "Book Plot Not Deleted", Toast.LENGTH_SHORT).show();
        } else {
  //          Toast.makeText(BookPlotAllocatedCustomerDetailsWithPrint.this, "Book Plot Deleted" + bookPlot, Toast.LENGTH_SHORT).show();
        }


        // Update  Main Plot Details
        long id = databaseHelper.PlotUpdate(plotid, plotAllocatedCustomerId, plotRegisterDate
                , plotStatus, plotRemark);

        if (id == 0) {
    //        Toast.makeText(BookPlotAllocatedCustomerDetailsWithPrint.this, "Plot Not Updated", Toast.LENGTH_SHORT).show();
        } else {
      //      Toast.makeText(BookPlotAllocatedCustomerDetailsWithPrint.this, "Plot Updated", Toast.LENGTH_SHORT).show();
        }



        // Delete Transaction using for Plot id Presernt
        long insertTransaction = databaseHelper.TransactionDeleteByPlotAndCustomer(plotid, CustomerID);

        if (insertTransaction == 0) {

        //    Toast.makeText(BookPlotAllocatedCustomerDetailsWithPrint.this, "Transaction Not Deleted ", Toast.LENGTH_SHORT).show();

        } else {

          //  Toast.makeText(BookPlotAllocatedCustomerDetailsWithPrint.this, "Transaction  Deleted ", Toast.LENGTH_SHORT).show();

        }

        finish();

        // Notification Cant be deleted


    }

}
