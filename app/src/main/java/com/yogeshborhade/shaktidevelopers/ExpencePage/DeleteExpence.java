package com.yogeshborhade.shaktidevelopers.ExpencePage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseExpenceNameClass;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.ArrayList;

public class DeleteExpence extends AppCompatActivity {


    RecyclerView recyclerView;
    Button backButton;
    //Expence Spinner
    ArrayList<DataBaseExpenceNameClass> dataBaseExpenceNameClassArrayList = new ArrayList<>();
    AdapterDeleteExpence adapterExpenceName;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expence_details_page);


        db = new DatabaseHelper(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        backButton = (Button) findViewById(R.id.mbackButton);


        adapterExpenceName = new AdapterDeleteExpence(dataBaseExpenceNameClassArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterExpenceName);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getCustomerList();
    }

    public void getCustomerList() {
        dataBaseExpenceNameClassArrayList.clear();
        dataBaseExpenceNameClassArrayList.addAll(db.ExpenceNameGetAll());
        adapterExpenceName.notifyDataSetChanged();
    }
}
