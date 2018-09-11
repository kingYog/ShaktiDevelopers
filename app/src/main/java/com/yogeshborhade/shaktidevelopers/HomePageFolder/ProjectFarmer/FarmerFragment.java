package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectFarmer;

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
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseFarmerClass;
import com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectHome.ProjectDetails;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 06-04-2018.
 */

public class FarmerFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    Button btnAddFarmer;
    TextView textViewTotalFarmer;
    DatabaseHelper databaseHelper;


    AdapterFarmer adapterFarmer;
    private List<DataBaseFarmerClass> dataBaseFarmerClassList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_farmers, container, false);

        databaseHelper = new DatabaseHelper(view.getContext());
        getXMLContent();
        btnAddFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view.getContext(), AddFarmer.class);
                startActivity(i);
            }
        });
        adapterFarmer = new AdapterFarmer(dataBaseFarmerClassList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterFarmer);


        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        getFarmerList();
    }

    public void getXMLContent() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        btnAddFarmer = (Button) view.findViewById(R.id.mbtnAddProject);
        textViewTotalFarmer = (TextView) view.findViewById(R.id.mtexTotalProject);
    }


    public void getFarmerList() {
        dataBaseFarmerClassList.clear();
        dataBaseFarmerClassList.addAll(databaseHelper.FarmerGetAll(ProjectDetails.ProjectID));
        textViewTotalFarmer.setText("Total Farmer:-   " + String.valueOf(dataBaseFarmerClassList.size()) + " /-");
        adapterFarmer.notifyDataSetChanged();
    }
}
