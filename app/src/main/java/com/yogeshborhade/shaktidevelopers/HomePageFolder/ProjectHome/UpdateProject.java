package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectHome;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class UpdateProject extends AppCompatActivity {

    Button backButton, updateButton, deleteButton;
    EditText editTextProjectName, editTextProjectAdress, editTextProjectSizeEast,
            editTextProjectSizeSouth, editTextProjectSizeNorth, editTextProjectSizeWest, editTextProjectTotalArea;
    TextView editTextProjectStartDate, editTextAvailblePlots, editTextSalePlots,
            editTextEarn, editTextExpence, editTextTotalProfit, editTextProjectId;
    ImageView imageViewDatePicker;

    String projectId, projectName, projectStartDate, projectAdress, projectEastSize, projectWestSize, projectNorthsize, projectSouthSize,
            projectTotalAreaInSft, project_total_available_plots, project_total_sale_plots, project_total_expence, project_total_earn, project_total_profit;
    String intentProjectId;

    DatabaseHelper databaseHelper;
    private List<DatabaseProjectClass> projectList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_project);

        getXMLContent();

        Bundle bundle = getIntent().getExtras();
        String intentProjectId = bundle.getString("pid", "0");

        databaseHelper = new DatabaseHelper(this);


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

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProject();
            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteProject(Integer.parseInt(projectId));
            }
        });


        getSingleProject(Integer.parseInt(intentProjectId));
    }

    public void getXMLContent() {
        backButton = (Button) findViewById(R.id.mbackButton);
        updateButton = (Button) findViewById(R.id.mUpdate);
        deleteButton = (Button) findViewById(R.id.mDelete);


        editTextProjectName = (EditText) findViewById(R.id.medt_ProjectName);
        editTextProjectAdress = (EditText) findViewById(R.id.medt_ProjectAdress);
        editTextProjectSizeEast = (EditText) findViewById(R.id.medt_ProjectEast);
        editTextProjectSizeWest = (EditText) findViewById(R.id.medt_ProjectWest);
        editTextProjectSizeNorth = (EditText) findViewById(R.id.medt_ProjectNorth);
        editTextProjectSizeSouth = (EditText) findViewById(R.id.medt_ProjectSouth);
        editTextProjectTotalArea = (EditText) findViewById(R.id.medt_ProjectTotalArea);


        editTextProjectId = (TextView) findViewById(R.id.mtextProjectID);
        editTextProjectStartDate = (TextView) findViewById(R.id.medt_date);
        editTextAvailblePlots = (TextView) findViewById(R.id.mtextAvailablePlot);
        editTextSalePlots = (TextView) findViewById(R.id.mtextSalePlot);
        editTextEarn = (TextView) findViewById(R.id.mtextEarn);
        editTextExpence = (TextView) findViewById(R.id.mtextExpence);
        editTextTotalProfit = (TextView) findViewById(R.id.mtextProfit);

        imageViewDatePicker = (ImageView) findViewById(R.id.mdatePicker);
    }

    public void getSingleProject(int pid) {

        projectList.addAll(databaseHelper.ProjectGetSingle(pid));

        for (int i = 0; i <= projectList.size() - 1; i++) {
            final DatabaseProjectClass pojoDatabaseProjectClass = projectList.get(i);
            projectId = String.valueOf(pojoDatabaseProjectClass.getProject_id());
            projectName = pojoDatabaseProjectClass.getProject_name();
            projectStartDate = pojoDatabaseProjectClass.getProject_added_date();
            projectAdress = pojoDatabaseProjectClass.getProject_adress();
            projectTotalAreaInSft = pojoDatabaseProjectClass.getProject_totalArea();
            projectEastSize = pojoDatabaseProjectClass.getProject_east_side();
            projectWestSize = pojoDatabaseProjectClass.getProject_west_side();
            projectNorthsize = pojoDatabaseProjectClass.getProject_north_side();
            projectSouthSize = pojoDatabaseProjectClass.getProject_south_side();
            project_total_available_plots = pojoDatabaseProjectClass.getProject_total_available_plots();
            project_total_sale_plots = pojoDatabaseProjectClass.getProject_total_sale_plots();
            project_total_earn = pojoDatabaseProjectClass.getProject_total_earn();
            project_total_expence = pojoDatabaseProjectClass.getProject_total_expence();
            project_total_profit = pojoDatabaseProjectClass.getProject_total_profit();


            editTextProjectId.setText("Project ID " + projectId);
            editTextProjectName.setText(projectName);
            editTextProjectStartDate.setText(projectStartDate);
            editTextProjectAdress.setText(projectAdress);
            editTextProjectTotalArea.setText(projectTotalAreaInSft);
            editTextProjectSizeEast.setText(projectEastSize);
            editTextProjectSizeWest.setText(projectWestSize);
            editTextProjectSizeSouth.setText(projectSouthSize);
            editTextProjectSizeNorth.setText(projectNorthsize);
            editTextAvailblePlots.setText(project_total_available_plots);
            editTextSalePlots.setText(project_total_sale_plots);
            editTextEarn.setText(project_total_earn);
            editTextExpence.setText(project_total_expence);
            editTextTotalProfit.setText(project_total_profit);


        }
    }

    public void updateProject() {
        projectName = editTextProjectName.getText().toString();
        projectStartDate = editTextProjectStartDate.getText().toString();
        projectAdress = editTextProjectAdress.getText().toString();
        projectEastSize = editTextProjectSizeEast.getText().toString();
        projectWestSize = editTextProjectSizeWest.getText().toString();
        projectNorthsize = editTextProjectSizeNorth.getText().toString();
        projectSouthSize = editTextProjectSizeSouth.getText().toString();
        projectTotalAreaInSft = editTextProjectTotalArea.getText().toString();


        long id = databaseHelper.ProjectUpdate(Integer.parseInt(projectId), projectName, projectAdress, projectStartDate, projectTotalAreaInSft, projectEastSize,
                projectWestSize, projectNorthsize, projectSouthSize);

        if (id == 0) {
         //   Toast.makeText(UpdateProject.this, "Not Inserted", Toast.LENGTH_SHORT).show();
        } else {
           // Toast.makeText(UpdateProject.this, "Row  Updated" + id, Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void deleteProject(int projectId) {
        long id = databaseHelper.ProjectDelete(projectId);

        if (id == 0) {
          //  Toast.makeText(UpdateProject.this, "Not Inserted", Toast.LENGTH_SHORT).show();
        } else {
          //  Toast.makeText(UpdateProject.this, "Row  Updated" + id, Toast.LENGTH_SHORT).show();
        }
        finish();
    }


}
