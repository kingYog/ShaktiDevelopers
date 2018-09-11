package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectExpence;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseExpenceClas;
import com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectHome.ProjectDetails;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by admin on 30-03-2018.
 */

public class FragmentExpence extends Fragment {
    View view;
    RecyclerView recyclerView;
    Button addExpence;
    TextView textViewTotalExpence;
    AdapterExpence adapterExpence;


    private List<DataBaseExpenceClas> dataBaseExpenceClasList = new ArrayList<>();
    DatabaseHelper db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_expence, container, false);

        db = new DatabaseHelper(view.getContext());
        getXMLContent();


        addExpence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view.getContext(), AddExpence.class);
                startActivity(i);
            }
        });
        adapterExpence = new AdapterExpence(dataBaseExpenceClasList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterExpence);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        getExpenceList();
        int i = db.getTotalOfExpenceByProject(ProjectDetails.ProjectID);
        if (i == 0) {
            Log.e("TotalValues", "0");
        } else {
            Log.e("TotalValues", String.valueOf(i));
            textViewTotalExpence.setText("Total Expence :-" + String.valueOf(i));
        }
    }

    public void getXMLContent() {

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        addExpence = (Button) view.findViewById(R.id.addExpence);
        textViewTotalExpence = (TextView) view.findViewById(R.id.mtexTotalExpence);

    }


    public void getExpenceList() {
        dataBaseExpenceClasList.clear();
        dataBaseExpenceClasList.addAll(db.ExpenceGetAll(ProjectDetails.ProjectID));
        textViewTotalExpence.setText("Total Expence :- " + String.valueOf(dataBaseExpenceClasList.size()) + " /-");
        adapterExpence.notifyDataSetChanged();
    }

}

