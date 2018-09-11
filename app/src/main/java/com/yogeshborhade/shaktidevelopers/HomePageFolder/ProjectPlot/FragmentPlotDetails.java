package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectPlot;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DatabasePlotClass;
import com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectHome.ProjectDetails;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 3/29/2018.
 */

public class FragmentPlotDetails extends Fragment {
    View view;
    RecyclerView recyclerView;
    TextView textTotalPlot;
    Button btnAddPlot;
    AdapterPlotDetails adapterPlotDetails;
    private List<DatabasePlotClass> plotList = new ArrayList<>();
    DatabaseHelper db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_plot_detail, container, false);
        db = new DatabaseHelper(view.getContext());
        getXMLContent();
        btnAddPlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view.getContext(), AddPlot.class);
                startActivity(i);
            }
        });
        adapterPlotDetails = new AdapterPlotDetails(plotList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterPlotDetails);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getProjectsList();
    }

    public void getXMLContent() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        textTotalPlot = (TextView) view.findViewById(R.id.mtexTotalPlot);
        btnAddPlot = (Button) view.findViewById(R.id.mbtnAddProject);
    }

    public void getProjectsList() {
        plotList.clear();
        plotList.addAll(db.PlotGetAll(ProjectDetails.ProjectID));

    /*    String status="'remaining'";
        plotList.addAll(db.getRemainingPlot(ProjectDetails.ProjectID,status));*/

        textTotalPlot.setText("Total Plot:- " + String.valueOf(plotList.size()) + " /-");
        adapterPlotDetails.notifyDataSetChanged();
    }


}
