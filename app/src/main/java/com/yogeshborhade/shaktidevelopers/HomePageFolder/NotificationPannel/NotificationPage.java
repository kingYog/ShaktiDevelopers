package com.yogeshborhade.shaktidevelopers.HomePageFolder.NotificationPannel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseNotification;
import com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectHome.ProjectDetails;
import com.yogeshborhade.shaktidevelopers.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class NotificationPage extends AppCompatActivity {


    RecyclerView recyclerView;
    Button buttonBack, buttonGetAll, buttonGetToday;
    AdapterNotification adapterNotification;

    private ArrayList<DataBaseNotification> dataBaseNotificationArrayList = new ArrayList<>();
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notification);

        db = new DatabaseHelper(NotificationPage.this);
        getXMLContent();


        adapterNotification = new AdapterNotification(dataBaseNotificationArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(NotificationPage.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        sortListByDate(dataBaseNotificationArrayList);
        recyclerView.setAdapter(adapterNotification);
        sortListByDate(dataBaseNotificationArrayList);


        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonGetToday.setBackgroundResource(R.color.colorAccent);
        buttonGetToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getToday();
                buttonGetToday.setBackgroundResource(R.color.colorAccent);
                buttonGetAll.setBackgroundResource(R.color.colorPrimaryDark);

            }
        });

        buttonGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAll();
                buttonGetAll.setBackgroundResource(R.color.colorAccent);
                buttonGetToday.setBackgroundResource(R.color.colorPrimaryDark);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        getToday();
    }

    public void getXMLContent() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        buttonBack = (Button) findViewById(R.id.mbackButton);
        buttonGetAll = (Button) findViewById(R.id.mgetAll);
        buttonGetToday = (Button) findViewById(R.id.mgetToday);

    }

    public void getAll() {
        dataBaseNotificationArrayList.clear();
        dataBaseNotificationArrayList.addAll(db.NotificationGetAll(ProjectDetails.ProjectID));


        adapterNotification.notifyDataSetChanged();
        sortListByDate(dataBaseNotificationArrayList);
    }

    public void getToday() {
        dataBaseNotificationArrayList.clear();


        dataBaseNotificationArrayList.addAll(db.NotificationGetByTodayDate(ProjectDetails.ProjectID));


        adapterNotification.notifyDataSetChanged();
        sortListByDate(dataBaseNotificationArrayList);
    }

    private void sortListByDate(ArrayList<DataBaseNotification> theArrayListEvents) {
        Collections.sort(theArrayListEvents, new EventDetailSortByDate());
    }

    private class EventDetailSortByDate implements java.util.Comparator<DataBaseNotification> {
        @Override
        public int compare(DataBaseNotification customerEvents1, DataBaseNotification customerEvents2) {
            Date DateObject1 = StringToDate(customerEvents1.getNotificationDate());
            Date DateObject2 = StringToDate(customerEvents2.getNotificationDate());

            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(DateObject1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(DateObject2);

            int month1 = cal1.get(Calendar.MONTH);
            int month2 = cal2.get(Calendar.MONTH);

            if (month1 < month2)
                return -1;
            else if (month1 == month2)
                return cal1.get(Calendar.DAY_OF_MONTH) - cal2.get(Calendar.DAY_OF_MONTH);

            else return 1;
        }
    }

    public static Date StringToDate(String theDateString) {
        Date returnDate = new Date();
        if (theDateString.contains("-")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            try {
                returnDate = dateFormat.parse(theDateString);
            } catch (ParseException e) {
                SimpleDateFormat altdateFormat = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    returnDate = altdateFormat.parse(theDateString);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            try {
                returnDate = dateFormat.parse(theDateString);
            } catch (ParseException e) {
                SimpleDateFormat altdateFormat = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    returnDate = altdateFormat.parse(theDateString);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return returnDate;
    }

    public String getTodayDate() {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("d-M-yyyy");
        String todayDate = df.format(c);

        return todayDate;
    }
}
