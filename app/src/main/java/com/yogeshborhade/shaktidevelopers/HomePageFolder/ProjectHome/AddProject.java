package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectHome;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DatabaseProjectClass;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddProject extends AppCompatActivity {
    Button backButton, submitButton;
    EditText editTextProjectName, editTextProjectAdress, editTextProjectSizeEast, editTextProjectSizeSouth, editTextProjectSizeNorth, editTextProjectSizeWest, editTextProjectTotalArea;
    TextView editTextProjectStartDate;
    ImageView imageViewDatePicker;

    String projectId, projectName, projectStartDate, projectAdress, projectEastSize, projectWestSize, projectNorthsize, projectSouthSize,
            projectTotalAreaInSft, project_total_available_plots, project_total_sale_plots, project_total_expence, project_total_earn, project_total_profit;
    DatabaseHelper db;

    private List<DatabaseProjectClass> projectList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        db = new DatabaseHelper(this);
        getXMLContent();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        imageViewDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int yy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH);
                int dd = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date = String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear+1)
                                + "-" + String.valueOf(year);

                        editTextProjectStartDate.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                projectName = editTextProjectName.getText().toString();
                projectStartDate = editTextProjectStartDate.getText().toString();
                projectAdress = editTextProjectAdress.getText().toString();
                projectEastSize = editTextProjectSizeEast.getText().toString();
                projectWestSize = editTextProjectSizeWest.getText().toString();
                projectNorthsize = editTextProjectSizeNorth.getText().toString();
                projectSouthSize = editTextProjectSizeSouth.getText().toString();
                projectTotalAreaInSft = editTextProjectTotalArea.getText().toString();
                project_total_available_plots = "NA";
                project_total_sale_plots = "NA";
                project_total_expence = "NA";
                project_total_earn = "NA";
                project_total_profit = "NA";



                long id = db.ProjectInsert(projectName, projectAdress, projectStartDate, projectTotalAreaInSft,
                        projectEastSize, projectWestSize, projectNorthsize, projectSouthSize, project_total_available_plots, project_total_sale_plots, project_total_expence, project_total_earn, project_total_profit);

                if (id == 0) {
                  //  Toast.makeText(AddProject.this, "Project not added", Toast.LENGTH_LONG).show();
                } else {
                  //  Toast.makeText(AddProject.this, "Project Created " + id, Toast.LENGTH_LONG).show();

                    getProjectsList();
                }

                finish();

            }
        });


    }

    //  Declare Xml Content
    public void getXMLContent() {
        backButton = (Button) findViewById(R.id.mbackButton);
        submitButton = (Button) findViewById(R.id.mSubmit);
        editTextProjectName = (EditText) findViewById(R.id.medt_ProjectName);
        editTextProjectAdress = (EditText) findViewById(R.id.medt_ProjectAdress);
        editTextProjectSizeEast = (EditText) findViewById(R.id.medt_ProjectEast);
        editTextProjectSizeWest = (EditText) findViewById(R.id.medt_ProjectWest);
        editTextProjectSizeNorth = (EditText) findViewById(R.id.medt_ProjectNorth);
        editTextProjectSizeSouth = (EditText) findViewById(R.id.medt_ProjectSouth);
        editTextProjectTotalArea = (EditText) findViewById(R.id.medt_ProjectTotalArea);
        editTextProjectStartDate = (TextView) findViewById(R.id.medt_date);
        imageViewDatePicker = (ImageView) findViewById(R.id.mdatePicker);
    }


    public void getProjectsList() {
        projectList.addAll(db.ProjectGetAll());

        for (int i = 0; i < projectList.size(); i++) {
            DatabaseProjectClass databaseProjectClass = projectList.get(i);
            databaseProjectClass.getProject_adress();

            Log.e("Project ID", String.valueOf(databaseProjectClass.getProject_id()));
            Log.e("Project NAme", String.valueOf(databaseProjectClass.getProject_name()));
            Log.e("Project Add", String.valueOf(databaseProjectClass.getProject_adress()));
            Log.e("Project added Date", String.valueOf(databaseProjectClass.getProject_added_date()));
            Log.e("Project Area", String.valueOf(databaseProjectClass.getProject_totalArea()));
            Log.e("Project East", String.valueOf(databaseProjectClass.getProject_east_side()));
            Log.e("Project West", String.valueOf(databaseProjectClass.getProject_west_side()));
            Log.e("Project North", String.valueOf(databaseProjectClass.getProject_south_side()));
            Log.e("Project South", String.valueOf(databaseProjectClass.getProject_north_side()));
            Log.e("Project Expence", String.valueOf(databaseProjectClass.getProject_total_expence()));
            Log.e("Project Availabale Plots", String.valueOf(databaseProjectClass.getProject_total_available_plots()));

            Log.e("***********************", "****************************************");


        }
    }
}
