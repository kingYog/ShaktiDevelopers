package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectTransaction;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class TransactionDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    Button btnAddTransaction, backButton;
    TextView textViewTotalAmount;

    String ComingFrom, ProjectID = "NA", CustFarmID = "NA", CustFarmAmountRescieved = "NA", CustFarmNotificationrRemark = "NA", farmerPlotId = "NA",
            CustFarmAmountResCievedDate = "NA", transactionDoneBy = "NA", transactionType = "NA", transactionCheckNumber = "NA", transactionMoneyGiveTake = "NA";
    DatabaseHelper databaseHelper;
    private List<DataBaseTransaction> dataBaseTransactionArrayList = new ArrayList<>();

    AdapterTransaction adapterTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_details);

        databaseHelper = new DatabaseHelper(this);

        Bundle bundle = getIntent().getExtras();

        ProjectID = bundle.getString("projectID", "0");
        CustFarmID = bundle.getString("CustFarmID", "0");
        ComingFrom = bundle.getString("from", "0");
        Log.e(ProjectID, CustFarmID);

        getXMLContent();

        adapterTransaction = new AdapterTransaction(dataBaseTransactionArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(TransactionDetails.this);
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
                if (ComingFrom.equals(Constants.CUSTOMER)) {
                    addNewTransactionCustomer();
                } else if (ComingFrom.equals(Constants.FARMER)) {
                    addNewTransactionFarmer();
                } else {

                }
            }
        });

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);

        // Setup refresh listener which triggers new data loading

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override

            public void onRefresh() {

                // Your code to refresh the list here.

                // Make sure you call swipeContainer.setRefreshing(false)

                // once the network request has completed successfully.

                if (ComingFrom.equals(Constants.CUSTOMER)) {
                   /* getSingleTransactionCustomer(ProjectID, CustFarmID, Constants.TRASACTION_DONE_BY_OWENER);

                    int i = databaseHelper.getTotalOfCustomerTransactions(ProjectID, CustFarmID, Constants.TRASACTION_DONE_BY_OWENER);
                    if (i == 0) {
                        Log.e("TotalValues", "0");
                    } else {
                        Log.e("TotalValues", String.valueOf(i));
                        textViewTotalAmount.setText("Total Amount :-" + String.valueOf(i));
                    }*/
                } else if (ComingFrom.equals(Constants.FARMER)) {
                    getSingleTransactionFarmer(ProjectID, CustFarmID, Constants.TRASACTION_DONE_BY_OWENER);


                    int i = databaseHelper.getTotalOfFarmerTransactions(ProjectID, CustFarmID, Constants.TRASACTION_DONE_BY_OWENER);
                    if (i == 0) {
                        Log.e("TotalValues", "0");
                    } else {
                        Log.e("TotalValues", String.valueOf(i));
                        textViewTotalAmount.setText("Total Amount :-" + String.valueOf(i));
                    }

                } else {

                }
                mSwipeRefreshLayout.setRefreshing(false);
            }

        });

        // Configure the refreshing colors

        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,

                android.R.color.holo_green_light,

                android.R.color.holo_orange_light,

                android.R.color.holo_red_light);



    }

    @Override
    protected void onResume() {
        super.onResume();


        if (ComingFrom.equals(Constants.CUSTOMER)) {
           /* getSingleTransactionCustomer(ProjectID, CustFarmID, Constants.TRASACTION_DONE_BY_OWENER);

            int i = databaseHelper.getTotalOfCustomerTransactions(ProjectID, CustFarmID, Constants.TRASACTION_DONE_BY_OWENER);
            if (i == 0) {
                Log.e("TotalValues", "0");
            } else {
                Log.e("TotalValues", String.valueOf(i));
                textViewTotalAmount.setText("Total Amount :-" + String.valueOf(i));
            }*/
        } else if (ComingFrom.equals(Constants.FARMER)) {
            getSingleTransactionFarmer(ProjectID, CustFarmID, Constants.TRASACTION_DONE_BY_OWENER);


            int i = databaseHelper.getTotalOfFarmerTransactions(ProjectID, CustFarmID, Constants.TRASACTION_DONE_BY_OWENER);
            if (i == 0) {
                Log.e("TotalValues", "0");
            } else {
                Log.e("TotalValues", String.valueOf(i));
                textViewTotalAmount.setText("Total Amount :-" + String.valueOf(i));
            }

        } else {

        }
    }

