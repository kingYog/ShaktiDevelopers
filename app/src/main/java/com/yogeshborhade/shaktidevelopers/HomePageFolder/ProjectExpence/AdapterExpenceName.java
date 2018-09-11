package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectExpence;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseExpenceNameClass;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.ArrayList;

public class AdapterExpenceName extends BaseAdapter {
    Context context;
    public ArrayList<DataBaseExpenceNameClass> dataBaseExpenceNameClassArrayList;

    LayoutInflater inflter;

    public AdapterExpenceName(Context applicationContext, ArrayList<DataBaseExpenceNameClass> dataBaseExpenceNameClassArrayList) {
        this.context = applicationContext;
        this.dataBaseExpenceNameClassArrayList = dataBaseExpenceNameClassArrayList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return dataBaseExpenceNameClassArrayList.size();
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
        view = inflter.inflate(R.layout.list_row_expence_name, null);
        TextView CustomerName = (TextView) view.findViewById(R.id.ProjectName);
        DataBaseExpenceNameClass databaseProjectClass = dataBaseExpenceNameClassArrayList.get(i);
        CustomerName.setText(databaseProjectClass.getExpenceName());

        return view;
    }
}
