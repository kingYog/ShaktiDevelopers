package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectFarmer;

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

import com.yogeshborhade.shaktidevelopers.Database.Constants;
import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseFarmerClass;
import com.yogeshborhade.shaktidevelopers.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static com.yogeshborhade.shaktidevelopers.Database.Constants.numberWithComma;

/**
 * Created by admin on 06-04-2018.
 */

public class AdapterFarmer extends RecyclerView.Adapter<AdapterFarmer.MyViewHolder> {

    private List<DataBaseFarmerClass> farmerPojoList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textFarmerId, textFarmerName, textFarmerAddress, textFarmerMobileNumber, textFarmerTotalAmount, textFarmerGivenAmount, textFarmerRemainingAmount, textFarmerNextCommitment;
        public Button btnCallMe, btnTransaction, btnNotifyMe;
        public LinearLayout mainLayout;

        public MyViewHolder(View view) {
            super(view);

            textFarmerId = (TextView) view.findViewById(R.id.mtextFarmerID);
            textFarmerName = (TextView) view.findViewById(R.id.mtextFarmerName);
            textFarmerMobileNumber = (TextView) view.findViewById(R.id.mtextMobileNumber);
            textFarmerTotalAmount = (TextView) view.findViewById(R.id.mtextTotalAmount);
            textFarmerGivenAmount = (TextView) view.findViewById(R.id.mtextAmountGiven);
            textFarmerRemainingAmount = (TextView) view.findViewById(R.id.mtextRemainingAmount);
            textFarmerNextCommitment = (TextView) view.findViewById(R.id.mtextnextCommitMentDate);

            btnCallMe = (Button) view.findViewById(R.id.mbuttonCallMe);
            btnTransaction = (Button) view.findViewById(R.id.mbuttonTransaction);
            btnNotifyMe = (Button) view.findViewById(R.id.mbuttonnotify);
            mainLayout = (LinearLayout) view.findViewById(R.id.rowmainLayour);


        }
    }


    public AdapterFarmer(List<DataBaseFarmerClass> farmerPojoList) {
        this.farmerPojoList = farmerPojoList;
    }

    @Override
    public AdapterFarmer.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_farmer, parent, false);

        return new AdapterFarmer.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterFarmer.MyViewHolder holder, int position) {
        final DataBaseFarmerClass farmerPojo = farmerPojoList.get(position);

        //holder.textFarmerId.setText(String.valueOf(farmerPojo.getFarmerID()));

        holder.textFarmerId.setText(String.valueOf(position + 1));
        holder.textFarmerName.setText(farmerPojo.getFarmerName());
        holder.textFarmerMobileNumber.setText(farmerPojo.getFarmerMobileNumber());
        holder.textFarmerTotalAmount.setText(numberWithComma.format(Double.valueOf(farmerPojo.getFarmerTotalAmountToBePaid())));
        holder.textFarmerGivenAmount.setText(numberWithComma.format(Double.valueOf(farmerPojo.getFarmerAmountgiven())));
        holder.textFarmerRemainingAmount.setText(numberWithComma.format(Double.valueOf(farmerPojo.getFarmerPendingAmount())));
        holder.textFarmerNextCommitment.setText(farmerPojo.getFarmerPendingAmountCommitmentDate());

        holder.btnCallMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + farmerPojo.getFarmerMobileNumber()));

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
                Intent i = new Intent(v.getContext(), Update_Farmer.class);
                i.putExtra("projectID", String.valueOf(farmerPojo.getProjectID()));
                i.putExtra("farmertID", String.valueOf(farmerPojo.getFarmerID()));
                ((Activity) v.getContext()).startActivity(i);
            }
        });

        holder.btnTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), FarmerTransactionDetails.class);
                //i.putExtra("from", Constants.FARMER);
                i.putExtra("projectID", String.valueOf(farmerPojo.getProjectID()));
                i.putExtra("CustFarmID", String.valueOf(farmerPojo.getFarmerID()));
                i.putExtra("plot", String.valueOf(farmerPojo.getFarmerSellingPlotID()));
                i.putExtra("total", String.valueOf(farmerPojo.getFarmerTotalAmountToBePaid()));
                i.putExtra("given", String.valueOf(farmerPojo.getFarmerAmountgiven()));
                i.putExtra("pending", String.valueOf(farmerPojo.getFarmerPendingAmount()));

                ((Activity) v.getContext()).startActivity(i);
            }
        });

        holder.btnNotifyMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewNotificationCustomer(v, String.valueOf(farmerPojo.getFarmerID()), String.valueOf(farmerPojo.getProjectID()));
            }
        });
    }


    @Override
    public int getItemCount() {
        return farmerPojoList.size();
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
                String forWhome = Constants.FARMER;
                String ReadUnread = Constants.UNREAD;


                DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
                long id = databaseHelper.NotificationInsert(project_id, NotificationDate, NotificationTime, NotificationTitle,
                        NotificationDesc, forWhome, farmerID, ReadUnread);

                if (id == 0) {
                 //   Toast.makeText(view.getContext(), "Not Inserted", Toast.LENGTH_SHORT).show();
                } else {
                   // Toast.makeText(view.getContext(), "Inserted Row " + id, Toast.LENGTH_SHORT).show();
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