/*
    public void getSingleTransactionCustomer(String ProjectID, String CuFaID, String doneBy) {

        dataBaseTransactionArrayList.clear();
        dataBaseTransactionArrayList.addAll(databaseHelper.TransactionGetSingleCustomer(ProjectID, CuFaID, doneBy));
        adapterTransaction.notifyDataSetChanged();

        for (int i = 0; i <= dataBaseTransactionArrayList.size() - 1; i++) {
            final DataBaseTransaction dataBaseFarmerClass = dataBaseTransactionArrayList.get(i);

            int TID = dataBaseFarmerClass.getTransaction_id();
            Log.e("Transaction Id ", String.valueOf(TID));

        }
    }
*/


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

    public void addNewTransactionCustomer() {
        Button buttonSubmit, buttonCancle;
        ImageView imageViewDatePicker;
        final EditText editTextTransactionAmount, editTextRemark, editTextCheckNummber;
        final TextView textDate;
        final RadioGroup radioGroupGivenTake, radioGroupPaymentType;
        RadioButton radioButtonGive, radioButtonTake, radioButtonCash, radioButtonCheck;


        final Dialog dialog = new Dialog(TransactionDetails.this);
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


        radioGroupGivenTake = (RadioGroup) dialog.findViewById(R.id.radioGroup);
        radioButtonGive = (RadioButton) dialog.findViewById(R.id.radioButton);
        radioButtonTake = (RadioButton) dialog.findViewById(R.id.radioButton2);
/*
        radioGroupPaymentType = (RadioGroup) dialog.findViewById(R.id.radioGroupType);
        radioButtonCash = (RadioButton) dialog.findViewById(R.id.radioButtonCash);
        radioButtonCheck = (RadioButton) dialog.findViewById(R.id.radioButtonCheck);*/


        editTextCheckNummber.setVisibility(View.GONE);
        textDate.setText(getDate());

        radioGroupGivenTake.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if (i == R.id.radioButton) {
                    Toast.makeText(TransactionDetails.this, "Give", Toast.LENGTH_SHORT).show();
                    //Your code
                } else if (i == R.id.radioButton2) {
                    Toast.makeText(TransactionDetails.this, "Take", Toast.LENGTH_SHORT).show();

                    //Your code
                }

            }
        });

   /*     radioGroupPaymentType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {

                if (i == R.id.radioButtonCash) {
                    Toast.makeText(TransactionDetails.this, "Give", Toast.LENGTH_SHORT).show();
                    editTextCheckNummber.setVisibility(View.GONE);

                    //Your code
                } else if (i == R.id.radioButtonCheck) {
                    Toast.makeText(TransactionDetails.this, "Take", Toast.LENGTH_SHORT).show();
                    editTextCheckNummber.setVisibility(View.VISIBLE);

                    //Your code
                }

            }
        });*/

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
            //    RadioButton rbPaymentType = (RadioButton) radioGroupPaymentType.findViewById(radioGroupPaymentType.getCheckedRadioButtonId());
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

                /*if (rbPaymentType.getText().equals("Cash")) {
                    transactionType = Constants.TRANSACTION_TYPE_CASH;
                } else {
                    transactionType = Constants.TRANSACTION_TYPE_CHECK;
                }*/


                if (editTextTransactionAmount.getText().toString().equals("")) {
                    editTextTransactionAmount.setError("Please Enter Amount");
                } else {


                    long insertTransaction = databaseHelper.TransactionInsert(ProjectID, "NA", CustFarmID, "NA", CustFarmAmountRescieved,
                            transactionDoneBy, transactionMoneyGiveTake, CustFarmAmountResCievedDate, transactionType, transactionCheckNumber, CustFarmNotificationrRemark);


                    if (insertTransaction == 0) {
                        Toast.makeText(TransactionDetails.this, "Not Inserted", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(TransactionDetails.this, "Inserted Transaction ", Toast.LENGTH_SHORT).show();


                    }

                    dialog.dismiss();
                 //   getSingleTransactionCustomer(ProjectID, CustFarmID, Constants.TRASACTION_DONE_BY_OWENER);

                }
            }
        });

        buttonCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    public void addNewTransactionFarmer() {
        Button buttonSubmit, buttonCancle;
        ImageView imageViewDatePicker;
        final EditText editTextTransactionAmount, editTextRemark, editTextCheckNummber;
        final TextView textDate;
        final RadioGroup radioGroupGivenTake, radioGroupPaymentType;
        RadioButton radioButtonGive, radioButtonTake, radioButtonCash, radioButtonCheck;


        final Dialog dialog = new Dialog(TransactionDetails.this);
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


        radioGroupGivenTake = (RadioGroup) dialog.findViewById(R.id.radioGroup);
        radioButtonGive = (RadioButton) dialog.findViewById(R.id.radioButton);
        radioButtonTake = (RadioButton) dialog.findViewById(R.id.radioButton2);

/*        radioGroupPaymentType = (RadioGroup) dialog.findViewById(R.id.radioGroupType);
        radioButtonCash = (RadioButton) dialog.findViewById(R.id.radioButtonCash);
        radioButtonCheck = (RadioButton) dialog.findViewById(R.id.radioButtonCheck);*/

        editTextCheckNummber.setVisibility(View.GONE);
        textDate.setText(getDate());
        radioGroupGivenTake.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if (i == R.id.radioButton) {
                    Toast.makeText(TransactionDetails.this, "Give", Toast.LENGTH_SHORT).show();
                    //Your code
                } else if (i == R.id.radioButton2) {
                    Toast.makeText(TransactionDetails.this, "Take", Toast.LENGTH_SHORT).show();

                    //Your code
                }

            }
        });

       /* radioGroupPaymentType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {

                if (i == R.id.radioButtonCash) {
                    Toast.makeText(TransactionDetails.this, "Give", Toast.LENGTH_SHORT).show();
                    editTextCheckNummber.setVisibility(View.GONE);

                    //Your code
                } else if (i == R.id.radioButtonCheck) {
                    Toast.makeText(TransactionDetails.this, "Take", Toast.LENGTH_SHORT).show();
                    editTextCheckNummber.setVisibility(View.VISIBLE);

                    //Your code
                }

            }
        });
*/
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

             //   RadioButton rbPaymentType = (RadioButton) radioGroupPaymentType.findViewById(radioGroupPaymentType.getCheckedRadioButtonId());


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
/*

                if (rbPaymentType.getText().equals("Cash")) {
                    transactionType = Constants.TRANSACTION_TYPE_CASH;
                } else {
                    transactionType = Constants.TRANSACTION_TYPE_CHECK;
                }
*/

                if (editTextTransactionAmount.getText().toString().equals("")) {
                    editTextTransactionAmount.setError("Please Enter Amount");
                } else {

                    long insertTransaction = databaseHelper.TransactionInsert(ProjectID, "NA", "NA", CustFarmID, CustFarmAmountRescieved,
                            transactionDoneBy, transactionMoneyGiveTake, CustFarmAmountResCievedDate, transactionType, transactionCheckNumber, CustFarmNotificationrRemark);

                    if (insertTransaction == 0) {
                        Toast.makeText(TransactionDetails.this, "Not Inserted", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(TransactionDetails.this, "Inserted Transaction ", Toast.LENGTH_SHORT).show();
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
