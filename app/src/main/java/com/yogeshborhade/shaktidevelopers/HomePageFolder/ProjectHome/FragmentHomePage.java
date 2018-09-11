package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectHome;

import android.app.Fragment;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DatabaseProjectClass;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 3/27/2018.
 */

public class FragmentHomePage extends Fragment {

    View view;
    RecyclerView recyclerView;
    AdapterHomePage adapterHomePage;
    private List<DatabaseProjectClass> projectList = new ArrayList<>();

    Button btnAddProject;
    TextView textViewTotalProjects;
    DatabaseHelper db;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_page, container, false);

        getXMLContent();

        db = new DatabaseHelper(view.getContext());
        adapterHomePage = new AdapterHomePage(projectList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterHomePage);
        btnAddProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddProject.class);
                startActivity(intent);
            }
        });


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        getProjectsList();
    }

    public void getXMLContent() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        btnAddProject = (Button) view.findViewById(R.id.mbtnAddProject);
        textViewTotalProjects = (TextView) view.findViewById(R.id.mtexTotalProject);
    }

    public void getProjectsList() {
        projectList.clear();
        projectList.addAll(db.ProjectGetAll());
        textViewTotalProjects.setText("Total Projects " + String.valueOf(projectList.size()) + " /-");
        adapterHomePage.notifyDataSetChanged();
    }

    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(view.getContext())
                        .setSmallIcon(R.drawable.icon_delete)
                        .setContentTitle("Notifications Example")
                        .setContentText("This is a test notification");

        Intent notificationIntent = new Intent(view.getContext(), view.getClass());
        PendingIntent contentIntent = PendingIntent.getActivity(view.getContext(), 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) view.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

}
