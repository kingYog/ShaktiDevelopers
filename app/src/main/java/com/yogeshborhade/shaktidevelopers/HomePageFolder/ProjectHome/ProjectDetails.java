package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectHome;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectCustomer.CustomerFragment;
import com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectExpence.FragmentExpence;
import com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectFarmer.FarmerFragment;
import com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectPlot.FragmentPlotDetails;
import com.yogeshborhade.shaktidevelopers.HomePageFolder.Result.ResultPage;
import com.yogeshborhade.shaktidevelopers.R;

import java.text.SimpleDateFormat;

public class ProjectDetails extends AppCompatActivity {

    TextView imageViewDetail, imageViewEntry, imageViewPrize, imageViewStricture, textViewProjectName;
    Button backButton, statButton;
    FrameLayout frameLayout;
    public static String ProjectID, ProjectName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);

        getXMLContent();
        Bundle bundle = getIntent().getExtras();
        ProjectID = bundle.getString("pid", "0");
        ProjectName = bundle.getString("pName", "NA");
        textViewProjectName.setText(ProjectName);

        loadFragment(new FragmentPlotDetails());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                finish();
            }
        });

        imageViewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ChangeButtonColor("plot");

            }
        });


        imageViewEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ChangeButtonColor("Customer");

            }
        });


        imageViewPrize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ChangeButtonColor("Farmer");

            }
        });


        imageViewStricture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeButtonColor("Expence");
            }
        });

        statButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProjectDetails.this, ResultPage.class);
                startActivity(intent);


            }
        });


    }

    public void loadFragment(Fragment fragment) {
        // create a FragmentManager
        FragmentManager fm = getFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.mDashBoardFrame, fragment);
        fragmentTransaction.commit(); // save the changes
    }


    public void getXMLContent() {
        imageViewDetail = (TextView) findViewById(R.id.mImageDetail);
        imageViewEntry = (TextView) findViewById(R.id.mImageEntries);
        imageViewPrize = (TextView) findViewById(R.id.mImagePrize);
        imageViewStricture = (TextView) findViewById(R.id.mImageStructure);
        frameLayout = (FrameLayout) findViewById(R.id.mDashBoardFrame);
        backButton = (Button) findViewById(R.id.mbackButton);
        statButton = (Button) findViewById(R.id.mgetStat);

        textViewProjectName = (TextView) findViewById(R.id.mtextProjectName);
    }


    public void ChangeButtonColor(String clickedButton) {
        if (clickedButton.equals("plot")) {

            imageViewDetail.setBackgroundResource(R.drawable.db_btn_detail_blue);
            imageViewEntry.setBackgroundResource(R.drawable.db_btn_enties_grey);
            imageViewPrize.setBackgroundResource(R.drawable.db_btn_enties_grey);
            imageViewStricture.setBackgroundResource(R.drawable.db_btn_structure_grey);
            loadFragment(new FragmentPlotDetails());

        } else if (clickedButton.equals("Customer")) {
            imageViewDetail.setBackgroundResource(R.drawable.db_btn_detail_grey);
            imageViewEntry.setBackgroundResource(R.drawable.db_btn_entries_blue);
            imageViewPrize.setBackgroundResource(R.drawable.db_btn_enties_grey);
            imageViewStricture.setBackgroundResource(R.drawable.db_btn_structure_grey);

            loadFragment(new CustomerFragment());

        } else if (clickedButton.equals("Farmer")) {

            imageViewDetail.setBackgroundResource(R.drawable.db_btn_detail_grey);
            imageViewEntry.setBackgroundResource(R.drawable.db_btn_enties_grey);
            imageViewPrize.setBackgroundResource(R.drawable.db_btn_entries_blue);
            imageViewStricture.setBackgroundResource(R.drawable.db_btn_structure_grey);
            loadFragment(new FarmerFragment());

        } else {

            imageViewDetail.setBackgroundResource(R.drawable.db_btn_detail_grey);
            imageViewEntry.setBackgroundResource(R.drawable.db_btn_enties_grey);
            imageViewPrize.setBackgroundResource(R.drawable.db_btn_enties_grey);
            imageViewStricture.setBackgroundResource(R.drawable.db_btn_structure_blue);
            loadFragment(new FragmentExpence());


        }
    }

    public static String getDate() {

        long date = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = sdf.format(date);

        return dateString;
    }

}
