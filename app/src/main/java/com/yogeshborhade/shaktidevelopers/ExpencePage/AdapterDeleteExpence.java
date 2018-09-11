package com.yogeshborhade.shaktidevelopers.ExpencePage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseExpenceNameClass;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.ArrayList;

public class AdapterDeleteExpence extends RecyclerView.Adapter<AdapterDeleteExpence.MyViewHolder> {

    ArrayList<DataBaseExpenceNameClass> dataBaseExpenceNameClassArrayList = new ArrayList<>();
    Context context;
    DatabaseHelper databaseHelper;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textExpenceName;
        public Button btnDeleteExpence;

        public MyViewHolder(View view) {
            super(view);
            textExpenceName = (TextView) view.findViewById(R.id.mTextExpenceName);
            btnDeleteExpence = (Button) view.findViewById(R.id.mDelete);

        }
    }


    public AdapterDeleteExpence(ArrayList<DataBaseExpenceNameClass> dataBaseExpenceNameClassArrayList) {
        this.dataBaseExpenceNameClassArrayList = dataBaseExpenceNameClassArrayList;
    }

    @Override

    public AdapterDeleteExpence.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_expence_list_update, parent, false);

        return new AdapterDeleteExpence.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(AdapterDeleteExpence.MyViewHolder holder, final int position) {
        final DataBaseExpenceNameClass dataBaseExpenceNameClass = dataBaseExpenceNameClassArrayList.get(position);


        holder.textExpenceName.setText(dataBaseExpenceNameClass.getExpenceName());

        holder.btnDeleteExpence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseHelper = new DatabaseHelper(view.getContext());
                int id = databaseHelper.ExpenceNameDelete(dataBaseExpenceNameClass.getExpenceID());
                dataBaseExpenceNameClassArrayList.remove(position);
                notifyDataSetChanged();
            }
        });

    }


    @Override
    public int getItemCount() {
        return dataBaseExpenceNameClassArrayList.size();
    }
}



