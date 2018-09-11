package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectPlot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.BookPlotDataBase;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseCustomerClass;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.PojoCustomerSpinner;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.ArrayList;
import java.util.List;

public class BookPlotMainPage extends AppCompatActivity {

    TextView AddNewCustomerToPlot;
    Button backButton;
    RecyclerView recyclerView;


    String projectId, plotAllocatedCustomerId = "0", plotid,
            plotNumber,
            plotEastSize, plotWestSize, plotNorthsize, plotSouthSize,
            plotRegisterDate, plotTotalAreaSqureFoot,
            plotRatePerSqureFoot, plotTotalSellingPrice,
            plotStatus, plotNotificationRemark, plotNotificationDate,
            plotGivenAmount, plotGivenAmountDate, plotRamainingAmount = "00", plotRemainingAmountCommitmentDate, plotRemark, plotPaymentMode, plotPaymentModenumber = "0";


    DatabaseHelper databaseHelper;


    public List<DataBaseCustomerClass> dataBaseCustomerClassList = new ArrayList<>();
    public ArrayList<PojoCustomerSpinner> pojoCustomerList = new ArrayList<PojoCustomerSpinner>();
    public List<BookPlotDataBase> bookPlotDataBaseList = new ArrayList<>();

    AdapterBookPlotCustomerList customerAdapter;
    AdapterAlloacteCustomerToPlot adapterAlloacteCustomerToPlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_plot_main_page);

        databaseHelper = new DatabaseHelper(this);
        getXMLContent();
        getDetailsFromIntent();

        // Recycler View
        adapterAlloacteCustomerToPlot = new AdapterAlloacteCustomerToPlot(bookPlotDataBaseList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterAlloacteCustomerToPlot);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();


            }
        });

        AddNewCustomerToPlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BookPlotMainPage.this, BookPlotAllocateCustomerToPlot.class);
                i.putExtra("bookprojectID", projectId);
                i.putExtra("bookPlotID", plotid);
                i.putExtra("bookPlotEast", plotEastSize);
                i.putExtra("bookPlotWest", plotWestSize);
                i.putExtra("bookPlotNorth", plotNorthsize);
                i.putExtra("bookPlotSouth", plotSouthSize);
                i.putExtra("bookTotalArea", plotTotalAreaSqureFoot);
                i.putExtra("bookRatePerSqureFoot", plotRatePerSqureFoot);
                i.putExtra("bookTotalAmount", plotTotalSellingPrice);
                i.putExtra("booktstatus", plotStatus);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        GetAllocatedCustomer();
    }

    public void getXMLContent() {
        backButton = (Button) findViewById(R.id.mbackButton);
        AddNewCustomerToPlot = (TextView) findViewById(R.id.mAddNewCustomerTothisPlot);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }


    public void getDetailsFromIntent() {
        Bundle bundle = getIntent().getExtras();
        projectId = bundle.getString("bookprojectID", "0");
        plotid = bundle.getString("bookPlotID", "0");
        plotEastSize = bundle.getString("bookPlotEast", "0");
        plotWestSize = bundle.getString("bookPlotWest", "0");
        plotNorthsize = bundle.getString("bookPlotNorth", "0");
        plotSouthSize = bundle.getString("bookPlotSouth", "0");

        plotTotalAreaSqureFoot = bundle.getString("bookTotalArea", "0");
        plotRatePerSqureFoot = bundle.getString("bookRatePerSqureFoot", "0");
        plotTotalSellingPrice = bundle.getString("bookTotalAmount", "0");
        plotTotalSellingPrice = bundle.getString("bookTotalAmount", "0");
        plotStatus = bundle.getString("booktstatus", "0");


    }


    private void GetAllocatedCustomer() {
        bookPlotDataBaseList.clear();
       // database handler
        databaseHelper = new DatabaseHelper(getApplicationContext());
       // Spinner Drop down elements
        bookPlotDataBaseList.addAll(databaseHelper.BookPlotGetAllAllocatedCustomerPlotId(String.valueOf(plotid), projectId));
        adapterAlloacteCustomerToPlot.notifyDataSetChanged();

/*        for (int i = 0; i < bookPlotDataBaseList.size(); i++) {
            BookPlotDataBase bookPlotDataBase = bookPlotDataBaseList.get(i);
            String CustomerID = bookPlotDataBase.getBookCustomerID();
            String bookedId = String.valueOf(bookPlotDataBase.getBookID());
            String PlotID = String.valueOf(bookPlotDataBase.getBookID());


        }*/
    }

}
