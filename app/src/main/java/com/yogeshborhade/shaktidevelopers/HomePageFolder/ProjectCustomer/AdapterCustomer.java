package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectCustomer;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yogeshborhade.shaktidevelopers.Database.Constants;
import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseCustomerClass;
import com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectTransaction.TransactionDetails;
import com.yogeshborhade.shaktidevelopers.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static com.yogeshborhade.shaktidevelopers.Database.Constants.numberWithComma;

/**
 * Created by admin on 06-04-2018.
 */

public class AdapterCustomer extends RecyclerView.Adapter<AdapterCustomer.MyViewHolder> {

    private List<DataBaseCustomerClass> dataBaseCustomerClassList;
    Context context;
    View itemView;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textCustomerID, textCustomerName, textCustomerAddress, textCustomerMobileNumber, textCustomeTotalAmount, textGivenAmount, textCustomerRemainingAmount, textCustomerNextCommitment;
        public Button btnCallMe, btnTransaction, btnNotifyMe;

        public LinearLayout mainLayout;

        public MyViewHolder(View view) {
            super(view);
            textCustomerID = (TextView) view.findViewById(R.id.mtextCustomerID);
            textCustomerName = (TextView) view.findViewById(R.id.mtextCustomerName);
            textCustomerMobileNumber = (TextView) view.findViewById(R.id.mtextMobileNumber);
            textCustomeTotalAmount = (TextView) view.findViewById(R.id.mtextTotalAmount);
            textGivenAmount = (TextView) view.findViewById(R.id.mtextGivenAmount);

            textCustomerRemainingAmount = (TextView) view.findViewById(R.id.mtextRemainingAmount);
            textCustomerNextCommitment = (TextView) view.findViewById(R.id.mtextnextCommitMentDate);
            btnCallMe = (Button) view.findViewById(R.id.mbuttonCallMe);
            btnTransaction = (Button) view.findViewById(R.id.mbuttonTransaction);
            btnNotifyMe = (Button) view.findViewById(R.id.mbuttonnotify);
            mainLayout = (LinearLayout) view.findViewById(R.id.rowmainLayour);

        }
    }


    public AdapterCustomer(List<DataBaseCustomerClass> dataBaseCustomerClassList) {
        this.dataBaseCustomerClassList = dataBaseCustomerClassList;
    }

    @Override
    public AdapterCustomer.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_customer, parent, false);

        return new AdapterCustomer.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterCustomer.MyViewHolder holder, int position) {
        final DataBaseCustomerClass customerPojo = dataBaseCustomerClassList.get(position);

//        holder.textCustomerID.setText(String.valueOf(customerPojo.getCustomerID()));
        holder.textCustomerID.setText(String.valueOf(position + 1));
        holder.textCustomerName.setText(customerPojo.getCustomerName());
        holder.textCustomerMobileNumber.setText(customerPojo.getCustomerMobileNumber());


        DatabaseHelper db = new DatabaseHelper(itemView.getContext());
        String customerTotal = Constants.numberWithoutExponential.format(db.getTotalOfCustomerAmountByCustomerAndProject(customerPojo.getProjectID(), String.valueOf(customerPojo.getCustomerID())));
        if (customerTotal.equals("0")) {
            Log.e("customerTotal", "0");
            holder.textCustomeTotalAmount.setText(String.valueOf(customerTotal));
        } else {
            Log.e("customerTotal", String.valueOf(customerTotal));
            holder.textCustomeTotalAmount.setText(String.valueOf(numberWithComma.format(Double.valueOf(customerTotal))));
        }


        String customerPaidAmount = Constants.numberWithoutExponential.format(db.getTotalOfCustomerPaidAmountCustomerAndProject(customerPojo.getProjectID(), String.valueOf(customerPojo.getCustomerID())));
        if (customerPaidAmount.equals("0")) {
            Log.e("customerPaidAmount", "0");
            holder.textGivenAmount.setText(String.valueOf(customerPaidAmount));
        } else {
            Log.e("customerPaidAmount", String.valueOf(customerPaidAmount));
            holder.textGivenAmount.setText(String.valueOf(numberWithComma.format(Double.valueOf(customerPaidAmount))));

        }

        String customerRemainingAmount = Constants.numberWithoutExponential.format(db.getTotalOfCustomerRemainingAmountByCustomerAndProject(customerPojo.getProjectID(), String.valueOf(customerPojo.getCustomerID())));
        if (customerRemainingAmount.equals("0")) {
            Log.e("customerRemainingAmount", "0");
            holder.textCustomerRemainingAmount.setText(String.valueOf(customerRemainingAmount));
        } else {
            Log.e("customerRemainingAmount", String.valueOf(customerRemainingAmount));
            holder.textCustomerRemainingAmount.setText(String.valueOf(numberWithComma.format(Double.valueOf(customerRemainingAmount))));

        }


        holder.btnCallMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + customerPojo.getCustomerMobileNumber()));
                if (ActivityCompat.checkSelfPermission(v.getContext(),
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                ((Activity) v.getContext()).startActivity(callIntent);

            }
        });

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), CustomerUpdate.class);
                i.putExtra("projectID", String.valueOf(customerPojo.getProjectID()));
                i.putExtra("customerID", String.valueOf(customerPojo.getCustomerID()));
                ((Activity) v.getContext()).startActivity(i);

            }
        });

        holder.btnTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), TransactionDetails.class);
                i.putExtra("from", Constants.CUSTOMER);
                i.putExtra("projectID", String.valueOf(customerPojo.getProjectID()));
                i.putExtra("CustFarmID", String.valueOf(customerPojo.getCustomerID()));

                ((Activity) v.getContext()).startActivity(i);

            }
        });

        holder.btnNotifyMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewNotificationCustomer(v, String.valueOf(customerPojo.getCustomerID()), String.valueOf(customerPojo.getProjectID()));
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataBaseCustomerClassList.size();
    }

    public void addNewNotificationCustomer(final View view, final String farmerID, final String project_id) {
        Button buttonSubmit, buttonCancle;
        ImageView imageViewDatePicker;


        final EditText editTextNotiTitle, editTextNotiDesc;
        final TextView textDate;

        final Dialog dialog = new Dialog(view.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dilog_add_notification);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // set the custom dialog components - text, image and button

        buttonSubmit = (Button) dialog.findViewById(R.id.mSubmit);
        buttonCancle = (Button) dialog.findViewById(R.id.mCancle);
        imageViewDatePicker = (ImageView) dialog.findViewById(R.id.mdatePicker);
        textDate = (TextView) dialog.findViewById(R.id.mtextdate);

        editTextNotiTitle = (EditText) dialog.findViewById(R.id.medtTitle);
        editTextNotiDesc = (EditText) dialog.findViewById(R.id.medtDesc);

        textDate.setText(getDate());

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
                String NotificationTitle = editTextNotiTitle.getText().toString();
                String NotificationDesc = editTextNotiDesc.getText().toString();
                String NotificationDate = textDate.getText().toString();
                String NotificationTime = "NA";
                String forWhome = Constants.CUSTOMER;
                String ReadUnread = Constants.UNREAD;

                DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());


                long id = databaseHelper.NotificationInsert(project_id, NotificationDate, NotificationTime, NotificationTitle,
                        NotificationDesc, forWhome, farmerID, ReadUnread);

                if (id == 0) {

                    Toast.makeText(view.getContext(), "Not Inserted", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(view.getContext(), "Inserted Row " + id, Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
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




