package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectPlot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yogeshborhade.shaktidevelopers.DatabaseClas.PojoCustomerSpinner;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.ArrayList;

/**
 * Created by admin on 13-05-2018.
 */

public class AdapterBookPlotCustomerList extends BaseAdapter {
    Context context;
    public ArrayList<PojoCustomerSpinner> CustomListViewValuesArr;

    LayoutInflater inflter;

    public AdapterBookPlotCustomerList(Context applicationContext, ArrayList<PojoCustomerSpinner> CustomListViewValuesArr) {
        this.context = applicationContext;
        this.CustomListViewValuesArr = CustomListViewValuesArr;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return CustomListViewValuesArr.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.spinner_rows, null);
        TextView CustomerID = (TextView) view.findViewById(R.id.CustomerID);
        TextView CustomerName = (TextView) view.findViewById(R.id.CustomerName);

        PojoCustomerSpinner pojoCustomerSpinner = CustomListViewValuesArr.get(i);
        CustomerID.setText("Customer ID:- " + pojoCustomerSpinner.getCustomerID());
        CustomerName.setText("Customer Name:- " + pojoCustomerSpinner.getCustomerNamer());
        return view;
    }
}