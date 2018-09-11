package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectCustomer;

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
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseCustomerClass;
import com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectHome.ProjectDetails;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 06-04-2018.
 */

public class CustomerFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    Button btnAddCustomer;
    TextView textViewTotalCustomer;

    AdapterCustomer customerAdapter;
    private List<DataBaseCustomerClass> dataBaseCustomerClassList = new ArrayList<>();
    DatabaseHelper db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_customet, container, false);

        getXMLContent();
        db = new DatabaseHelper(view.getContext());
        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view.getContext(), AddCustomer.class);
                startActivity(i);
            }
        });

        customerAdapter = new AdapterCustomer(dataBaseCustomerClassList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(customerAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getCustomerList();
    }


    public void getXMLContent() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        btnAddCustomer = (Button) view.findViewById(R.id.mbtnAddProject);
        textViewTotalCustomer = (TextView) view.findViewById(R.id.mtexTotalProject);
    }


    public void getCustomerList() {
        dataBaseCustomerClassList.clear();
        dataBaseCustomerClassList.addAll(db.CustomerGetAll(ProjectDetails.ProjectID));
        textViewTotalCustomer.setText("Total Customers:- " + String.valueOf(dataBaseCustomerClassList.size()) + " /-");
        customerAdapter.notifyDataSetChanged();
    }

}
