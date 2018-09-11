package com.yogeshborhade.shaktidevelopers.HomePageFolder.CustomerDetailsPdf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yogeshborhade.shaktidevelopers.DatabaseClas.DatabaseProjectClass;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.ArrayList;

public class AdapterGetAllProject extends BaseAdapter {
    Context context;
    public ArrayList<DatabaseProjectClass> databaseProjectClasses;

    LayoutInflater inflter;

    public AdapterGetAllProject(Context applicationContext, ArrayList<DatabaseProjectClass> databaseProjectClasses) {
        this.context = applicationContext;
        this.databaseProjectClasses = databaseProjectClasses;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return databaseProjectClasses.size();
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
        view = inflter.inflate(R.layout.list_row_get_all_project, null);
        TextView CustomerName = (TextView) view.findViewById(R.id.ProjectName);
        DatabaseProjectClass databaseProjectClass = databaseProjectClasses.get(i);
        CustomerName.setText(databaseProjectClass.getProject_name());

        return view;
    }
}