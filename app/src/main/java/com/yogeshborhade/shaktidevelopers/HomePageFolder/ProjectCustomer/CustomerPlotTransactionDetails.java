package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectCustomer;

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

import com.yogeshborhade.shaktidevelopers.Database.Constants;
import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseTransaction;
import com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectHome.ProjectDetails;
import com.yogeshborhade.shaktidevelopers.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.yogeshborhade.shaktidevelopers.Database.Constants.numberWithComma;
import static com.yogeshborhade.shaktidevelopers.Database.Constants.numberWithoutExponential;

public class CustomerPlotTransactionDetails extends AppCompatActivity {
    RecyclerView recyclerView;

    Button btnAddTransaction, backButton;
    TextView textViewTotalAmount;
    public static String bookID, bookTotalAmount, bookGivenAmount, bookPendingAmount;

    String ProjectID, bookplotID, bookCustomerID, bookDate, bookTotalArea, bookRatePerSqureFoot,
            bookGivenAmountDate,
            bookPendingAmountDate, bookNotification, bookNotificationDate, bookStatus;


    String newAmountRescived, newAmountResCievedDate, newTransactionType, newTransactionDoneby, newtransactionMoneyGiveTake, newtransactionCheckNumber, newNotificationrRemark;
    double updateNewGivenAmount, updateNewPendingAmount;

    DatabaseHelper databaseHelper;
    private List<DataBaseTransaction> dataBaseTransactionArrayList = new ArrayList<>();

