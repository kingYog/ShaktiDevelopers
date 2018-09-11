package com.yogeshborhade.shaktidevelopers.HomePageFolder.NotificationPannel;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yogeshborhade.shaktidevelopers.Database.Constants;
import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseNotification;
import com.yogeshborhade.shaktidevelopers.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 02-06-2018.
 */

public class AdapterNotification extends RecyclerView.Adapter<AdapterNotification.MyViewHolder> {

    private List<DataBaseNotification> dataBaseNotificationList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textDate, textTitle, textDesc, textID, textFor;
        LinearLayout linearLayoutMain;
        RelativeLayout imageViewDelet;

        public MyViewHolder(View view) {
            super(view);

            textDate = (TextView) view.findViewById(R.id.mtextDate);
            textTitle = (TextView) view.findViewById(R.id.mtextTitle);
            textDesc = (TextView) view.findViewById(R.id.mtextDesc);
            textID = (TextView) view.findViewById(R.id.mtextNotids);
            textFor = (TextView) view.findViewById(R.id.mtextNotfrom);

            linearLayoutMain = (LinearLayout) view.findViewById(R.id.mllMain);
            imageViewDelet = (RelativeLayout) view.findViewById(R.id.mdelete_Notification);

        }
    }


    public AdapterNotification(List<DataBaseNotification> dataBaseNotificationList) {
        this.dataBaseNotificationList = dataBaseNotificationList;
    }

    @Override
    public AdapterNotification.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_notification, parent, false);

        return new AdapterNotification.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AdapterNotification.MyViewHolder holder, final int position) {
        final DataBaseNotification dataBaseNotification = dataBaseNotificationList.get(position);

//        holder.textID.setText(String.valueOf(dataBaseNotification.getNotificationID()));

        holder.textID.setText(String.valueOf(position + 1));
        holder.textDate.setText(dataBaseNotification.getNotificationDate());
        holder.textTitle.setText(dataBaseNotification.getNotificationTitle());
        holder.textDesc.setText(dataBaseNotification.getNotificationDescription());
        holder.textFor.setText(dataBaseNotification.getNotificationFor());

        if (dataBaseNotification.getNotificationReadUnread().equals(Constants.READ)) {
            holder.linearLayoutMain.setBackgroundResource(R.color.colorGrey);
        } else {
            holder.linearLayoutMain.setBackgroundResource(R.color.colorWhite);
        }

        holder.linearLayoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readNotification(v, String.valueOf(dataBaseNotification.getNotificationID()));
                holder.linearLayoutMain.setBackgroundResource(R.color.colorGrey);
                dataBaseNotification.setNotificationReadUnread(Constants.READ);
                notifyDataSetChanged();
            }
        });

        holder.imageViewDelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (dataBaseNotification.getNotificationDate().equals("NA") || dataBaseNotification.getNotificationDate().equals("na") || dataBaseNotification.getNotificationDate().equals("")||dataBaseNotification.getNotificationDate().equals("Please Enter Notification Date")) {
                    deletNotification(view, dataBaseNotification.getNotificationID());
                    NotificationPage notificationPage = new NotificationPage();
                    dataBaseNotificationList.remove(position);
                } else {


                    if (dataBaseNotification.getNotificationReadUnread().equals(Constants.UNREAD)) {
                        Toast.makeText(view.getContext(), "Please Read Notification First", Toast.LENGTH_LONG).show();

                    } else if (ConvertDate(dataBaseNotification.getNotificationDate()).after(ConvertDate(getDate()))) {
                        Toast.makeText(view.getContext(), "This Notification will be delete After Notification Date", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(view.getContext(), "Delete here", Toast.LENGTH_LONG).show();
                        deletNotification(view, dataBaseNotification.getNotificationID());
                        NotificationPage notificationPage = new NotificationPage();
                        dataBaseNotificationList.remove(position);
                        notifyDataSetChanged();
                    }
                }

       /*         deletNotification(view, dataBaseNotification.getNotificationID());
                NotificationPage notificationPage = new NotificationPage();
                dataBaseNotificationList.remove(position);
                notifyDataSetChanged();
*/

/*
                if (ConvertDate(dataBaseNotification.getNotificationDate()).before(ConvertDate(getDate()))) {
                    Toast.makeText(view.getContext(), "after", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(view.getContext(), "Before", Toast.LENGTH_SHORT).show();
                }
*/

            }
        });
    }


    @Override
    public int getItemCount() {
        return dataBaseNotificationList.size();
    }

    public void readNotification(View view, String notificationID) {

        DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());

        long id = databaseHelper.NotificatioReadUpdate(notificationID, Constants.READ);

        if (id == 0) {
            Toast.makeText(view.getContext(), "There is Some Errors", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(view.getContext(), "Read Notification", Toast.LENGTH_SHORT).show();
        }

    }

    public void deletNotification(View view, int notificationID) {

        DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());

        long id = databaseHelper.NotificatioDelete(notificationID);

        if (id == 0) {
            Toast.makeText(view.getContext(), "There is Some Errors", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(view.getContext(), "deleted", Toast.LENGTH_SHORT).show();

        }

    }

    public static String getDate() {

        long date = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATEFORMATE);
        String dateString = sdf.format(date);

        return dateString;
    }

    public Date ConvertDate(String dtStart) {

        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat(Constants.DATEFORMATE);
        try {
            date = format.parse(dtStart);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


}




