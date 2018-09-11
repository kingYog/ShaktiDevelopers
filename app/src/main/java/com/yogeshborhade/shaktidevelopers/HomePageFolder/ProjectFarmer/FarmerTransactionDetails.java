package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectFarmer;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yogeshborhade.shaktidevelopers.Database.Constants;
import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseTransaction;
import com.yogeshborhade.shaktidevelopers.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.yogeshborhade.shaktidevelopers.Database.Constants.numberWithoutExponential;

public class FarmerTransactionDetails extends AppCompatActivity {
    RecyclerView recyclerView;

    Button btnAddTransaction, backButton;
    TextView textViewTotalAmount;

    String ProjectID = "NA", CustFarmID = "NA", CustFarmAmountRescieved = "NA", CustFarmNotificationrRemark = "NA", farmerPlotId = "NA",
            CustFarmAmountResCievedDate = "NA", transactionDoneBy = "NA", transactionType = "NA", transactionCheckNumber = "NA", transactionMoneyGiveTake = "NA";

    public static String TotalAmount, GivenAmount, PendingAmount;
    double newGivenAmount, newPendingAmount;
    DatabaseHelper databaseHelper;
    private List<DataBaseTransaction> dataBaseTransactionArrayList = new ArrayList<>();

    AdapterFarmerTransaction adapterTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_transaction_details);

        databaseHelper = new DatabaseHelper(this);

        Bundle bundle = getIntent().getExtras();

        ProjectID = bundle.getString("projectID", "0");
        CustFarmID = bundle.getString("CustFarmID", "0");
        TotalAmount = bundle.getString("total", "0");
        GivenAmount = bundle.getString("given", "0");
        PendingAmount = bundle.getString("pending", "0");

        Log.e(ProjectID, CustFarmID);

        getXMLContent();

        adapterTransaction = new AdapterFarmerTransaction(dataBaseTransactionArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(FarmerTransactionDetails.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterTransaction);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnAddTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addNewTransactionFarmer();

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        getSingleTransactionFarmer(ProjectID, CustFarmID, Constants.TRASACTION_DONE_BY_OWENER);
        int i = databaseHelper.getTotalOfFarmerTransactions(ProjectID, CustFarmID, Constants.TRASACTION_DONE_BY_OWENER);
        if (i == 0) {
            Log.e("TotalValues", "0");
        } else {
            Log.e("TotalValues", String.valueOf(i));
            textViewTotalAmount.setText("Total Amount :-" + String.valueOf(i));
        }

    }


    public void getSingleTransactionFarmer(String ProjectID, String CuFaID, String doneBy) {

        dataBaseTransactionArrayList.clear();
        dataBaseTransactionArrayList.addAll(databaseHelper.TransactionGetSingleFarmer(ProjectID, CuFaID, doneBy));

        adapterTransaction.notifyDataSetChanged();

        for (int i = 0; i <= dataBaseTransactionArrayList.size() - 1; i++) {
            final DataBaseTransaction dataBaseFarmerClass = dataBaseTransactionArrayList.get(i);

            int TID = dataBaseFarmerClass.getTransaction_id();
            Log.e("Transaction Id ", String.valueOf(TID));


        }
    }


    public void getXMLContent() {
        backButton = (Button) findViewById(R.id.mbackButton);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        btnAddTransaction = (Button) findViewById(R.id.mbtnAddTransaction);
        textViewTotalAmount = (TextView) findViewById(R.id.mtexTotalTransactionAmount);
    }


    public void addNewTransactionFarmer() {
        Button buttonSubmit, buttonCancle;
        ImageView imageViewDatePicker;
        Spinner paymentModespinner;
        final EditText editTextTransactionAmount, editTextRemark, editTextCheckNummber;
        final TextView textDate;
        final TextInputLayout textInputLayoutPaymentMode;
        final RadioGroup radioGroupGivenTake;
        RadioButton radioButtonGive, radioButtonTake;


        final Dialog dialog = new Dialog(FarmerTransactionDetails.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dilog_transaction);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // set the custom dialog components - text, image and button

        buttonSubmit = (Button) dialog.findViewById(R.id.mSubmit);
        buttonCancle = (Button) dialog.findViewById(R.id.mCancle);
        imageViewDatePicker = (ImageView) dialog.findViewById(R.id.mdatePicker);
        textDate = (TextView) dialog.findViewById(R.id.mtextdate);

        editTextTransactionAmount = (EditText) dialog.findViewById(R.id.mtransactionAmount);
        editTextCheckNummber = (EditText) dialog.findViewById(R.id.mtransaction_CheckNumber);
        editTextRemark = (EditText) dialog.findViewById(R.id.mtransaction_remark);
        textInputLayoutPaymentMode = (TextInputLayout) dialog.findViewById(R.id.input_payment_mode);

        radioGroupGivenTake = (RadioGroup) dialog.findViewById(R.id.radioGroup);
        radioButtonGive = (RadioButton) dialog.findViewById(R.id.radioButton);
        radioButtonTake = (RadioButton) dialog.findViewById(R.id.radioButton2);

        paymentModespinner = (Spinner) dialog.findViewById(R.id.mpaymentModeSpinner);

        textInputLayoutPaymentMode.setVisibility(View.GONE);
        textDate.setText(getDate());
        radioGroupGivenTake.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if (i == R.id.radioButton) {
                    Toast.makeText(FarmerTransactionDetails.this, "Give", Toast.LENGTH_SHORT).show();
                    //Your code
                } else if (i == R.id.radioButton2) {
                    Toast.makeText(FarmerTransactionDetails.this, "Take", Toast.LENGTH_SHORT).show();

                    //Your code
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
        paymentModespinner.setAdapter(dataAdapter);

        paymentModespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String item = parentView.getItemAtPosition(position).toString();

                if (position == 0) {
                    textInputLayoutPaymentMode.setVisibility(View.GONE);
                    editTextCheckNummber.setText("0");
                } else {
                    textInputLayoutPaymentMode.setVisibility(View.VISIBLE);
                    editTextCheckNummber.setText("0");

                }
                transactionType = item;
                // Showing selected spinner item
                //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
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
                        textDate.setText(date);

                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RadioButton rbGiveTakeTypeIS = (RadioButton) radioGroupGivenTake.findViewById(radioGroupGivenTake.getCheckedRadioButtonId());


                CustFarmAmountRescieved = editTextTransactionAmount.getText().toString();
                transactionCheckNumber = editTextCheckNummber.getText().toString();
                CustFarmNotificationrRemark = editTextRemark.getText().toString();
                CustFarmAmountResCievedDate = textDate.getText().toString();
                transactionDoneBy = Constants.TRASACTION_DONE_BY_OWENER;


                if (rbGiveTakeTypeIS.getText().equals("Give")) {
                    transactionMoneyGiveTake = Constants.TRASACTION_MONEY_GIVE;
                } else {
                    transactionMoneyGiveTake = Constants.TRASACTION_MONEY_TAKE;
                }


                if (editTextTransactionAmount.getText().toString().equals("")) {
                    editTextTransactionAmount.setError("Please Enter Amount");
                } else {

                    // Add Transaction
                    long insertTransaction = databaseHelper.TransactionInsert(ProjectID, "NA", "NA", CustFarmID, CustFarmAmountRescieved,
                            transactionDoneBy, transactionMoneyGiveTake, CustFarmAmountResCievedDate, transactionType, transactionCheckNumber, CustFarmNotificationrRemark);

                    if (insertTransaction == 0) {
                      //  Toast.makeText(FarmerTransactionDetails.this, "Not Inserted", Toast.LENGTH_SHORT).show();

                    } else {
                        //Toast.makeText(FarmerTransactionDetails.this, "Inserted Transaction ", Toast.LENGTH_SHORT).show();
                    }


                    newGivenAmount = Double.valueOf(GivenAmount) + Double.valueOf(CustFarmAmountRescieved);
                    newPendingAmount = Double.valueOf(PendingAmount) - Double.valueOf(CustFarmAmountRescieved);

                    GivenAmount = String.valueOf(numberWithoutExponential.format(newGivenAmount));
                    PendingAmount = String.valueOf(numberWithoutExponential.format(newPendingAmount));

                    //Update Farmer Plot
                    long UpdateFarmer = databaseHelper.FarmerUpdateAmount(ProjectID, CustFarmID, String.valueOf(numberWithoutExponential.format(newGivenAmount)), String.valueOf(numberWithoutExponential.format(newPendingAmount)));
                    if (UpdateFarmer == 0) {
                        //Toast.makeText(FarmerTransactionDetails.this, "Not Inserted", Toast.LENGTH_SHORT).show();

                    } else {
                        //Toast.makeText(FarmerTransactionDetails.this, "Inserted Farmer ", Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();

                    getSingleTransactionFarmer(ProjectID, CustFarmID, Constants.TRASACTION_DONE_BY_OWENER);


                }
            }
        });

        buttonCancle.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    public static String getDate() {

        long date = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATEFORMATE);
        String dateString = sdf.format(date);

        return dateString;
    }


}

