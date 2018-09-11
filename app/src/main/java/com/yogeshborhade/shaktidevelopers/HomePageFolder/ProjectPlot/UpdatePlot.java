package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectPlot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DatabasePlotClass;
import com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectHome.ProjectDetails;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.ArrayList;
import java.util.List;

import static com.yogeshborhade.shaktidevelopers.Database.Constants.numberWithoutExponential;

public class UpdatePlot extends AppCompatActivity {

    Button backButton, updateButton, deleteButton;
    TextView textViewPlotID, textViewProjectID,
            textViewImageID, textViewEast, textViewWest, textViewSouth, textViewNorth, textViewstatus;
    EditText editTextCustomerAllocationID, editTextPlotNumber,
            editTextPlotEastSize, editTextPlotWestSize, editTextPlotNorthSize, editTextPlotSouthSize,
            editTextPlotRegistrationDate, editTextPlotTotalSqureFooot, editTextPlotPricePerSqureFoot,
            editTextTotalPlotSellingPrice;


    String projectId, plotAllocatedCustomerId, plotid,
            plotNumber,
            plotEastSize, plotWestSize, plotNorthsize, plotSouthSize,
            plotRegisterDate, plotTotalAreaSqureFoot,
            plotPricePerSqureFoot, plotTotalSellingPrice,
            plotStatus,
            plotRemark;
    DatabaseHelper databaseHelper;