    AdapterCustomerPlotTransaction adapterTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_plot_transaction_details);
        databaseHelper = new DatabaseHelper(this);

        Bundle bundle = getIntent().getExtras();
        bookID = bundle.getString("bookid", "0");
        bookplotID = bundle.getString("bookplotid", "0");
        bookCustomerID = bundle.getString("bookcustomerid", "0");
        bookDate = bundle.getString("bookdate", "0");
        bookTotalArea = bundle.getString("booktotaarea", "0");
        bookRatePerSqureFoot = bundle.getString("bookratepersqurefoot", "0");
        bookTotalAmount = bundle.getString("booktotalamount", "0");
        bookGivenAmount = bundle.getString("bookgivenamount", "0");
        bookGivenAmountDate = bundle.getString("bookgivenamountdate", "0");
        bookPendingAmount = bundle.getString("bookremainingamount", "0");
        bookPendingAmountDate = bundle.getString("bookremainingamountdate", "0");
        bookNotification = bundle.getString("booknotification", "0");
        bookNotificationDate = bundle.getString("booknotificationdate", "0");
        bookStatus = bundle.getString("bookstatus", "0");
        ProjectID = ProjectDetails.ProjectID;

        getXMLContent();

        adapterTransaction = new AdapterCustomerPlotTransaction(dataBaseTransactionArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(CustomerPlotTransactionDetails.this);
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
                addNewTransactionCustomer();
            }
        });
        getSingleTransactionCustomer(ProjectID, bookCustomerID, "nill");

    }

    @Override
    protected void onResume() {
        super.onResume();

//        getSingleTransactionCustomer(ProjectID, bookCustomerID, Constants.TRASACTION_DONE_BY_OWENER);
        getSingleTransactionCustomer(ProjectID, bookCustomerID, "nill");

        int i = databaseHelper.getTotalOfCustomerTransactions(ProjectID, bookCustomerID, Constants.TRASACTION_DONE_BY_OWENER);
        if (i == 0) {
            Log.e("TotalValues", "0");
        } else {
            Log.e("TotalValues", String.valueOf(i));
            textViewTotalAmount.setText("Total Amount :-" + String.valueOf(numberWithComma.format(Double.valueOf(i))));
        }
    }

    public void getSingleTransactionCustomer(String ProjectID, String CuFaID, String doneBy) {
        dataBaseTransactionArrayList.clear();
        dataBaseTransactionArrayList.addAll(databaseHelper.TransactionGetSingleCustomer(ProjectID, CuFaID, doneBy, bookplotID));
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
        Spinner paymentModespinner;
        final EditText editTextTransactionAmount, editTextRemark, editTextCheckNummber;
        final TextInputLayout textInputLayoutPaymentMode;
        final TextView textDate;
        final RadioGroup radioGroupGivenTake;
        RadioButton radioButtonGive, radioButtonTake, radioButtonCash, radioButtonCheck;


        final Dialog dialog = new Dialog(CustomerPlotTransactionDetails.this);
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
                  //  Toast.makeText(CustomerPlotTransactionDetails.this, "Give", Toast.LENGTH_SHORT).show();
                    //Your code
                } else if (i == R.id.radioButton2) {
                  //  Toast.makeText(CustomerPlotTransactionDetails.this, "Take", Toast.LENGTH_SHORT).show();

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
                newTransactionType = item;
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
                newAmountRescived = editTextTransactionAmount.getText().toString();
                newtransactionCheckNumber = editTextCheckNummber.getText().toString();
                newNotificationrRemark = editTextRemark.getText().toString();
                newAmountResCievedDate = textDate.getText().toString();
                newTransactionDoneby = Constants.TRASACTION_DONE_BY_OWENER;


                if (rbGiveTakeTypeIS.getText().equals("Give")) {
                    newtransactionMoneyGiveTake = Constants.TRASACTION_MONEY_GIVE;
                } else {
                    newtransactionMoneyGiveTake = Constants.TRASACTION_MONEY_TAKE;
                }


                // Update Transaction Table
                // Update Book Plot Table

                if (editTextTransactionAmount.getText().toString().equals("")) {
                    editTextTransactionAmount.setError("Please Enter Amount");
                } else {


                    long insertTransaction = databaseHelper.TransactionInsert(ProjectID, bookplotID, bookCustomerID, "NA", newAmountRescived,
                            Constants.TRASACTION_DONE_BY_CUSTOMER, Constants.TRASACTION_MONEY_GIVE, newAmountResCievedDate, newTransactionType, newtransactionCheckNumber, newNotificationrRemark);

                    if (insertTransaction == 0) {
                      //  Toast.makeText(CustomerPlotTransactionDetails.this, "Not Inserted", Toast.LENGTH_SHORT).show();

                    } else {
                     //   Toast.makeText(CustomerPlotTransactionDetails.this, "Inserted Transaction ", Toast.LENGTH_SHORT).show();
                    }


                    updateNewGivenAmount = (Double.parseDouble(bookGivenAmount) + Double.parseDouble(newAmountRescived));
                    updateNewPendingAmount = (Double.parseDouble(bookTotalAmount) - updateNewGivenAmount);

                    bookGivenAmount = String.valueOf(numberWithoutExponential.format(updateNewGivenAmount));
                    bookPendingAmount = String.valueOf(numberWithoutExponential.format(updateNewPendingAmount));

                    Log.e("New Given Amount", String.valueOf(updateNewGivenAmount));
                    Log.e("New Remaining Amount", String.valueOf(updateNewPendingAmount));

                    long updatePlot = databaseHelper.BookPlotUpdate(Integer.parseInt(bookID), String.valueOf(numberWithoutExponential.format(updateNewGivenAmount)), String.valueOf(numberWithoutExponential.format(updateNewPendingAmount)));

                    if (updatePlot == 0) {
                      //  Toast.makeText(CustomerPlotTransactionDetails.this, "Plot not Updated", Toast.LENGTH_SHORT).show();
                    } else {
                      //  Toast.makeText(CustomerPlotTransactionDetails.this, "Updated Plot", Toast.LENGTH_SHORT).show();

                    }

                }
                dialog.dismiss();
                getSingleTransactionCustomer(ProjectID, bookCustomerID, Constants.TRASACTION_DONE_BY_OWENER);
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

    public static String getDate() {
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATEFORMATE);
        String dateString = sdf.format(date);

        return dateString;
    }


}