    private List<DatabasePlotClass> plotList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot_details);
        databaseHelper = new DatabaseHelper(this);
        Bundle bundle = getIntent().getExtras();
        projectId = bundle.getString("projectID", "0");
        plotid = bundle.getString("plotID", "0");

        Log.e(projectId, String.valueOf(plotid));
        getXMLContent();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdatePlot();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteProject(Integer.parseInt(plotid));
            }
        });


        editTextPlotPricePerSqureFoot.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (s.length() != 0) {
                    editTextTotalPlotSellingPrice.setText(String.valueOf(numberWithoutExponential.format(Double.valueOf(editTextPlotPricePerSqureFoot.getText().toString()) * Double.valueOf(editTextPlotTotalSqureFooot.getText().toString()))));
                } else {
                    editTextTotalPlotSellingPrice.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        getSinglePlot(Integer.parseInt(plotid), projectId);
    }

    public void getXMLContent() {
        backButton = (Button) findViewById(R.id.mbackButton);
        updateButton = (Button) findViewById(R.id.mUpdate);
        deleteButton = (Button) findViewById(R.id.mDelete);
        textViewProjectID = (TextView) findViewById(R.id.mtextProjectId);
        textViewPlotID = (TextView) findViewById(R.id.mtextPlotID);

        textViewEast = (TextView) findViewById(R.id.mtextimageEastSize);
        textViewWest = (TextView) findViewById(R.id.mtextimageWestSize);
        textViewNorth = (TextView) findViewById(R.id.mtextimageNorthSize);
        textViewSouth = (TextView) findViewById(R.id.mtextimageSouthSize);
        textViewImageID = (TextView) findViewById(R.id.mtextimageplotID);
        textViewstatus = (TextView) findViewById(R.id.mtextstatus);


        editTextCustomerAllocationID = (EditText) findViewById(R.id.medtCustomerAllocationID);
        editTextPlotNumber = (EditText) findViewById(R.id.medtPlotNumber);
        editTextPlotEastSize = (EditText) findViewById(R.id.medtPlotEastSize);
        editTextPlotWestSize = (EditText) findViewById(R.id.medtPlotWestSize);
        editTextPlotNorthSize = (EditText) findViewById(R.id.medtPlotNorthSize);
        editTextPlotSouthSize = (EditText) findViewById(R.id.medtPlotSouthSize);
        editTextPlotRegistrationDate = (EditText) findViewById(R.id.medtRegistrationDate);
        editTextPlotTotalSqureFooot = (EditText) findViewById(R.id.medtTotalSqureFoot);
        editTextPlotPricePerSqureFoot = (EditText) findViewById(R.id.medtPlotPricePerSqureFoot);
        editTextTotalPlotSellingPrice = (EditText) findViewById(R.id.medtPlotPriceSelligPrice);


    }

    public void getSinglePlot(int Plot_Id, String ProjectID) {

        plotList.addAll(databaseHelper.PlotGetSingle(Plot_Id, ProjectID));

        for (int i = 0; i <= plotList.size() - 1; i++) {
            final DatabasePlotClass databasePlotClass = plotList.get(i);


            projectId = databasePlotClass.getProject_Id();
            plotAllocatedCustomerId = databasePlotClass.getPlot_allocated_customer_id();
            plotid = String.valueOf(databasePlotClass.getPlot_Id());
            plotNumber = databasePlotClass.getPlot_number();
            plotEastSize = databasePlotClass.getPlot_east_side();
            plotWestSize = databasePlotClass.getPlot_west_side();
            plotNorthsize = databasePlotClass.getPlot_north_side();
            plotSouthSize = databasePlotClass.getPlot_south_side();
            plotRegisterDate = databasePlotClass.getPlot_register_date();
            plotTotalAreaSqureFoot = databasePlotClass.getPloto_total_area_squre_foot();
            plotPricePerSqureFoot = databasePlotClass.getPlot_price_per_squre_foot();
            plotTotalSellingPrice = databasePlotClass.getPlot_total_price();
            plotStatus = databasePlotClass.getPlot_status();


            textViewImageID.setText("Plot id = " + plotid);
            textViewEast.setText("EAST = " + plotEastSize);
            textViewWest.setText("WEST = " + plotWestSize);
            textViewNorth.setText("SOUTH = " + plotNorthsize);
            textViewSouth.setText("NORTH = " + plotSouthSize);


            textViewProjectID.setText(projectId);
            textViewPlotID.setText(plotid);
            editTextCustomerAllocationID.setText(plotAllocatedCustomerId);
            editTextPlotNumber.setText(plotNumber);
            editTextPlotEastSize.setText(plotEastSize);
            editTextPlotWestSize.setText(plotWestSize);
            editTextPlotNorthSize.setText(plotNorthsize);
            editTextPlotSouthSize.setText(plotSouthSize);
            editTextPlotRegistrationDate.setText(plotRegisterDate);
            editTextPlotTotalSqureFooot.setText(plotTotalAreaSqureFoot);
            editTextPlotPricePerSqureFoot.setText(plotPricePerSqureFoot);
            editTextTotalPlotSellingPrice.setText(plotTotalSellingPrice);
            textViewstatus.setText(plotStatus);


        }
    }

    public void UpdatePlot() {
        projectId = ProjectDetails.ProjectID;
        plotid = textViewPlotID.getText().toString();
        plotAllocatedCustomerId = editTextCustomerAllocationID.getText().toString();
        plotNumber = editTextPlotNumber.getText().toString();
        plotEastSize = editTextPlotEastSize.getText().toString();
        plotWestSize = editTextPlotWestSize.getText().toString();
        plotNorthsize = editTextPlotNorthSize.getText().toString();
        plotSouthSize = editTextPlotSouthSize.getText().toString();
        plotRegisterDate = editTextPlotRegistrationDate.getText().toString();
        plotTotalAreaSqureFoot = editTextPlotTotalSqureFooot.getText().toString();
        plotPricePerSqureFoot = editTextPlotPricePerSqureFoot.getText().toString();
        plotTotalSellingPrice = editTextTotalPlotSellingPrice.getText().toString();
        plotStatus = textViewstatus.getText().toString();
        plotRemark = "NA";


        long id = databaseHelper.PlotUpdate(plotid, projectId, plotAllocatedCustomerId, plotNumber, plotEastSize,
                plotWestSize, plotNorthsize, plotSouthSize, plotRegisterDate,
                plotTotalAreaSqureFoot, plotPricePerSqureFoot, plotTotalSellingPrice, plotStatus, plotRemark);

        if (id == 0) {
          //  Toast.makeText(UpdatePlot.this, "Not Inserted", Toast.LENGTH_SHORT).show();
        } else {
          //  Toast.makeText(UpdatePlot.this, "Inserted Row " + id, Toast.LENGTH_SHORT).show();
            finish();

        }

    }

    public void deleteProject(int plotID) {
        long id = databaseHelper.PlotDelete(plotID);

        if (id == 0) {
           // Toast.makeText(UpdatePlot.this, "Not Inserted", Toast.LENGTH_SHORT).show();
        } else {
          //  Toast.makeText(UpdatePlot.this, "Row  Updated" + id, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}
